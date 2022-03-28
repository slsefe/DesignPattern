package singleton.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 全局唯一递增id生成器
 * 双重检测
 */
public class IdGenerator03 {
    // 在类加载的时候，创建实例变量，不进行初始化
    private static IdGenerator03 instance;

    private final AtomicLong id = new AtomicLong(0);

    private IdGenerator03() {}

    // 当需要实例变量的时候，再进行初始化
    // 初始化实例变量需要加锁，这里只在创建的时候加锁，获取的时候不加锁，只加锁一次
    public static IdGenerator03 getInstance() {
        if (instance == null) {
            synchronized(IdGenerator03.class) {
                instance = new IdGenerator03();
            }
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
