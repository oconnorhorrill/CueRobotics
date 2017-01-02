package companionbot.observer;

import javafx.scene.control.TextArea;

public class TopicObserverImpl implements TopicObserver {
    
    private final TextArea textArea;
    
    public TopicObserverImpl(TextArea textArea) {
        this.textArea = textArea;
    }
    
    @Override
    public void update() {
        textArea.setText("");
    }
}
