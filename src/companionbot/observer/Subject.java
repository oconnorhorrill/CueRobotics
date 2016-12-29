package companionbot.observer;

public interface Subject {
    void addObserver(TopicObserver topicObserver);
    boolean deleteObserver(TopicObserver topicObserver);
    void notifyObservers();
}
