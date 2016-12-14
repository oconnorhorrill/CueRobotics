
package companionbot;
/**
 *
 * @author James
 */
        
public class Context {
    public String source = "basecontext.xml";
    
    private Bot bot;

    //method to get value of source for dataparser
    public String getSource(){
    return source;
    }
    //get the new topic from Topic Class and prepare to reload bot and parset
    public void newSource(String currentTopic){
    source = currentTopic;
    Main main = new Main();
    main.loadContext();
    }

}

