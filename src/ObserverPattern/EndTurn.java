package ObserverPattern;

import java.util.ArrayList;

public class EndTurn implements Observable {
    private final ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
            observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.endTurn();
        }
    }
    public void removeObserver(Observer o){
        observers.remove(o);

    }

}
