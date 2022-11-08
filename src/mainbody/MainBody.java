/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainbody;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class MainBody extends JFrame implements ActionListener {
    
    //USER VARIABLES: change these, in minutes
    //if desired can be altered
    static int amount_of_errors = 90;               //the amount of errors there should be during the whole duration of the simulation
    static int types_of_erros = 12;                 //how many different types of errors there are (12 by default, and 12 if not re-programmed)
    static int round_duration = 15000;              //the number of milliseconds one round should last - how often the variables are to be refreshed
    //redesigned by Jurda
    static int min_time_difference = 80;          //minimum amount of rounds between two failures, for seconds, multiply by round_duration/1000; origin 120
    //redesigned by Jurda
    static int max_time_difference = 160;          //maximum amount of rounds between two failures, for seconds, multiply by round_duration/1000; origin 240
    
    
    // the most important, initiation variables
    static float round = 0;                         //the initial round
    static int errornumber = 0;                     //defines how many errors there already were
    static int time = 0;                            //time of the initiation, will increase by round_duration/1000 per round
    static boolean FAILURE = true;                 //defines that we begin in the state with no failure
    static boolean TIMETOFAIL = true;              //defines that we begin with the time that does not initiate failure
    
    
    // timers used
    static Timer t;                                 //timer for this, main body for no-failure scenario
    static Timer t2;                                //timer for this, main body for a failure scenario, initiated when t.stop()
    static Timer s;                                 //timer for the sidebody for a no-failure scenario to refresh the variables
    static Timer f;                                 //timer for the sidebody for a failure scenario to refresh the variables and check on the command
    
    
    // object variables
    static MainBody mainbody;
    static SideBody sidebody;
    static SideBody fsidebody;
    static Terminal terminal;
    
    
    //these are the variables of ECLSS itself, and the initial conditions
    //if desired can be altered
    double temperature = 20;                        //cabin temperature in degrees Celsius
    double pressure = 14504;                        //cabin pressure 10e3
    double oxygen = 21;                             //the level of oxygen in %
    double throttle = 80;                           //throttle in %
    double lpotrpm = 5150;                          //low pressure oxidizer turbopump - roots per minute
    double lpotpsia = 422;                          //low pressure oxidizer turbopump pressure in psia - pounds per square inch absolute
    double hpotrpm = 28120;                         //high pressure oxidizer turbopump - roots per minute
    double hpotpsia = 4000;                         //high pressure oxidizer turbopump pressure in psia
    double lpftrpm = 16185;                         //low pressure fuel turbopump - roots per minute
    double hpftrpm = 35360;                         //high pressure fuel turbopump - roots per minute
    double coolantcontrolvalve = 100;               //operation of the coolant control valve in %
    boolean pogo = true;                            //status of the pogo suppression system
    String GPCstatus = ("1 1 1 1 1");               //status of the 5 shuttle general purpose computers
    boolean purgesequence = false;                  //status of the purge sequence 
    double lh2psia = 34;                            //liquid hydrogen tank's vent pressure in psia
    double hepsiaright = 1050;                      //right part of the helium subsystem: valves' pressure in psia
    double hepsiacenter = 1050;                     //central part of the helium subsystem: valves' pressure in psia
    double hepsialeft = 1050;                       //left part of the helium subsystem: valves' pressure in psia
    double heregpsiaright = hepsiaright / 1.4;      //right part of the helium subsystem: valves' regulated pressure in psia
    double heregpsiacenter = hepsiacenter / 1.4;    //central part of the helium subsystem: valves' regulated pressure in psia
    double heregpsialeft = hepsialeft / 1.4;        //left part of the helium subsystem: valves' regulated pressure in psia
    double hedpdtright = heregpsiaright / 29;       //right part of the helium subsystem: valves' pressure change
    double hedpdtcenter = heregpsiacenter / 29;     //central part of the helium subsystem: valves' pressure change
    double hedpdtleft = heregpsialeft / 29;         //left part of the helium subsystem: valves' pressure change
    
    
    // these are the variables of ECLSS for GUI
    JLabel jtemperature;
    JLabel jpressure;
    JLabel joxygen;
    JLabel jthrottle;
    JLabel jlpotrpm; 
    JLabel jlpotpsia; 
    JLabel jhpotrpm; 
    JLabel jhpotpsia;
    JLabel jlpftrpm; 
    JLabel jhpftrpm;
    JLabel jcoolantcontrolvalve;
    JLabel jpogo;
    JLabel jGPCstatus;
    JLabel jpurgesequence;
    JLabel jlh2psia;
    JLabel jhepsiaright; 
    JLabel jhepsiacenter; 
    JLabel jhepsialeft;
    JLabel jheregpsiaright; 
    JLabel jheregpsiacenter; 
    JLabel jheregpsialeft;
    JLabel jhedpdtright; 
    JLabel jhedpdtcenter; 
    JLabel jhedpdtleft;
    JLabel jtime;
    JLabel jdate;
    JLabel jnotice;
    
        
    // these are the variables that help computing
    static Random random = new Random();                            //for the method Random
    static int[] timesfield = new int[amount_of_errors];            //field of times when there will be a failure initiated
    static String[] errors = new String[types_of_erros];            //field of errors that can be selected out of when there is a failure
    
    
    
    //CONSTRUCTOR
    public MainBody()                                                       //to again begin the state of no-failure, timers are set and the first one started
    {
        t = new Timer(round_duration, this);
        t2 = new Timer(round_duration, this);
        t.start();
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //ACTIONLISTENER
    public void actionPerformed(ActionEvent i)                              //the actionlistener that controls the timers
    {
        if (i.getSource() == t)                                             //the no-failure timer
        {
            round++;                                                        //round is updated
            time = time + (round_duration/1000);                            //time is updated, it is done individually mainly for test purposes
            TIMETOFAIL = timeToFail(time);                                  //checks whether there is or is not time for a failure
            
            if (FAILURE == false)
            {
                if (TIMETOFAIL == false)                                    //if there's still not the right time for any failure
                {
                    everythingsOk();
                }
                
                else                                                        //if it is the "right time" for a failure, then one is generated here
                {
                    FAILURE = true;
                    t2.start();                                             //the second timer with for the failure scenario is started
                    t.stop();
                }
            }
            
            else
            {
                failure();
            }
        }
        
        
        if (i.getSource() == t2)                                            //the failure timer
        {
            round++;                                                        //round is updated
            time = time + (round_duration/1000);                            //time is updated, it is done individually mainly for test purposes
            
            if (FAILURE == true)                                            //if there still is a failure, move to the failure scenario method
            {
                failure();
            }
            
            else                                                            //if the command that was entered into terminal was correct
            {
                t.start();
                t2.stop();
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //AUXILIARY RANDOMIZERS:
    public static double randomInRange(double min, double max)              //an auxiliary method to randomize from doubles with limits
    {
      double range = max - min;
      double scaled = random.nextDouble() * range;
      double shifted = scaled + min;
      
      return shifted;                                                    
    }
    
    public static int randomInRangeInt(double min, double max)              //an auxiliary method to randomize from integers with limits
    {
      double range = max - min;
      double scaled = random.nextDouble() * range;
      double shifted = scaled + min;
      Long L = Math.round(shifted);
      int shiftedint = Integer.valueOf(L.intValue());
      
      return shiftedint;                                                  
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //SETTING THE RUN OF THE SIMULATION
    public static void setTimes()                                           //this method sets random times of errors conditioned by the user's requirements
    {
        int[] roundsfield = new int[amount_of_errors];                      //auxiliary field of rounds is created
        int mindifference = min_time_difference;
        int maxdifference = max_time_difference;
        int deltaround = randomInRangeInt(mindifference, maxdifference);
        roundsfield[0] = deltaround;
        
        for (int i = 1; i < amount_of_errors; i++)                          //firstly, the round of the errors are randomized and selected
        {
            deltaround = randomInRangeInt(mindifference, maxdifference);
            roundsfield[i] = roundsfield[i-1] + deltaround;
        }
        
        for (int i = 0; i < amount_of_errors; i++)                          //secondly, the times are assesed
        {
            timesfield[i] = roundsfield[i] * round_duration/1000;
        }
    }
    
    public static void setErrors()                                          //this method states which failures are available in the field of errors
    {
        errors[0] = "OXYGEN";
        errors[1] = "POT";
        errors[2] = "CCV";
        errors[3] = "LH2";
        errors[4] = "HeRIGHT";
        errors[5] = "HeCENTER";
        errors[6] = "HeLEFT";
        errors[7] = "POGO";
        errors[8] = "GPC1";
        errors[9] = "GPC235";
        errors[10]= "GPC1234";
        errors[11]= "GPC14";
        
    }
    
    public static boolean timeToFail(int timex)                             //this method evaluates whether there should be already another failure or not yet
    {
        
        boolean timetofail = false;
        for (int i = 0; i < amount_of_errors; i++)                          //the method compared the current time with the times in the field of error times
        {
            if (timex == timesfield[i])
            {
                timetofail = true;
            }
            
        }
        
        return timetofail;
    }
    
    public static String whichError()                                       //this method randomly picks up one of the errors in the field errors
    {
        int amountoferrors = errors.length;
        int whicherror = randomInRangeInt(0, (amountoferrors - 1));  
        
        return errors[whicherror];
        
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //FAILURE / NO-FAILURE SCENARIOS
    public void everythingsOk()                                             //should there be no failure, the no-failure environment does not change
    {
        sidebody.setVisible(true);
    }
    
    public void failure()
    {
        sidebody.setVisible(false);                                         //the no-failure environment is closed when there is a failure
        
        String error = whichError();                                        //a random error out of the field of errors is selected
        
        fsidebody = new SideBody(timesfield[errornumber], error);           //a new, failure environment with the selected error is created
        fsidebody.setVisible(true);
        
        terminal = new Terminal(error);                                     //a terminal window to enter the commands is launched
        terminal.setVisible(true);
        
        if (terminal.returnSubmit())                                        //if there was a command submitted, check its correctness and adjust the status
        {
            FAILURE = terminal.returnState();
        }

    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //DEFAULT
    public static void createSideBody()                                     //begins after the end of every failure, created new no-failure environment
    {
        sidebody = new SideBody(timesfield[errornumber]);
        sidebody.setVisible(true);
        mainbody = new MainBody();
    }
    
    public static void main(String[] args)                                  //initiated in the very beginning
    {
        setTimes();                                                         //the times of failures are set only once per simulation
        setErrors();                                                        //the types of errors are set only once per simulation 
        createSideBody();                                                   //the first initiation of the Sidebodies
    }
}
    
    
    