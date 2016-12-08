/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

/**
 *
 * @author James
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
    System.out.println("from context.java " + source);
    //relaod parser and bot
    //DataParser.MyInputStream;
    

     //get the parser going
    DataParser dp = new DataParser();
        
    //make new bot with level 0 as default and given data parser
    bot = new Bot("0", dp);
    //return source;
    }
}

