package builder;

import java.util.Objects;

/**
 * 资源池配置类
 * 必选参数通过构造方法注入，可选参数通过setter方法注入
 */
public class ResourcePoolConfig02 {
    private static final int DEFAULT_MAX_TOTAL_COUNT = 8;
    private static final int DEFAULT_MAX_IDLE_COUNT = 8;
    private static final int DEFAULT_MIN_IDLE_COUNT = 0;

    private String name;
    private int maxTotalCount = DEFAULT_MAX_TOTAL_COUNT;
    private int maxIdleCount = DEFAULT_MAX_IDLE_COUNT;
    private int minIdleCount = DEFAULT_MIN_IDLE_COUNT;

    public ResourcePoolConfig02(String name) {
        if (Objects.isNull(name) || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.name = name;
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

    public void setMaxTotalCount(Integer maxTotalCount) {
        if (!Objects.isNull(maxTotalCount)) {
            if (maxTotalCount <= 0) {
                throw new IllegalArgumentException("max total count should be positive");
            }
            this.maxTotalCount = maxTotalCount;
        }
    }

    public void setMaxIdleCount(Integer maxIdleCount) {
        if (!Objects.isNull(maxIdleCount)) {
            if (maxIdleCount <= 0) {
                throw new IllegalArgumentException("max idle count should be positive");
            }
            this.maxIdleCount = maxIdleCount;
        }
    }

    public void setMinIdleCount(Integer minIdleCount) {
        if (!Objects.isNull(minIdleCount)) {
            if (minIdleCount < 0) {
                throw new IllegalArgumentException("min idle count cannot be negative");
            }
            this.minIdleCount = minIdleCount;
        }
    }
}
