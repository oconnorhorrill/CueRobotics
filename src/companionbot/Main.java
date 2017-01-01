
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
    //@FXML    
    public Bot bot;
    
       
    public void addBotText(String message) {
        txtHistory.setText(txtHistory.getText() + "\nBot: " + message);
    }
    
    public void deleteHistory() {
        txtHistory.setText("");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadContext();  
    }    
    
}
