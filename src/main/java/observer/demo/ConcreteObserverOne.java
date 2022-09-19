package observer.demo;

public class ConcreteObserverOne implements Observer {
    @Override
    public void update(Message message) {
        // 获取消息通知，执行自己的逻辑
        System.out.println("observer 1 is notified");
    }
}
