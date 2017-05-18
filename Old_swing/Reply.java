/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Reply implements Runnable {
    
    public BlockingQueue<String> queue;
    public static int remainingCapacity;

    Reply(BlockingQueue<String> queue) {
                this.queue = queue;
  }
    
    @Override
    public synchronized void run () {
        
        ConfigurationManager cm;

        cm = new ConfigurationManager(Reply.class.getResource("helloworld.config.xml"));
        
        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
        recognizer.allocate();

        // start the microphone or exit if the programm if this is not possible
        Microphone microphone = (Microphone) cm.lookup("microphone");
        if (microphone.startRecording()) {
            while (true) {
                System.out.println("Start speaking. Press Ctrl-C to quit.\n");
                Result result = recognizer.recognize();
                    
                if (result != null) {
                try {
                    queue.put(result.getBestFinalResultNoFiller());
                    Consumer consumer = new Consumer("response", queue);
                    consumer.speak();
                    while(queue.remainingCapacity() == 0) {
                        notifyAll();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Reply.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("I can't hear what you said.\n");
                }  
            }
        }
        else {    System.out.println("Cannot start microphone.");
                    recognizer.deallocate();
                    System.exit(1);
        }     
    }
}


 
