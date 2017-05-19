
package companionbot.smalltalk;

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
public class Weather {

    // weather condition variables
    public String today = "";
    public String tomorrow = "";
    public String dayAfterTomorrow = "";
    public String adelaide = "";
    public String sydney = "";
    public String melbourne = "";
    public String perth = "";
    public String hobart = "";

    // Cue robotics weather xml
    private String url = "http://www.cuerobotics/cue/weather.xml";   

    // default constructor
    public Weather() {

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

            // get current condition node
            NodeList nodes = doc.getElementsByTagName("brisbane:weather");

            // tomorrow and day after tomorrow condition nodes
            NodeList forcast = doc.getElementsByTagName("weather:forecast");

            // get today, tomorrow and day after tomorrow condition from nodes
            today = ((Element) nodes.item(0)).getAttribute("text");
            tomorrow = ((Element) forcast.item(0)).getAttribute("text");
            dayAfterTomorrow = ((Element) forcast.item(1)).getAttribute("text");
            adelaide = ((Element) nodes.item(0)).getAttribute("text");
            sydney = ((Element) nodes.item(0)).getAttribute("text");
            melbourne = ((Element) nodes.item(0)).getAttribute("text");
            perth = ((Element) nodes.item(0)).getAttribute("text");
            hobart = ((Element) nodes.item(0)).getAttribute("text");
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // return weather condition by given day
    private final String VOICENAME="kevin16";
    public String getResponse(String day) {
        String weather = "";

        // if argument is today or not provided, return today's weather
        if (day.equals("today") || day.length() == 0) {
            weather = "This is " + today;
             //turn the bot responce to sound
        Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("This is " + today);
            }catch(Exception e){
            }
        voice.deallocate();
        }

        // tomorrow weather
        if (day.equals("tomorrow")) {
            weather = "I guess tomorrow will be " + tomorrow;
                 //turn the bot responce to sound
        Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("I guess tomorrow will be " + tomorrow);
            }catch(Exception e){
            }
        }

        // day after tomorrow weather
        if (day.equals("dayaftertomorrow")) {
            weather = "Day after tomorrow should be " + dayAfterTomorrow;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("Day after tomorrow should be " + dayAfterTomorrow);
            }catch(Exception e){
            }
        voice.deallocate();
        }
                // weatehr in adelaide
        if (day.equals("adelaide")) {
            weather = "The weather for today should be " + adelaide;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("The weatehr for Adelaide should be " + adelaide);
            }catch(Exception e){
            }
        voice.deallocate();
        }
                // weatehr in sydney
        if (day.equals("sydney")) {
            weather = "The weather for today should be " + sydney;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("Today in Sydeny the weather should be " + sydney);
            }catch(Exception e){
            }
        voice.deallocate();
        }
                // weatehr in melbourne
        if (day.equals("melbourne")) {
            weather = "The weather for today should be " + melbourne;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("In the souther capital melbourne " + melbourne);
            }catch(Exception e){
            }
        voice.deallocate();
        }
                // weatehr in perth
        if (day.equals("perth")) {
            weather = "The weather for today should be " + perth;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("The weather in in western australia and perth " + perth);
            }catch(Exception e){
            }
        voice.deallocate();
        }
                // weatehr in hobart
        if (day.equals("sydney")) {
            weather = "The for sydney " + hobart;
            Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak("Day after tomorrow should be " + hobart);
            }catch(Exception e){
            }
        voice.deallocate();
        }
        return weather;
        
    }
}

