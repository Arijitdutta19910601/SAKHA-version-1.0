package stt;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
import java.io.BufferedReader;

import java.io.File;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class SpeechToText 
{
public static void main(String[] args) 
{
 try 
 {
  URL url;
  if (args.length > 0) 
  {
   url = new File(args[0]).toURI().toURL();
  } 
  else 
  {
   url = SpeechToText.class.getResource("stt-config.xml");
  }
  System.out.println("Loading...");
  ConfigurationManager cm = new ConfigurationManager(url);
  Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
  Microphone microphone = (Microphone) cm.lookup("microphone");
  /* allocate the resource necessary for the recognizer */
  recognizer.allocate();
  /* the microphone will keep recording until the program exits */
  if (microphone.startRecording()) 
  {
   System.out.println("Say Anything");
   while (true) 
   {
    System.out.println("Start speaking. Press Ctrl-C to quit.\n");
    /*
    * This method will return when the end of speech
    * is reached. Note that the endpointer will determine
    * the end of speech.
    */ 
    Result result = recognizer.recognize();
    if (result != null) 
    {
     String resultText = result.getBestFinalResultNoFiller();
     //System.out.println("You said: " + resultText + "\n");
     
     //Modification
     JavaSystemCall jsc=new JavaSystemCall();
     System.out.println("Checking for Validation");
     jsc.validation(resultText);
     //
    } 
    else 
    {
     System.out.println("I can't hear what you said.\n");
    }
   }	    
  } 
  else 
  {
   System.out.println("Cannot start microphone.");
   recognizer.deallocate();
   System.exit(1);
  }
 } 
 catch (IOException e) 
 {
  System.err.println("Problem when loading HelloWorld: " + e);
  e.printStackTrace();
 } 
 catch (PropertyException e) 
 {
  System.err.println("Problem configuring HelloWorld: " + e);
  e.printStackTrace();
 } 
 catch (InstantiationException e) 
 {
  System.err.println("Problem creating HelloWorld: " + e);
  e.printStackTrace();
 }
 }
}

/**
 *
 * @author debdeep
 */

class JavaSystemCall {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public void system(String command)
    {
        try 
        {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            
            while ((line = reader.readLine()) != null) 
            {
                System.out.println(line);
                if(command.equals("whoami"))
                {
                    system("espeak Hello -s 150");
                    system("espeak "+line+" -s 150");
                }
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void validation(String resultText) throws FileNotFoundException
    {
        
        //***************** Firefox Commands*********************
        
        if(resultText.equals("open fire fox"))              //  English
        {
            system("espeak Openinng_firefox_for_you -s 150");
            system("firefox");
            //system("firefox www.gmail.com");
            //system("firefox www.google.co.in");
        }
        else if((resultText.equals("fire fox coo low")))    //  Bengali
        {
            system("espeak fire_fox_kho_laa_hoachtch_a -s 160");
            system("firefox");
        }
        //*******************************************************
        
        //***************** Terminal Commands*********************
        
        else if((resultText.equals("open terminal")))       //  English
        {
            system("espeak Openinng_terminal_for_you -s 150");
            system("gnome-terminal");
        }
        else if((resultText.equals("terminal coo low")))    //  Bengali
        {
            system("espeak Terminal_kho_laa_hoachtch_a -s 160");
            system("gnome-terminal");
        }
        //*********************************************************
        
        //***************** gedit Commands*************************
        
        else if(resultText.equals("open g a deed"))         //  English
        {
            system("espeak Openinng_gedit_for_you -s 150");
            system("gedit");
        }
        else if((resultText.equals("g a deed coo low")))    //  Bengali
        {
            system("espeak gedit_kho_laa_hoachtch_a -s 160");
            system("gedit");
        }
        //*********************************************************
        
        //********************** Some Other Commands ***************

        //**************************  Date Command ******************
        else if(resultText.equals("show the date"))         //      English
        {
            system("date");
        }
        else if((resultText.equals("date the cow")))        //      Bengali
        {
            system("date");
        }
        
        //**********************************************************************
        
        
        //**************************  ls command *************************
        else if(resultText.equals("show the file list"))       //      English
        {
            system("espeak here_is_the_list_for_you -s 150");
            system("ls");
        }
        else if((resultText.equals("sob files the cow")))        //     Bengali
        {
            system("espeak file_goo_lee_ho_lo -s 160");
            system("ls");
        }
        
        //**********************************************************************
        
        // *******************************  Shutdown Command *****************
        else if(resultText.equals("shut down the system"))    //     English
        {
            system("espeak system_is_shutting_down -s 150");
            system("shutdown -h now");
        }
        
        else if((resultText.equals("system bond o coo row")))   //  Bengali
        {
            system("espeak system_bond_o_hoachtch_a -s 160");
            system("shutdown -h now");
        }
        
        //**********************************************************************
        
        //********************** Restart Command ******************************
        
        else if(resultText.equals("restart the system"))        //     English
        {
            system("espeak system_is_restaring_for_you -s 150");
            system("shutdown -r now");
        }               
        
        else if((resultText.equals("put no ray system start coo row")))   //  Bengali
        {
            system("espeak system_bond_o_hoachtch_a -s 160");
            system("shutdown -r now");
        }
        
        //**********************************************************************
        
        // ******************* Setting Command ********************************
        else if(resultText.equals("open settings"))    //     English
        {
            system("espeak openning_system_setting_for_you -s 150");
            system("gnome-control-center");
        }
        
        else if((resultText.equals("setting cow low")))   //  Bengali
        {
            system("espeak system_setting_khola_hoachtch_a -s 160");
            system("gnome-control-center");
        }
        
        //**********************************************************************
        
        //*********************** Who am I Command *****************************
        
        else if(resultText.equals("who am i"))              //      Present User
        {
            system("whoami");
            //System("who");
        }
         
        else if(resultText.equals("open wire shark"))       //      Extra ######
        {
            system("espeak Openinng_wireshark_for_you -s 150");
            system("wireshark");
        }
        
        
        
        else
        {
            system("espeak Can't_Recognize -s 150");
            system("espeak please_try_again -s 150");
            System.out.println("Validation Failed");
        }
    }
}
