package singleton.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 全局唯一递增id生成器
 * 懒汉式
 */
public class IdGenerator02 {
    // 在类加载的时候，创建静态实例变量，不进行初始化
    private static IdGenerator02 instance;

    private final AtomicLong id = new AtomicLong(0);

    private IdGenerator02() {}

    // 当需要实例对象的时候，再进行初始化。线程不安全，需要加锁。
    // 缺点：每次获取实例的时候，都需要加锁，影响性能
    public static synchronized IdGenerator02 getInstance() {
        if (instance == null) {
            instance = new IdGenerator02();
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
