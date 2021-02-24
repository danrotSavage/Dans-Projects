package ObserverPattern;

public interface Observable {

    void addObserver(Observer o);
    void notifyObserver();

}
