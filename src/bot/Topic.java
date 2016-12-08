/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;


public class Topic {
    
// topic variables
    public String currentTopic = "";
    public String testTopic = "";
    public String newtopic = "";
    
    public void getTopic(String topic){
        if (topic.equals("newtopic")) {
            currentTopic = "newTopic.xml";
        System.out.println("the current topic in Topic is  " + currentTopic);
        }
        if (topic.equals("oldtopic")){
            currentTopic = "oldTopic.xml";
            System.out.println("the current topic in Topic is  " + currentTopic);
        }
        if (topic.equals("maincontext")){
            currentTopic = "newcontext.xml";
            System.out.println("the current topic in Topic is  " + currentTopic);
        }
        Context.newSource(currentTopic);
    }
}


