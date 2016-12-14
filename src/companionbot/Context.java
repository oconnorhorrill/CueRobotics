
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
    //public static String getSource(){
    return source;
    }
    //get the new topic from Topic Class and prepare to reload bot and parset
    public void newSource(String currentTopic){
    //public static void newSource(String currentTopic){
    source = currentTopic;
    System.out.println("the current source file in Context is  " + source);
    Main main = new Main();
    main.loadContext();
    }

}

