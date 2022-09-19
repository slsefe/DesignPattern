package observer.demo;

/**
 * 主题接口
 */
public interface Subject {
    /**
     * 注册一个观察者到当前主题
     */
    void registerObserver(Observer observer);

    /**
     * 在当前主题中删除一个观察者
     */
    void removeObserver(Observer observer);

    /**
     * 当主题状态改变时，该方法会被调用，以通知所有观察者
     */
    void notifyObservers(Message message);
}
