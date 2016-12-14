/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companionbot;

public class Topic {
    
// topic variables
    public String currentTopic = "";
    public String newbot = "";
    
    public void getTopic(String topic){
        if (topic.equals("newcontext")) {
            currentTopic = "newContext.xml";
            newbot = "altbot";
        System.out.println("the current topic in Topic is  " + currentTopic);
        }
        if (topic.equals("basecontext")){
            currentTopic = "baseContext.xml";
            //Main.changeBot(newbot);
            newbot = "altbot";
            System.out.println("the current topic in Topic is  " + currentTopic);
        }
        if (topic.equals("basecontext")){
            currentTopic = "newcontext.xml";
            newbot = "homebot";
            //Main.changeBot(newbot);
            System.out.println("the current topic in Topic is  " + currentTopic);
        }
        Context context = new Context();
        context.newSource(currentTopic);
    }
}


