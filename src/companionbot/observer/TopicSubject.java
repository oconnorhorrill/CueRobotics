package companionbot.observer;

import java.util.HashSet;
import java.util.Set;

public class TopicSubject implements Subject{
    
    private final Set<TopicObserver> observers;
    
    public TopicSubject() {
        observers = new HashSet<>();
    }
    
    public void addObserver(TopicObserver topicObserver) {
        observers.add(topicObserver);
    }
    
    public boolean deleteObserver(TopicObserver topicObserver) {
        return observers.remove(topicObserver);
    }
    
    public void notifyObservers() {
        observers.forEach(o -> o.update());
    }
}
