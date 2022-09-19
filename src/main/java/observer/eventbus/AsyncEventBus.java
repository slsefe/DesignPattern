package observer.eventbus;

import java.util.concurrent.Executor;

/**
 * 异步非阻塞观察者模式
 * 直接继承EventBus，将线程池注入即可。
 */
public class AsyncEventBus extends EventBus{
    private Executor executor;
    private ObserverRegistry registry = new ObserverRegistry();

    public AsyncEventBus(Executor executor) {
        this.executor = executor;
    }
}
