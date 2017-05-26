package companionbot.observer;

public interface VoiceSubject {
    void addObserver(VoiceObserver VoiceObserver);
    boolean deleteObserver(VoiceObserver VoiceObserver);
    void notifyObservers();
}
