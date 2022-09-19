package observer.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个具体的主题
 */
public class ConcreteSubject implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Message message) {
        observers.forEach(observer -> observer.update(message));
    }
}
