package companionbot.observer;

import java.util.HashSet;
import java.util.Set;

public class TopicSubject implements Subject{
    
    private final Set<TopicObserver> observers;
    
    public TopicSubject() {
        observers = new HashSet<>();
    }
    
    @Override
    public void addObserver(TopicObserver topicObserver) {
        observers.add(topicObserver);
    }
    
    @Override
    public boolean deleteObserver(TopicObserver topicObserver) {
        return observers.remove(topicObserver);
    }
    
    @Override
    public void notifyObservers() {
        observers.forEach(o -> o.update());
    }
}
