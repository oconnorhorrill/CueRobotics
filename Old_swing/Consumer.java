package bot;

import java.util.concurrent.*;
import java.awt.event.ActionEvent;

public class Consumer {
    public BlockingQueue<String>queue;
    public static String voice;
    private Main main;
    
    public Consumer(String str, BlockingQueue<String> queue) {
        this.queue = queue;
    }
    public synchronized void speak ()
    {        
       try 
       {
        String str = queue.take();
                if (str.length() > 0) {
        voice = str;
        Main.click();
        System.out.println("from comsume queue: " + voice + '\n');
        }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }  
}



