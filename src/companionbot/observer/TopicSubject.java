package companionbot.observer;

public interface TopicSubject {
    void addObserver(TopicObserver topicObserver);
    boolean deleteObserver(TopicObserver topicObserver);
    void notifyObservers();
}
