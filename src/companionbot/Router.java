
package companionbot;
    
//import companionbot.observer.TopicSubject;
import companionbot.observer.VoiceSubject;
import java.util.concurrent.*;
import java.awt.event.ActionEvent;

public class Router {
    
    public BlockingQueue<String>queue;
    
    private final VoiceSubject voiceSubject;
    
    public String userText;
    
    public Router(String str, BlockingQueue<String> queue, VoiceSubject voicesubject) {
        this.queue = queue;
        this.voiceSubject = voiceSubject;
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
                    
        voiceSubject.notifyObservers();
        }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }  
}
