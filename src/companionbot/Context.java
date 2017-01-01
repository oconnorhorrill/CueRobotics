
package companionbot;

/**
 *
 * @author James
 */
public class Context {
    private final String source;
    
    public Context() {
        source = "basecontext.xml";
    }
    
    public Context(String source) {
        this.source = source;
    }
    
    //method to get value of source for dataparser
    public String getSource(){
        return source;
    }
    
    public Topic getTopic(String newTopic) {
        return Topic.getTopic(getSource(), newTopic);
    }    
}

