package companionbot.observer;

// This is the same as TopicObserver, So adding TopicObserver to the VoiceSubject could do this. Dupplication?
// Might be dangerous to access Text area from two observers?
// Think about this after doing the Router code.

import javafx.scene.control.TextArea;

public class VoiceObserverImpl implements VoiceObserver {
    
    private final TextArea textArea;
    
    public VoiceObserverImpl(TextArea textArea) {
        this.textArea = textArea;
    }
    
    @Override
    public void update() {
        textArea.setText("");
    }
}
