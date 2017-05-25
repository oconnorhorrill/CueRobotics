
package companionbot;
    
import java.util.concurrent.*;
import java.awt.event.ActionEvent;

public class Router {
    public BlockingQueue<String>queue;
    public String userText;
    
    public Router(String str, BlockingQueue<String> queue) {
        this.queue = queue;
    }
    public synchronized void speak ()
    {        
       try 
       {
        String str = queue.take();
                if (str.length() > 0) {
        userText = str;
        System.out.println("from router queue: " + userSpeech + '\n');
        //TODO Check the user speech first before passing it to Main, update text area object.
                    
        //Main.listen(userText);
        }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }  
}
