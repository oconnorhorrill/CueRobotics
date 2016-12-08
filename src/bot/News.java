/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.sun.speech.freetts.*;
/**
 *
 * @author James
 */
public class News {

    // weather condition variables
    public String local = "";
    public String international = "";
    public String sports = "";
    public String entertainment = "";
    public String business = "";

    // yahoo API URL for london weather
    private static String url = "http://www.pass-the-ielts-exam.com/rss_xml/news.xml";   

    // default constructor
    public News() {

        // create http client
        HttpClient client = new HttpClient();

        // Create a method instance.
        GetMethod method = new GetMethod(url);

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            // display an error when method execution has failed
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            // get reponse
            BufferedReader br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));

            String xmlResponse = "";
            String readLine;
            while (((readLine = br.readLine()) != null)) {
                xmlResponse += readLine;
            }

            // parse the response
            parse(xmlResponse);

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

    }

    // parse returned XML string from weather API
    private void parse(String xmlString) {

        // get new document builder factory instance
        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        try {

            // construct a new document builder object
            DocumentBuilder db = dbf.newDocumentBuilder();

            // get input source
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlString));

            // parse the content
            Document doc = db.parse(is);

            // get current news node
            NodeList nodes = doc.getElementsByTagName("local:news");

            // get international condition nodes
            NodeList nodes2 = doc.getElementsByTagName("international:news");

            // get sports condition nodes
            NodeList nodes3 = doc.getElementsByTagName("sports:news");
            
            // get entertainment condition nodes
            NodeList nodes4 = doc.getElementsByTagName("entertainment:news"); 
           
            // get business condition nodes
            NodeList nodes5 = doc.getElementsByTagName("business:news");
            
            // get news from condition from nodes
            local = ((Element) nodes.item(0)).getAttribute("text");
            international = ((Element) nodes2.item(0)).getAttribute("text");
            sports = ((Element) nodes3.item(0)).getAttribute("text");
            entertainment = ((Element) nodes4.item(0)).getAttribute("text");
            business = ((Element) nodes5.item(0)).getAttribute("text");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // return news condition by given topic
    private static final String VOICENAME="kevin16";
    public String getResponse(String day) {
        String news = "";

        // if argument is local or not provided, return local news
        if (day.equals("local") || day.length() == 0) {
            news = "This is the news for today." + local;
            //turn the bot responce to sound
        Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("This is the news for today" + local);
            }catch(Exception e){
            }
        voice.deallocate();
        }

        // if argument is international news
        if (day.equals("international")) {
            news = "In the world today. " + international;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("In the world today. " + international);
            }catch(Exception e){
            }
        voice.deallocate();
        }

        // if argument is sports news
        if (day.equals("sports")) {
            news = "In sport today. " + sports;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("In sport today. " + sports);
            }catch(Exception e){
            }
        voice.deallocate();
        }
        
        // if argument is entertainment news
        if (day.equals("entertainment")) {
            news = "In the world of show biz. " + entertainment;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("In the world of show biz. " + entertainment);
            }catch(Exception e){
            }
        voice.deallocate();
        }
        
        // if argument is business news
        if (day.equals("business")) {
            news = "In economics today. " + business;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("In economics today. " + business);
            }catch(Exception e){
            }
        voice.deallocate();
        }
        return news;
    }
}

