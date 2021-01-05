package stt;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

import java.io.File;
import java.io.IOException;
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
     System.out.println("You said: " + resultText + "\n");
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
