
package companionbot;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    @FXML    
    public Bot bot;
    
       
    public void addBotText(String message) {
        txtHistory.setText(txtHistory.getText() + "\nBot: " + message);
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
        // construct a data parser
        DataParser dp = new DataParser();

        // construct new bot with level 0 as default and given data parser
        bot = new Bot("0", dp);

        // display the default message
        txtHistory.setText("Bot: " + bot.getMessage());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadContext();  
    }    
    
}
