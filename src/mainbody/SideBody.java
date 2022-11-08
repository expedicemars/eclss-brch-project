/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainbody;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class SideBody extends MainBody implements ActionListener
{

    static int x = 1280;                                                     //if desired can be altered: the x size of the window
    static int y = 1024;                                                     //if desired can be altered: the y size of the window
    static String error;                                                    //to input and evaluate the error
    DecimalFormat df = new DecimalFormat("#.00");                           //if desired can be altered: how the numbers onscreen are rounded
    ///////////////////////////////////////////////////////////////////////
    
    String notice = "<html> Temperature [°C]:     19 - 21<br><br><br>pressure [psi]:     14 504<br><br><br>oxygen level [%]:     20.5 – 21.5<br><br><br>LPOT [rpm]:     5 150<br><br><br>LPOT [psia]:     100 - 422<br><br><br>HPOT [rpm]:     28 120<br><br><br>HPOT [psia]:     422 - 7420<br><br><br>LPFT [rpm]:     16 185<br><br><br>HPFT [rpm]:     35 360<br><br><br>CC Valve [%]:     64.5 - 100<br><br><br>POGO SS. [true / false]:     true<br><br><br>GPC status [1/ 0]:     1 1 1 1 1<br><br><br>Purge seq. [true/ false]:     false<br><br><br>LH2 [psia]:     28 - 60<br><br><br>He right [psia]: < 1 150<br><br><br>He center [psia]:     < 1 150<br><br><br>He left [psia]:     < 1 150<br><br><br>He right r. [psia]:     < 820<br><br><br>He center r. [psia]: < 820 <br><br><br>He left r. [psia]:    < 820<br><br><br>He right dP/dt:     < 29<br><br><br>He center dP/dt:     < 29 </html>";
    
    BufferedImage image;
    String path = "lod-01.png";
    File file = new File(path);{
    try{
    image = ImageIO.read(file);}
    catch(IOException ex){}}
    
    //THE NO FAILURE CONSTRUCTOR
    public SideBody(int timefirst)                                          //the no failure constructor
    {   
        this.setSize(x,y);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);
        
        //add by Jurda, for testing******************************
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        //****************************************************************
        
        s = new Timer(round_duration, this);                                //setting & starting a new timer s for refreshing of the variables; s does not affect time
        s.start();                                                          
        
        //setting what will be written on the screen
        jtemperature = new JLabel("Temperature:   \t" + df.format(temperature));
        jpressure = new JLabel("Pressure:      \t\t" + df.format(pressure));
        joxygen = new JLabel("Oxy. level:    \t\t" + df.format(oxygen));
        jlpotrpm  = new JLabel("LPOT [rpm]:    \t\t" + df.format(lpotrpm));
        jlpotpsia  = new JLabel("LPOT [psia]:   \t\t"+ df.format(lpotpsia));
        jhpotrpm  = new JLabel("HPOT [rpm]:    \t\t" + df.format(hpotrpm));
        jhpotpsia  = new JLabel("HPOT [psia]:   \t\t" + df.format(hpotpsia));
        jlpftrpm  = new JLabel("LPFT [rpm]:    \t\t" + df.format(lpftrpm));
        jhpftrpm  = new JLabel("HPFT [rpm]:    \t\t"+ df.format(hpftrpm));
        jcoolantcontrolvalve  = new JLabel("C.C. Valve:    \t\t" + df.format(coolantcontrolvalve));
        jpogo  = new JLabel("POGO SS.:      \t\t" + pogo);
        jGPCstatus  = new JLabel("GPC status:  \t\t" + GPCstatus);
        jpurgesequence  = new JLabel("Purge seq.:  \t\t" + purgesequence);
        jlh2psia  = new JLabel("LH2 [psia]:  \t\t" + df.format(lh2psia));
        jhepsiaright  = new JLabel("He right [psia]:    \t\t" + df.format(hepsiaright));
        jhepsiacenter  = new JLabel("He center [psia]:   \t\t" + df.format(hepsiacenter));
        jhepsialeft  = new JLabel("He left [psia]:     \t\t" + df.format(hepsialeft));
        jheregpsiaright  = new JLabel("He right r. [psia]:    \t\t" + df.format(heregpsiaright));
        jheregpsiacenter  = new JLabel("He center r. [psia]:   \t\t" + df.format(heregpsiacenter));
        jheregpsialeft  = new JLabel("He left r. [psia]:     \t\t" + df.format(heregpsialeft));
        jhedpdtright  = new JLabel("He right dP/dt:    \t\t" + df.format(hedpdtright));
        jhedpdtcenter  = new JLabel("He center dP/dt:   \t\t" + df.format(hedpdtcenter));
        jhedpdtleft = new JLabel("He left dP/dt:     \t\t" + df.format(hedpdtleft));
        jtime = new JLabel("Welcome to the ECLSS session.");
        jdate = new JLabel("Expedice Mars");
        jnotice = new JLabel(new ImageIcon(image));
            
        
        //getting everything actually on the screen
        this.getContentPane().add(jtemperature);
        this.getContentPane().add(jpressure);
        this.getContentPane().add(joxygen);
        this.getContentPane().add(jlpotrpm);
        this.getContentPane().add(jlpotpsia);
        this.getContentPane().add(jhpotrpm);
        this.getContentPane().add(jhpotpsia);
        this.getContentPane().add(jlpftrpm);
        this.getContentPane().add(jhpftrpm);
        this.getContentPane().add(jcoolantcontrolvalve);
        this.getContentPane().add(jpogo);
        this.getContentPane().add(jGPCstatus);
        this.getContentPane().add(jpurgesequence);
        this.getContentPane().add(jlh2psia);
        this.getContentPane().add(jhepsiaright);
        this.getContentPane().add(jhepsiacenter);
        this.getContentPane().add(jhepsialeft);
        this.getContentPane().add(jheregpsiaright);
        this.getContentPane().add(jheregpsiacenter);
        this.getContentPane().add(jheregpsialeft);
        this.getContentPane().add(jhedpdtright);
        this.getContentPane().add(jhedpdtcenter);
        this.getContentPane().add(jhedpdtleft);  
        this.getContentPane().add(jtime);
        this.getContentPane().add(jdate);
        this.getContentPane().add(jnotice);    

        
        //setting the colors, position and font of the text
        jtemperature.setBounds(50,50,300,50);
        jtemperature.setFont(new Font("monospaced", Font.PLAIN, 14));
        jtemperature.setForeground(Color.GREEN);
        
        jpressure.setBounds(50,100,300,50);
        jpressure.setFont(new Font("monospaced", Font.PLAIN, 14));
        jpressure.setForeground(Color.GREEN);
        
        joxygen.setBounds(50,150,300,50);
        joxygen.setFont(new Font("monospaced", Font.PLAIN, 14));
        joxygen.setForeground(Color.GREEN);
        
        jlpotrpm.setBounds(50,200,300,50);
        jlpotrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
        jlpotrpm.setForeground(Color.GREEN);
        
        jlpotpsia.setBounds(50,250,300,50);
        jlpotpsia.setFont(new Font("monospaced", Font.PLAIN, 14));
        jlpotpsia.setForeground(Color.GREEN);
        
        jhpotrpm.setBounds(50,300,300,50);
        jhpotrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhpotrpm.setForeground(Color.GREEN);
        
        jhpotpsia.setBounds(50,350,300,50);
        jhpotpsia.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhpotpsia.setForeground(Color.GREEN);
        
        jlpftrpm.setBounds(50,400,300,50);
        jlpftrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
        jlpftrpm.setForeground(Color.GREEN);
        
        jhpftrpm.setBounds(50,450,300,50);
        jhpftrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhpftrpm.setForeground(Color.GREEN);
        
        jcoolantcontrolvalve.setBounds(50,500,300,50);
        jcoolantcontrolvalve.setFont(new Font("monospaced", Font.PLAIN, 14));
        jcoolantcontrolvalve.setForeground(Color.GREEN);
        
        jpogo.setBounds(50,550,300,50);
        jpogo.setFont(new Font("monospaced", Font.PLAIN, 14));
        jpogo.setForeground(Color.GREEN);
        
        jGPCstatus.setBounds(350,50,300,50);
        jGPCstatus.setFont(new Font("monospaced", Font.PLAIN, 14));
        jGPCstatus.setForeground(Color.GREEN);
        
        jpurgesequence.setBounds(350,100,300,50);
        jpurgesequence.setFont(new Font("monospaced", Font.PLAIN, 14));
        jpurgesequence.setForeground(Color.GREEN);
        
        jlh2psia.setBounds(350,150,300,50);
        jlh2psia.setFont(new Font("monospaced", Font.PLAIN, 14));
        jlh2psia.setForeground(Color.GREEN);
        
        jhepsiaright.setBounds(350,200,300,50);
        jhepsiaright.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhepsiaright.setForeground(Color.GREEN);
        
        jhepsiacenter.setBounds(350,250,300,50);
        jhepsiacenter.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhepsiacenter.setForeground(Color.GREEN);
        
        jhepsialeft.setBounds(350,300,300,50);
        jhepsialeft.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhepsialeft.setForeground(Color.GREEN);
        
        jheregpsiaright.setBounds(350,350,300,50);
        jheregpsiaright.setFont(new Font("monospaced", Font.PLAIN, 14));
        jheregpsiaright.setForeground(Color.GREEN);
        
        jheregpsiacenter.setBounds(350,400,300,50);
        jheregpsiacenter.setFont(new Font("monospaced", Font.PLAIN, 14));
        jheregpsiacenter.setForeground(Color.GREEN);
        
        jheregpsialeft.setBounds(350,450,300,50);
        jheregpsialeft.setFont(new Font("monospaced", Font.PLAIN, 14));
        jheregpsialeft.setForeground(Color.GREEN);
        
        jhedpdtright.setBounds(350,500,300,50);
        jhedpdtright.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhedpdtright.setForeground(Color.GREEN);
        
        jhedpdtcenter.setBounds(350,550,300,50);
        jhedpdtcenter.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhedpdtcenter.setForeground(Color.GREEN);
        
        jhedpdtleft.setBounds(350,600,300,50);
        jhedpdtleft.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhedpdtleft.setForeground(Color.GREEN);
        
        jtime.setBounds(10,10,600,20);
        jtime.setFont(new Font("monospaced", Font.PLAIN, 12));
        jtime.setForeground(Color.GREEN);
        
        jdate.setBounds(600,10,100,20);
        jdate.setFont(new Font("monospaced", Font.PLAIN, 12));
        jdate.setForeground(Color.GREEN);
        
        jnotice.setBounds(700,10,564,682);
        jnotice.setFont(new Font("monospaced", Font.PLAIN, 12));
        jnotice.setForeground(Color.WHITE);
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //THE FAILURE CONSTRUCTOR
    public SideBody(int timesecond, String theerror)
    {
        this.setSize(x,y);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);
        
        error = theerror;
        f = new Timer(round_duration, this);                                //Starting & setting new timer for the failure scenario
        f.start();  
        
        jtemperature = new JLabel("Temperature:   \t" + df.format(temperature));
        jpressure = new JLabel("Pressure:      \t\t" + df.format(pressure));
        joxygen = new JLabel("Oxy. level:    \t\t" + df.format(oxygen));
        jlpotrpm  = new JLabel("LPOT [rpm]:    \t\t" + df.format(lpotrpm));
        jlpotpsia  = new JLabel("LPOT [psia]:   \t\t"+ df.format(lpotpsia));
        jhpotrpm  = new JLabel("HPOT [rpm]:    \t\t" + df.format(hpotrpm));
        jhpotpsia  = new JLabel("HPOT [psia]:   \t\t" + df.format(hpotpsia));
        jlpftrpm  = new JLabel("LPFT [rpm]:    \t\t" + df.format(lpftrpm));
        jhpftrpm  = new JLabel("HPFT [rpm]:    \t\t"+ df.format(hpftrpm));
        jcoolantcontrolvalve  = new JLabel("C.C. Valve:    \t\t" + df.format(coolantcontrolvalve));
        jpogo  = new JLabel("POGO SS.:      \t\t" + pogo);
        jGPCstatus  = new JLabel("GPC status:  \t\t" + GPCstatus);
        jpurgesequence  = new JLabel("Purge seq.:  \t\t" + purgesequence);
        jlh2psia  = new JLabel("LH2 [psia]:  \t\t" + df.format(lh2psia));
        jhepsiaright  = new JLabel("He right [psia]:    \t\t" + df.format(hepsiaright));
        jhepsiacenter  = new JLabel("He center [psia]:   \t\t" + df.format(hepsiacenter));
        jhepsialeft  = new JLabel("He left [psia]:     \t\t" + df.format(hepsialeft));
        jheregpsiaright  = new JLabel("He right r. [psia]:    \t\t" + df.format(heregpsiaright));
        jheregpsiacenter  = new JLabel("He center r. [psia]:   \t\t" + df.format(heregpsiacenter));
        jheregpsialeft  = new JLabel("He left r. [psia]:     \t\t" + df.format(heregpsialeft));
        jhedpdtright  = new JLabel("He right dP/dt:    \t\t" + df.format(hedpdtright));
        jhedpdtcenter  = new JLabel("He center dP/dt:   \t\t" + df.format(hedpdtcenter));
        jhedpdtleft = new JLabel("He left dP/dt:     \t\t" + df.format(hedpdtleft));
        jtime = new JLabel("Welcome to the ECLSS session.");
        jdate = new JLabel("Expedice Mars");
        jnotice = new JLabel(new ImageIcon(image));
            
        
        this.getContentPane().add(jtemperature);
        this.getContentPane().add(jpressure);
        this.getContentPane().add(joxygen);
        this.getContentPane().add(jlpotrpm);
        this.getContentPane().add(jlpotpsia);
        this.getContentPane().add(jhpotrpm);
        this.getContentPane().add(jhpotpsia);
        this.getContentPane().add(jlpftrpm);
        this.getContentPane().add(jhpftrpm);
        this.getContentPane().add(jcoolantcontrolvalve);
        this.getContentPane().add(jpogo);
        this.getContentPane().add(jGPCstatus);
        this.getContentPane().add(jpurgesequence);
        this.getContentPane().add(jlh2psia);
        this.getContentPane().add(jhepsiaright);
        this.getContentPane().add(jhepsiacenter);
        this.getContentPane().add(jhepsialeft);
        this.getContentPane().add(jheregpsiaright);
        this.getContentPane().add(jheregpsiacenter);
        this.getContentPane().add(jheregpsialeft);
        this.getContentPane().add(jhedpdtright);
        this.getContentPane().add(jhedpdtcenter);
        this.getContentPane().add(jhedpdtleft);        
        this.getContentPane().add(jtime);
        this.getContentPane().add(jdate);
        this.getContentPane().add(jnotice);    
        
        
        jtemperature.setBounds(50,50,300,50);
        jtemperature.setFont(new Font("monospaced", Font.PLAIN, 14));
        jtemperature.setForeground(Color.GREEN);
        
        jpressure.setBounds(50,100,300,50);
        jpressure.setFont(new Font("monospaced", Font.PLAIN, 14));
        jpressure.setForeground(Color.GREEN);
        
        joxygen.setBounds(50,150,300,50);
        joxygen.setFont(new Font("monospaced", Font.PLAIN, 14));
        joxygen.setForeground(Color.GREEN);
        
        jlpotrpm.setBounds(50,200,300,50);
        jlpotrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
        jlpotrpm.setForeground(Color.GREEN);
        
        jlpotpsia.setBounds(50,250,300,50);
        jlpotpsia.setFont(new Font("monospaced", Font.PLAIN, 14));
        jlpotpsia.setForeground(Color.GREEN);
        
        jhpotrpm.setBounds(50,300,300,50);
        jhpotrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhpotrpm.setForeground(Color.GREEN);
        
        jhpotpsia.setBounds(50,350,300,50);
        jhpotpsia.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhpotpsia.setForeground(Color.GREEN);
        
        jlpftrpm.setBounds(50,400,300,50);
        jlpftrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
        jlpftrpm.setForeground(Color.GREEN);
        
        jhpftrpm.setBounds(50,450,300,50);
        jhpftrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhpftrpm.setForeground(Color.GREEN);
        
        jcoolantcontrolvalve.setBounds(50,500,300,50);
        jcoolantcontrolvalve.setFont(new Font("monospaced", Font.PLAIN, 14));
        jcoolantcontrolvalve.setForeground(Color.GREEN);
        
        jpogo.setBounds(50,550,300,50);
        jpogo.setFont(new Font("monospaced", Font.PLAIN, 14));
        jpogo.setForeground(Color.GREEN);
        
        jGPCstatus.setBounds(350,50,300,50);
        jGPCstatus.setFont(new Font("monospaced", Font.PLAIN, 14));
        jGPCstatus.setForeground(Color.GREEN);
        
        jpurgesequence.setBounds(350,100,300,50);
        jpurgesequence.setFont(new Font("monospaced", Font.PLAIN, 14));
        jpurgesequence.setForeground(Color.GREEN);
        
        jlh2psia.setBounds(350,150,300,50);
        jlh2psia.setFont(new Font("monospaced", Font.PLAIN, 14));
        jlh2psia.setForeground(Color.GREEN);
        
        jhepsiaright.setBounds(350,200,300,50);
        jhepsiaright.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhepsiaright.setForeground(Color.GREEN);
        
        jhepsiacenter.setBounds(350,250,300,50);
        jhepsiacenter.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhepsiacenter.setForeground(Color.GREEN);
        
        jhepsialeft.setBounds(350,300,300,50);
        jhepsialeft.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhepsialeft.setForeground(Color.GREEN);
        
        jheregpsiaright.setBounds(350,350,300,50);
        jheregpsiaright.setFont(new Font("monospaced", Font.PLAIN, 14));
        jheregpsiaright.setForeground(Color.GREEN);
        
        jheregpsiacenter.setBounds(350,400,300,50);
        jheregpsiacenter.setFont(new Font("monospaced", Font.PLAIN, 14));
        jheregpsiacenter.setForeground(Color.GREEN);
        
        jheregpsialeft.setBounds(350,450,300,50);
        jheregpsialeft.setFont(new Font("monospaced", Font.PLAIN, 14));
        jheregpsialeft.setForeground(Color.GREEN);
        
        jhedpdtright.setBounds(350,500,300,50);
        jhedpdtright.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhedpdtright.setForeground(Color.GREEN);
        
        jhedpdtcenter.setBounds(350,550,300,50);
        jhedpdtcenter.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhedpdtcenter.setForeground(Color.GREEN);
        
        jhedpdtleft.setBounds(350,600,300,50);
        jhedpdtleft.setFont(new Font("monospaced", Font.PLAIN, 14));
        jhedpdtleft.setForeground(Color.GREEN);
        
        jtime.setBounds(10,10,600,20);
        jtime.setFont(new Font("monospaced", Font.PLAIN, 12));
        jtime.setForeground(Color.GREEN);
        
        jdate.setBounds(600,10,100,20);
        jdate.setFont(new Font("monospaced", Font.PLAIN, 12));
        jdate.setForeground(Color.GREEN);
        
        jnotice.setBounds(700,10,564,682);
        jnotice.setFont(new Font("monospaced", Font.PLAIN, 12));
        jnotice.setForeground(Color.WHITE);
        
        //setting the changes in the color of text when there is a certain type of error
        if (error == "OXYGEN")
        {
            joxygen.setForeground(Color.RED);
        }
        
        if (error == "POT")
        {
           jlpotpsia.setForeground(Color.RED);
           jhpotpsia.setForeground(Color.RED);
        }
        
        if (error == "CCV")
        {
            jcoolantcontrolvalve.setForeground(Color.RED);
        }
        
        if (error == "LH2")
        {
            jlh2psia.setForeground(Color.RED);
        }
        
        if (error == "HeRIGHT")
        {
            jhepsiaright.setForeground(Color.RED);
            jheregpsiaright.setForeground(Color.RED);
            jhedpdtright.setForeground(Color.RED);
        }
        
        if (error == "HeCENTER")
        {
            jhepsiacenter.setForeground(Color.RED);
            jheregpsiacenter.setForeground(Color.RED);
            jhedpdtcenter.setForeground(Color.RED);
        }
        
        if (error == "HeLEFT")
        {
            jhepsialeft.setForeground(Color.RED);
            jheregpsialeft.setForeground(Color.RED);
            jhedpdtleft.setForeground(Color.RED);
        }
        
        if (error == "POGO")
        {
            jpogo.setForeground(Color.RED);
        }
            
        if (error == "GPC1")
        {
            jGPCstatus.setForeground(Color.RED);
        }
        
        if (error == "GPC235")
        {
            jGPCstatus.setForeground(Color.RED);
        }
        
        if (error == "GPC1234")
        {
            jGPCstatus.setForeground(Color.RED);
        }
        
        if (error == "GPC14")
        {
            jGPCstatus.setForeground(Color.RED);
        } 
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //ACTIONLISTENER
    public void actionPerformed(ActionEvent i)
    {
        if(i.getSource() == s)
        {
            sidebody.getContentPane().removeAll();                          //to refresh the variables, the old ones are removed
            
            temperature = randomInRange(19.9, 20.1);
            oxygen = randomInRange(21, 21.05);
            throttle = randomInRange(66, 68);
            lpotpsia = randomInRange(366, 422);
            hpotpsia = randomInRange(4000, 5000);
            coolantcontrolvalve = randomInRange(65, 100);
            lh2psia = randomInRange(30, 50);
            hepsiaright = randomInRange(0, 1150);
            hepsiacenter = randomInRange(0, 1150);
            hepsialeft = randomInRange(0, 1150);
            heregpsiaright = hepsiaright / 1.4;
            heregpsiacenter = hepsiacenter / 1.4;
            heregpsialeft = hepsialeft / 1.4;
            hedpdtright = heregpsiaright / 29;
            hedpdtcenter = heregpsiacenter / 29;
            hedpdtleft = heregpsialeft / 29;
            pogo = true;
            GPCstatus = "1 1 1 1 1";
            
            TIMETOFAIL = timeToFail(time);                                  //evaluating whether it is not the time for any failure
            if (TIMETOFAIL == false)                                        //if it is not the time for a fialure, refresh the values
            {
                FAILURE = false;
                Body();
            }
            
            else                                                            //if it is time for a failure, stop this timer 
            {
                FAILURE = true;
                Body();
                s.stop();
            }
        }
        
        if (i.getSource() == f)
        {
            fsidebody.getContentPane().removeAll();                         //to refresh the variables, old ones are removed
            
            temperature = randomInRange(19.9, 20.1);
            if (error != "OXYGEN")                                          //since oxygen is meant to be decreasing if it fails, this condition must take place here
            {
                oxygen = randomInRange(21, 21.05);                          //because otherwise it would always be randomized and hence not decreased
            }
            
            throttle = randomInRange(66, 68);
            lpotpsia = randomInRange(366, 422);
            hpotpsia = randomInRange(4000, 5000);
            coolantcontrolvalve = randomInRange(65, 100);
            lh2psia = randomInRange(30, 50);
            hepsiaright = randomInRange(0, 1150);
            hepsiacenter = randomInRange(0, 1150);
            hepsialeft = randomInRange(0, 1150);
            heregpsiaright = hepsiaright / 1.4;
            heregpsiacenter = hepsiacenter / 1.4;
            heregpsialeft = hepsialeft / 1.4;
            hedpdtright = heregpsiaright / 29;
            hedpdtcenter = heregpsiacenter / 29;
            hedpdtleft = heregpsialeft / 29;
            pogo = true;
            GPCstatus = "1 1 1 1 1";
            
            
            //setting the new values for each type of error:
            if (error == "OXYGEN")
            {
                oxygen = oxygen - 1;                                        //decreasing of the oxygen in between the rounds
            }
            
            if (error == "POT")
            {
               lpotpsia = randomInRange(423, 505);
               hpotpsia = randomInRange(5001, 6000); 
            }
            
            if (error == "CCV")
            {
                coolantcontrolvalve = 0;
            }
            
            if (error == "LH2")
            {
                lh2psia = randomInRange(1, 19);
            }
            
            if (error == "HeRIGHT")
            {
                hepsiaright = randomInRange(1151,2000);
                heregpsiaright = hepsiaright / 1.4;
                hedpdtright =  heregpsiaright / 29;
            }
            
            if (error == "HeCENTER")
            {
                hepsiacenter = randomInRange(1151,2000);
                heregpsiacenter = hepsiacenter / 1.4;
                hedpdtcenter =  heregpsiacenter / 29;
            }
            
            if (error == "HeLEFT")
            {
                hepsialeft = randomInRange(1151,2000);
                heregpsialeft = hepsialeft / 1.4;
                hedpdtleft =  heregpsialeft / 29;
            }
            
            if (error == "POGO")
            {
                pogo = false;
            }
            
            if (error == "GPC1")
            {
                GPCstatus = "0 1 1 1 1";
            }
            
            if (error == "GPC235")
            {
                GPCstatus = "1 0 0 1 0";
            }
            
            if (error == "GPC1234")
            {
                GPCstatus = "0 0 0 0 1";
            }
            
            if (error == "GPC14")
            {
                GPCstatus = "0 1 1 0 1";
            }

            Body();
        }
        
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //THE DEFAULT SCREEN
    public void Body()
    {
        if (FAILURE == false)                                               //Screen when there is no failure
        {
            jtemperature = new JLabel("Temperature:   \t" + df.format(temperature));
            jpressure = new JLabel("Pressure:      \t\t" + df.format(pressure));
            joxygen = new JLabel("Oxy. level:    \t\t" + df.format(oxygen));
            jlpotrpm  = new JLabel("LPOT [rpm]:    \t\t" + df.format(lpotrpm));
            jlpotpsia  = new JLabel("LPOT [psia]:   \t\t"+ df.format(lpotpsia));
            jhpotrpm  = new JLabel("HPOT [rpm]:    \t\t" + df.format(hpotrpm));
            jhpotpsia  = new JLabel("HPOT [psia]:   \t\t" + df.format(hpotpsia));
            jlpftrpm  = new JLabel("LPFT [rpm]:    \t\t" + df.format(lpftrpm));
            jhpftrpm  = new JLabel("HPFT [rpm]:    \t\t"+ df.format(hpftrpm));
            jcoolantcontrolvalve  = new JLabel("C.C. Valve:    \t\t" + df.format(coolantcontrolvalve));
            jpogo  = new JLabel("POGO SS.:      \t\t" + pogo);
            jGPCstatus  = new JLabel("GPC status:  \t\t" + GPCstatus);
            jpurgesequence  = new JLabel("Purge seq.:  \t\t" + purgesequence);
            jlh2psia  = new JLabel("LH2 [psia]:  \t\t" + df.format(lh2psia));
            jhepsiaright  = new JLabel("He right [psia]:    \t\t" + df.format(hepsiaright));
            jhepsiacenter  = new JLabel("He center [psia]:   \t\t" + df.format(hepsiacenter));
            jhepsialeft  = new JLabel("He left [psia]:     \t\t" + df.format(hepsialeft));
            jheregpsiaright  = new JLabel("He right r. [psia]:    \t\t" + df.format(heregpsiaright));
            jheregpsiacenter  = new JLabel("He center r. [psia]:   \t\t" + df.format(heregpsiacenter));
            jheregpsialeft  = new JLabel("He left r. [psia]:     \t\t" + df.format(heregpsialeft));
            jhedpdtright  = new JLabel("He right dP/dt:    \t\t" + df.format(hedpdtright));
            jhedpdtcenter  = new JLabel("He center dP/dt:   \t\t" + df.format(hedpdtcenter));
            jhedpdtleft = new JLabel("He left dP/dt:     \t\t" + df.format(hedpdtleft));
            jtime = new JLabel("Welcome to the ECLSS session.");
            jdate = new JLabel("Expedice Mars");
            jnotice = new JLabel(new ImageIcon(image));

            
            this.getContentPane().add(jtemperature);
            this.getContentPane().add(jpressure);
            this.getContentPane().add(joxygen);
            this.getContentPane().add(jlpotrpm);
            this.getContentPane().add(jlpotpsia);
            this.getContentPane().add(jhpotrpm);
            this.getContentPane().add(jhpotpsia);
            this.getContentPane().add(jlpftrpm);
            this.getContentPane().add(jhpftrpm);
            this.getContentPane().add(jcoolantcontrolvalve);
            this.getContentPane().add(jpogo);
            this.getContentPane().add(jGPCstatus);
            this.getContentPane().add(jpurgesequence);
            this.getContentPane().add(jlh2psia);
            this.getContentPane().add(jhepsiaright);
            this.getContentPane().add(jhepsiacenter);
            this.getContentPane().add(jhepsialeft);
            this.getContentPane().add(jheregpsiaright);
            this.getContentPane().add(jheregpsiacenter);
            this.getContentPane().add(jheregpsialeft);
            this.getContentPane().add(jhedpdtright);
            this.getContentPane().add(jhedpdtcenter);
            this.getContentPane().add(jhedpdtleft); 
            this.getContentPane().add(jtime);
            this.getContentPane().add(jdate);
            this.getContentPane().add(jnotice);    

            
            jtemperature.setBounds(50,50,300,50);
            jtemperature.setFont(new Font("monospaced", Font.PLAIN, 14));
            jtemperature.setForeground(Color.GREEN);
            
            jpressure.setBounds(50,100,300,50);
            jpressure.setFont(new Font("monospaced", Font.PLAIN, 14));
            jpressure.setForeground(Color.GREEN);
            
            joxygen.setBounds(50,150,300,50);
            joxygen.setFont(new Font("monospaced", Font.PLAIN, 14));
            joxygen.setForeground(Color.GREEN);
            
            jlpotrpm.setBounds(50,200,300,50);
            jlpotrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
            jlpotrpm.setForeground(Color.GREEN);
            
            jlpotpsia.setBounds(50,250,300,50);
            jlpotpsia.setFont(new Font("monospaced", Font.PLAIN, 14));
            jlpotpsia.setForeground(Color.GREEN);
            
            jhpotrpm.setBounds(50,300,300,50);
            jhpotrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhpotrpm.setForeground(Color.GREEN);
            
            jhpotpsia.setBounds(50,350,300,50);
            jhpotpsia.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhpotpsia.setForeground(Color.GREEN);
            
            jlpftrpm.setBounds(50,400,300,50);
            jlpftrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
            jlpftrpm.setForeground(Color.GREEN);
            
            jhpftrpm.setBounds(50,450,300,50);
            jhpftrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhpftrpm.setForeground(Color.GREEN);
            
            jcoolantcontrolvalve.setBounds(50,500,300,50);
            jcoolantcontrolvalve.setFont(new Font("monospaced", Font.PLAIN, 14));
            jcoolantcontrolvalve.setForeground(Color.GREEN);
            
            jpogo.setBounds(50,550,300,50);
            jpogo.setFont(new Font("monospaced", Font.PLAIN, 14));
            jpogo.setForeground(Color.GREEN);
            
            jGPCstatus.setBounds(350,50,300,50);
            jGPCstatus.setFont(new Font("monospaced", Font.PLAIN, 14));
            jGPCstatus.setForeground(Color.GREEN);
            
            jpurgesequence.setBounds(350,100,300,50);
            jpurgesequence.setFont(new Font("monospaced", Font.PLAIN, 14));
            jpurgesequence.setForeground(Color.GREEN);
            
            jlh2psia.setBounds(350,150,300,50);
            jlh2psia.setFont(new Font("monospaced", Font.PLAIN, 14));
            jlh2psia.setForeground(Color.GREEN);
            
            jhepsiaright.setBounds(350,200,300,50);
            jhepsiaright.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhepsiaright.setForeground(Color.GREEN);
            
            jhepsiacenter.setBounds(350,250,300,50);
            jhepsiacenter.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhepsiacenter.setForeground(Color.GREEN);
            
            jhepsialeft.setBounds(350,300,300,50);
            jhepsialeft.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhepsialeft.setForeground(Color.GREEN);
            
            jheregpsiaright.setBounds(350,350,300,50);
            jheregpsiaright.setFont(new Font("monospaced", Font.PLAIN, 14));
            jheregpsiaright.setForeground(Color.GREEN);
            
            jheregpsiacenter.setBounds(350,400,300,50);
            jheregpsiacenter.setFont(new Font("monospaced", Font.PLAIN, 14));
            jheregpsiacenter.setForeground(Color.GREEN);
            
            jheregpsialeft.setBounds(350,450,300,50);
            jheregpsialeft.setFont(new Font("monospaced", Font.PLAIN, 14));
            jheregpsialeft.setForeground(Color.GREEN);
            
            jhedpdtright.setBounds(350,500,300,50);
            jhedpdtright.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhedpdtright.setForeground(Color.GREEN);
            
            jhedpdtcenter.setBounds(350,550,300,50);
            jhedpdtcenter.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhedpdtcenter.setForeground(Color.GREEN);
            
            jhedpdtleft.setBounds(350,600,300,50);
            jhedpdtleft.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhedpdtleft.setForeground(Color.GREEN);
            
            jtime.setBounds(10,10,600,20);
            jtime.setFont(new Font("monospaced", Font.PLAIN, 12));
            jtime.setForeground(Color.GREEN);
            
            jdate.setBounds(600,10,100,20);
            jdate.setFont(new Font("monospaced", Font.PLAIN, 12));
            jdate.setForeground(Color.GREEN);
            
            jnotice.setBounds(700,10,564,682);
            jnotice.setFont(new Font("monospaced", Font.PLAIN, 12));
            jnotice.setForeground(Color.WHITE);
        }
   
    
        if (FAILURE)                                                        //and the screen when there is a failure
        {
            jtemperature = new JLabel("Temperature:   \t" + df.format(temperature));
            jpressure = new JLabel("Pressure:      \t\t" + df.format(pressure));
            joxygen = new JLabel("Oxy. level:    \t\t" + df.format(oxygen));
            jlpotrpm  = new JLabel("LPOT [rpm]:    \t\t" + df.format(lpotrpm));
            jlpotpsia  = new JLabel("LPOT [psia]:   \t\t"+ df.format(lpotpsia));
            jhpotrpm  = new JLabel("HPOT [rpm]:    \t\t" + df.format(hpotrpm));
            jhpotpsia  = new JLabel("HPOT [psia]:   \t\t" + df.format(hpotpsia));
            jlpftrpm  = new JLabel("LPFT [rpm]:    \t\t" + df.format(lpftrpm));
            jhpftrpm  = new JLabel("HPFT [rpm]:    \t\t"+ df.format(hpftrpm));
            jcoolantcontrolvalve  = new JLabel("C.C. Valve:    \t\t" + df.format(coolantcontrolvalve));
            jpogo  = new JLabel("POGO SS.:      \t\t" + pogo);
            jGPCstatus  = new JLabel("GPC status:  \t\t" + GPCstatus);
            jpurgesequence  = new JLabel("Purge seq.:  \t\t" + purgesequence);
            jlh2psia  = new JLabel("LH2 [psia]:  \t\t" + df.format(lh2psia));
            jhepsiaright  = new JLabel("He right [psia]:    \t\t" + df.format(hepsiaright));
            jhepsiacenter  = new JLabel("He center [psia]:   \t\t" + df.format(hepsiacenter));
            jhepsialeft  = new JLabel("He left [psia]:     \t\t" + df.format(hepsialeft));
            jheregpsiaright  = new JLabel("He right r. [psia]:    \t\t" + df.format(heregpsiaright));
            jheregpsiacenter  = new JLabel("He center r. [psia]:   \t\t" + df.format(heregpsiacenter));
            jheregpsialeft  = new JLabel("He left r. [psia]:     \t\t" + df.format(heregpsialeft));
            jhedpdtright  = new JLabel("He right dP/dt:    \t\t" + df.format(hedpdtright));
            jhedpdtcenter  = new JLabel("He center dP/dt:   \t\t" + df.format(hedpdtcenter));
            jhedpdtleft = new JLabel("He left dP/dt:     \t\t" + df.format(hedpdtleft));
            jtime = new JLabel("Welcome to the ECLSS session.");
            jdate = new JLabel("January 2016");
            jnotice = new JLabel(new ImageIcon(image));
                
            
            this.getContentPane().add(jtemperature);
            this.getContentPane().add(jpressure);
            this.getContentPane().add(joxygen);
            this.getContentPane().add(jlpotrpm);
            this.getContentPane().add(jlpotpsia);
            this.getContentPane().add(jhpotrpm);
            this.getContentPane().add(jhpotpsia);
            this.getContentPane().add(jlpftrpm);
            this.getContentPane().add(jhpftrpm);
            this.getContentPane().add(jcoolantcontrolvalve);
            this.getContentPane().add(jpogo);
            this.getContentPane().add(jGPCstatus);
            this.getContentPane().add(jpurgesequence);
            this.getContentPane().add(jlh2psia);
            this.getContentPane().add(jhepsiaright);
            this.getContentPane().add(jhepsiacenter);
            this.getContentPane().add(jhepsialeft);
            this.getContentPane().add(jheregpsiaright);
            this.getContentPane().add(jheregpsiacenter);
            this.getContentPane().add(jheregpsialeft);
            this.getContentPane().add(jhedpdtright);
            this.getContentPane().add(jhedpdtcenter);
            this.getContentPane().add(jhedpdtleft); 
            this.getContentPane().add(jtime);
            this.getContentPane().add(jdate);
            this.getContentPane().add(jnotice);    
            
            
            jtemperature.setBounds(50,50,300,50);
            jtemperature.setFont(new Font("monospaced", Font.PLAIN, 14));
            jtemperature.setForeground(Color.GREEN);
            
            jpressure.setBounds(50,100,300,50);
            jpressure.setFont(new Font("monospaced", Font.PLAIN, 14));
            jpressure.setForeground(Color.GREEN);
            
            joxygen.setBounds(50,150,300,50);
            joxygen.setFont(new Font("monospaced", Font.PLAIN, 14));
            joxygen.setForeground(Color.GREEN);
            
            jlpotrpm.setBounds(50,200,300,50);
            jlpotrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
            jlpotrpm.setForeground(Color.GREEN);
            
            jlpotpsia.setBounds(50,250,300,50);
            jlpotpsia.setFont(new Font("monospaced", Font.PLAIN, 14));
            jlpotpsia.setForeground(Color.GREEN);
            
            jhpotrpm.setBounds(50,300,300,50);
            jhpotrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhpotrpm.setForeground(Color.GREEN);
            
            jhpotpsia.setBounds(50,350,300,50);
            jhpotpsia.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhpotpsia.setForeground(Color.GREEN);
            
            jlpftrpm.setBounds(50,400,300,50);
            jlpftrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
            jlpftrpm.setForeground(Color.GREEN);
            
            jhpftrpm.setBounds(50,450,300,50);
            jhpftrpm.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhpftrpm.setForeground(Color.GREEN);
            
            jcoolantcontrolvalve.setBounds(50,500,300,50);
            jcoolantcontrolvalve.setFont(new Font("monospaced", Font.PLAIN, 14));
            jcoolantcontrolvalve.setForeground(Color.GREEN);
            
            jpogo.setBounds(50,550,300,50);
            jpogo.setFont(new Font("monospaced", Font.PLAIN, 14));
            jpogo.setForeground(Color.GREEN);
            
            jGPCstatus.setBounds(350,50,300,50);
            jGPCstatus.setFont(new Font("monospaced", Font.PLAIN, 14));
            jGPCstatus.setForeground(Color.GREEN);
            
            jpurgesequence.setBounds(350,100,300,50);
            jpurgesequence.setFont(new Font("monospaced", Font.PLAIN, 14));
            jpurgesequence.setForeground(Color.GREEN);
            
            jlh2psia.setBounds(350,150,300,50);
            jlh2psia.setFont(new Font("monospaced", Font.PLAIN, 14));
            jlh2psia.setForeground(Color.GREEN);
            
            jhepsiaright.setBounds(350,200,300,50);
            jhepsiaright.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhepsiaright.setForeground(Color.GREEN);
            
            jhepsiacenter.setBounds(350,250,300,50);
            jhepsiacenter.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhepsiacenter.setForeground(Color.GREEN);
            
            jhepsialeft.setBounds(350,300,300,50);
            jhepsialeft.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhepsialeft.setForeground(Color.GREEN);
            
            jheregpsiaright.setBounds(350,350,300,50);
            jheregpsiaright.setFont(new Font("monospaced", Font.PLAIN, 14));
            jheregpsiaright.setForeground(Color.GREEN);
            
            jheregpsiacenter.setBounds(350,400,300,50);
            jheregpsiacenter.setFont(new Font("monospaced", Font.PLAIN, 14));
            jheregpsiacenter.setForeground(Color.GREEN);
            
            jheregpsialeft.setBounds(350,450,300,50);
            jheregpsialeft.setFont(new Font("monospaced", Font.PLAIN, 14));
            jheregpsialeft.setForeground(Color.GREEN);
            
            jhedpdtright.setBounds(350,500,300,50);
            jhedpdtright.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhedpdtright.setForeground(Color.GREEN);
            
            jhedpdtcenter.setBounds(350,550,300,50);
            jhedpdtcenter.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhedpdtcenter.setForeground(Color.GREEN);
            
            jhedpdtleft.setBounds(350,600,300,50);
            jhedpdtleft.setFont(new Font("monospaced", Font.PLAIN, 14));
            jhedpdtleft.setForeground(Color.GREEN);
            
            jtime.setBounds(10,10,600,20);
            jtime.setFont(new Font("monospaced", Font.PLAIN, 12));
            jtime.setForeground(Color.GREEN);
            
            jdate.setBounds(600,10,100,20);
            jdate.setFont(new Font("monospaced", Font.PLAIN, 12));
            jdate.setForeground(Color.GREEN);
            
            jnotice.setBounds(700,10,564,682);
            jnotice.setFont(new Font("monospaced", Font.PLAIN, 12));
            jnotice.setForeground(Color.WHITE);
            
            
            if (error == "OXYGEN")
            {
                joxygen.setForeground(Color.RED);
            }
            
            if (error == "POT")
            {
               jlpotpsia.setForeground(Color.RED);
               jhpotpsia.setForeground(Color.RED);
            }
            
            if (error == "CCV")
            {
                jcoolantcontrolvalve.setForeground(Color.RED);
            }
            
            if (error == "LH2")
            {
                jlh2psia.setForeground(Color.RED);
            }
            
            if (error == "HeRIGHT")
            {
                jhepsiaright.setForeground(Color.RED);
                jheregpsiaright.setForeground(Color.RED);
                jhedpdtright.setForeground(Color.RED);
            }
            
            if (error == "HeCENTER")
            {
                jhepsiacenter.setForeground(Color.RED);
                jheregpsiacenter.setForeground(Color.RED);
                jhedpdtcenter.setForeground(Color.RED);
            }
            
            if (error == "HeLEFT")
            {
                jhepsialeft.setForeground(Color.RED);
                jheregpsialeft.setForeground(Color.RED);
                jhedpdtleft.setForeground(Color.RED);
            }
            
            if (error == "POGO")
            {
                jpogo.setForeground(Color.RED);
            }
            
            if (error == "GPC1")
            {
                jGPCstatus.setForeground(Color.RED);
            }
            
            if (error == "GPC235")
            {
                jGPCstatus.setForeground(Color.RED);
            }
            
            if (error == "GPC1234")
            {
                jGPCstatus.setForeground(Color.RED);
            }
            
            if (error == "GPC14")
            {
                jGPCstatus.setForeground(Color.RED);
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //DEFAULT IF NECESSARY IN THE FUTURE
        public static void main()
    {
        
        
    }
}

