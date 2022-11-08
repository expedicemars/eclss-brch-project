/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainbody;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Terminal extends MainBody implements ActionListener
{
    //Computation variables
    static String error;                                                    //the input error for which the correct command should be determined
    static String keycommand;                                               //the correct command
    static boolean submit = false;                                          //indicates that there has been no command entered yet
    
    //GUI variables
    JButton jbutton = new JButton("OK");                                    //if desired can be altered: the text on the buttion
    JTextField jtextfield = new JTextField("");                             //if desired can be altered: the initial text in the command text field 
    JLabel jlabel;
    ///////////////////////////////////////////////////////////////////////
    
    
    //THE CONSTRUCTOR
    public Terminal(String theerror)                                        //identifies the input error and created the body of the terminal
    {
        error = theerror;
        
        this.setSize(400,230);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);
        
        terminalBody();
        
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //THE TERMINAL GUI
    public void terminalBody()                                              //the GUI of the terminal
    {
        this.getContentPane().removeAll();
        this.setSize(400,230);                                              //if desired can be altered: the size of the window
        this.setLayout(null);
        this.getContentPane().setBackground(Color.black);
        this.setVisible(true);
        
        //add by Jurda, for testing******************************
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        //**************************************************
        
        JLabel jlabel = new JLabel("MALFUNCTION DETECTED!");                //if desired can be altered: the text that will be shown 

        this.getContentPane().add(jlabel);
        this.getContentPane().add(jbutton);
        this.getContentPane().add(jtextfield);
        jbutton.addActionListener(this);
     
        jlabel.setBounds(10,10,380,150);
        jlabel.setFont(new Font("monospaced", Font.PLAIN, 12));
        jlabel.setForeground(Color.GREEN);
     
        jtextfield.setBounds(10,150,260,25);
        jtextfield.setFont(new Font("monospaced", Font.PLAIN, 12));
        jtextfield.setForeground(Color.GREEN);
        jtextfield.setBackground(Color.BLACK);
    
        jbutton.setBounds(270,150,80,25);
        jbutton.setFont(new Font("monospaced", Font.PLAIN, 12));
        jbutton.setForeground(Color.GREEN);
        jbutton.setBackground(Color.BLACK);
        
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //ACTIONLISTENER
    public void actionPerformed(ActionEvent i)
    {
        if (i.getSource() == jbutton)
        {
            submit = true;
            
            if(jtextfield.getText().compareToIgnoreCase(keyCommand()) == 0) //if the command was correct, stop the failure scenario and begin a new no-failure session
            {
                t2.stop();                                                  //stopping the failure timer
                errornumber++;                                              //indicating that the next session should go with a following failure
                FAILURE = false;                                            //stopping the failure scenario
                terminal.setVisible(false);                                 //closing the terminal window
                fsidebody.setVisible(false);                                //closing the failure scenario window
                createSideBody();                                           //creating a new session
                resetSubmit();                                              //resetting the submit boolean for the following failure
            }
            
            else                                                            //if the command was not correct, the state does not change
            {
                FAILURE = true;
            }
            
        }

    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //INFORMATIVE AND AUXILIARY
    public static void resetSubmit()                                        //to reset the submit boolean for the next failure
    {   
        submit = false;
    }
    
    public boolean returnSubmit()                                           //to inform the MainBody that the command was entered and evaluated
    {
        return submit;
    }
    
    public boolean returnState()                                            //to inform the MainBody about whether the problem was solved or not
    {
        return FAILURE;
    }
    ///////////////////////////////////////////////////////////////////////
    
    
    //EVALUATION OF COMMANDS
    public String keyCommand()                                              //determines and returns the "correct code" to solve each failure
    {                                                                     
        
                                                                            //if desired can be altered: correct commands for each type of failure
        if (error.compareToIgnoreCase("OXYGEN") == 0)
        {
            return "O2RES";
        }
        else if (error.compareToIgnoreCase("POT") == 0)
        {
            return "STLPOT;STHPOT";
        }
        else if (error.compareToIgnoreCase("CCV") == 0)
        {
            return "RECCVL";
        }
        else if (error.compareToIgnoreCase("LH2") == 0)
        {
            return "STLH2S";
        }
        else if (error.compareToIgnoreCase("HeRIGHT") == 0)
        {
            return "STHESS:r";
        }
        else if (error.compareToIgnoreCase("HeCENTER") == 0)
        {
            return "STHESS:c";
        }
        else if (error.compareToIgnoreCase("HeLEFT") == 0)
        {
            return "STHESS:l";
        }
        else if (error.compareToIgnoreCase("POGO") == 0)
        {
            return "REPOGO:true";
        }
        else if (error.compareToIgnoreCase("GPC1") == 0)
        {
            return "REGPC:1";
        }
        else if (error.compareToIgnoreCase("GPC235") == 0)
        {
            return "REGPC:2;REGPC:3;REGPC:5";
        }
        else if (error.compareToIgnoreCase("GPC1234") == 0)
        {
            return "REGPC:1_4";
        }
        else if (error.compareToIgnoreCase("GPC14") == 0)
        {
            return "REGPC:1;REGPC:4";
        }
        
        else 
        {
            return "";
        }
    }    
}

