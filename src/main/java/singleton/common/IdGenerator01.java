package singleton.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 全局唯一递增iD生成器
 * 饿汉式
 */
public class IdGenerator01 {
    // 在类加载的时候，就创建实例变量，并进行初始化。线程安全，但是不支持延迟加载。
    private static IdGenerator01 instance = new IdGenerator01();

    private final AtomicLong id = new AtomicLong(0);

    private IdGenerator01() {
    }

    public static IdGenerator01 getInstance() {
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }

}
