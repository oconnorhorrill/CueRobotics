
package bot;

/**
 *
 * @author James O'Connorhorrill
 */
public class Context {

    public static String source = "newcontext.xml";
    
    private static Bot bot;
    
    //method to get value of source
    public static String getSource(){
    return source;
    }
    
    //get the new topic from Topic Class and prepare to reload DataParser
    public static void newSource(String currentTopic){
    source = currentTopic;
    }
    // TO DO - get the Bot and DataParser class to reload in JVM while Main class persists.
}

