
package companionbot;
    
import java.util.concurrent.*;
import java.awt.event.ActionEvent;

public class Router {
    public BlockingQueue<String>queue;
    public String userSpeech;
    //private Main main;
    
    public Router(String str, BlockingQueue<String> queue) {
        this.queue = queue;
    }
    public synchronized void speak ()
    {        
       try 
       {
        String str = queue.take();
                if (str.length() > 0) {
        userSpeech = str;
        System.out.println("from router queue: " + userSpeech + '\n');
        //TODO check the string before handing it to the bot. Method to check repetition. Method to check last utterance.
        // Send the user speech to bot the via via the text field in the gui
        //Main main= new Main();
        main.listen(userSpeech);
        }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }  
}
