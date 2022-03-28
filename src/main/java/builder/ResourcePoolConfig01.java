package builder;

import java.util.Objects;

/**
 * 资源池配置类
 * 所有参数都通过构造函数传入
 */
public class ResourcePoolConfig01 {
    private static final int DEFAULT_MAX_TOTAL_COUNT = 8;
    private static final int DEFAULT_MAX_IDLE_COUNT = 8;
    private static final int DEFAULT_MIN_IDLE_COUNT = 0;

    private String name;
    private int maxTotalCount = DEFAULT_MAX_TOTAL_COUNT;
    private int maxIdleCount = DEFAULT_MAX_IDLE_COUNT;
    private int minIdleCount = DEFAULT_MIN_IDLE_COUNT;

    /**
     * 参数校验 & 赋值
     *
     * @param name,          必填参数，为null的话抛出异常
     * @param maxTotalCount, 可选参数
     * @param maxIdleCount,  可选参数
     * @param minIdleCount,  可选参数
     */
    public ResourcePoolConfig01(String name, Integer maxTotalCount, Integer maxIdleCount, Integer minIdleCount) {
        if (Objects.isNull(name) || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.name = name;

        if (!Objects.isNull(maxTotalCount)) {
            if (maxIdleCount <= 0) {
                throw new IllegalArgumentException("max total count should be positive");
            }
            this.maxTotalCount = maxTotalCount;
        }

        if (!Objects.isNull(maxIdleCount)) {
            if (maxIdleCount <= 0) {
                throw new IllegalArgumentException("max idle count should be positive");
            }
            this.maxIdleCount = maxIdleCount;
        }

        if (!Objects.isNull(minIdleCount)) {
            if (minIdleCount < 0) {
                throw new IllegalArgumentException("min idle count cannot be negative");
            }
            this.minIdleCount = minIdleCount;
        }
    }

    public String getName() {
        return name;
    }

    public int getMaxTotalCount() {
        return maxTotalCount;
    }

    public int getMaxIdleCount() {
        return maxIdleCount;
    }

    public int getMinIdleCount() {
        return minIdleCount;
    }
}
