/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companionbot;

public class Topic {
    
// topic variables
    private final String currentTopic;
    private final String newbot;
    private final Boolean newTopic;
    
    private Topic(String currentTopic) {
        this(currentTopic, "homebot", false);
    }
    
    private Topic(String currentTopic, String newbot, boolean newTopic) {
        this.currentTopic = currentTopic;
        this.newbot = newbot;
        this.newTopic = newTopic;
    }

    public String getCurrentTopic() {
        return currentTopic;
    }

    public String getNewbot() {
        return newbot;
    }
    
    public boolean isNewTopic() {
        return newTopic;
    }
    
    public static Topic getTopic(String currentTopicXml, String topic){
        
        String topicXml = "baseContext.xml";
        if (topic.equals("newcontext")) {
            topicXml = "newContext.xml";
            return new Topic(topicXml, "altbot", !topicXml.equals(currentTopicXml));
        }
        if (topic.equals("basecontext")){
            return new Topic(topicXml, "altbot", !topicXml.equals(currentTopicXml));
        }
        return new Topic(topicXml, "homebot", !topicXml.equals(currentTopicXml));
    }
}


