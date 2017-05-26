

package companionbot;

import companionbot.context.ConversationContext;
import companionbot.observer.TopicObserver;
import companionbot.observer.TopicObserverImpl;
import companionbot.observer.TopicSubjectImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import companionbot.observer.TopicSubject;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import com.sun.speech.freetts.*;

/**
 *
 * @author James
 */
public class Main implements Initializable {
    
    @FXML
    private Label label;
    @FXML    
    private TextArea txtHistory;
    @FXML    
    private TextField txtMessage;
    @FXML
    private Button button;

    private Bot bot;
    
    // display bot response in the text area
    private static final String VOICENAME="kevin16";
    public void addBotText(String message) {
        txtHistory.setText(txtHistory.getText() + "\nBot: " + message);
        Voice voice;
            VoiceManager vm= VoiceManager.getInstance();
            voice=vm.getVoice(VOICENAME);
            
            voice.allocate();
            
            try{
                voice.speak(bot.getMessage());
            }catch(Exception e){
        }
    }
    
    public void deleteHistory() {
        txtHistory.setText("");
    }
        
    public void listen (String userSpeech) {        

        System.out.println("from main listen method: " + userSpeech + '\n');
        
        txtHistory.setText(txtHistory.getText() + "\nYou: " + userSpeech + "\n");
        
        //send the input to the bot and get bot response
        String response = bot.send(userSpeech);
        
        //if the response is not empty show it
        if (response.length() > 0) {
            addBotText(response);
        }   
        //display new state message to continue
        addBotText(bot.getMessage()); 
    }
    
    @FXML
    public void onSend(ActionEvent event) {
        // display users message
        txtHistory.setText(txtHistory.getText() + "\nYou: " + txtMessage.getText() + "\n");

        // send the message to the bot and get the bot response
        String response = bot.send(txtMessage.getText());

        // if the response is not empty display it
        if (response.length() > 0) {
            addBotText(response);
        }

        // display new state message
        addBotText(bot.getMessage());

        // clear the message textbox
        txtMessage.setText("");
    } 
    
    public void loadContext(){
        TopicSubject subject = setupObservers();
        // construct a new context
        ConversationContext cc = ConversationContext.newConversationContext(new Context());
        // construct new bot with level 0 as default and given data parser
        bot = new Bot("0", cc, subject);

        // display the default message
        txtHistory.setText("Bot: " + bot.getMessage());
    }
    
    private TopicSubject setupObservers() {
        TopicObserver observer = new TopicObserverImpl(txtHistory);
        TopicSubject subject = new TopicSubjectImpl();
        subject.addObserver(observer);
        return subject;
    }
    public void loadVoiceInput (){
        VoiceSubject voice = setupVoice();
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        Runnable reply = new Reply(queue);
        new Thread(reply).start();
        Router router = new Router("response", queue, voice);
    }
    
    private VoiceSubject setupVoice(){
        VoiceObserver voiceOb = new VoiceObserverImpl(txtHistory);
        VoiceSubject voiceSub = new VoiceSubjectImpl();
        voiceSub.addObserver(voiceOb);
        return voiceSub;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadContext();
        loadVoiceInput();
    }    
    
}
