package bot;

import java.util.concurrent.*;

public class Consumer {
    public BlockingQueue<String>queue;
    public static String speech;
    
    public Consumer(String str, BlockingQueue<String> queue) {
        this.queue = queue;
    }
    public synchronized void speak ()
    {        
       try 
       {
        String str = queue.take();
                if (str.length() > 0) {
                speech = str;
                Main.listen(speech);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }  
}



