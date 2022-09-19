package observer.demo;

public class ConcreteObserverTwo implements Observer {
    @Override
    public void update(Message message) {
        // 获取消息通知，执行自己的逻辑
        System.out.println("observer 2 is notified");
    }
}
