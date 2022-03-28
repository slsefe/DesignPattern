package singleton.common;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 全局唯一id生成器
 * 枚举
 */
public enum IdGenerator05 {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
