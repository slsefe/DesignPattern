package singleton.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 全局唯一id生成器
 * 静态内部类
 */
public class IdGenerator04 {
    private AtomicLong id = new AtomicLong(0);

    private IdGenerator04() {}

    private static class SingletonHolder {
        private static final IdGenerator04 instance = new IdGenerator04();
    }

    public static IdGenerator04 getInstance() {
        return SingletonHolder.instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
