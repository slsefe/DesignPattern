package builder;

import java.util.Objects;

/**
 * 资源池配置类
 * 原有的类不对外暴露公有的构造方法，通过Class.Builder().withField().build()来创建对象。
 * 在Class类中创建 静态内部类，将构造对象的过程包装在 静态内部类 中。
 */
public class ResourcePoolConfig03 {

    private final String name;
    private final int maxTotalCount;
    private final int maxIdleCount;
    private final int minIdleCount;

    private ResourcePoolConfig03(Builder builder) {
        this.name = builder.name;
        this.maxTotalCount = builder.maxTotalCount;
        this.maxIdleCount = builder.maxIdleCount;
        this.minIdleCount = builder.minIdleCount;
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

    public static class Builder {
        private static final int DEFAULT_MAX_TOTAL_COUNT = 8;
        private static final int DEFAULT_MAX_IDLE_COUNT = 8;
        private static final int DEFAULT_MIN_IDLE_COUNT = 0;

        private String name;
        private int maxTotalCount = DEFAULT_MAX_TOTAL_COUNT;
        private int maxIdleCount = DEFAULT_MAX_IDLE_COUNT;
        private int minIdleCount = DEFAULT_MIN_IDLE_COUNT;

        public ResourcePoolConfig03 build() {
            if (maxIdleCount > maxTotalCount || minIdleCount > maxIdleCount) {
                throw new IllegalArgumentException("max total count >= max idle count >= min idle count");
            }
            return new ResourcePoolConfig03(this);
        }

        public Builder withName(String name) {
            if (Objects.isNull(name) || name.isEmpty() || name.isBlank()) {
                throw new IllegalArgumentException("name cannot be empty");
            }
            this.name = name;
            return this;
        }

        public Builder withMaxTotalCount(int maxTotalCount) {
            if (maxTotalCount <= 0) {
                throw new IllegalArgumentException("max total count should be positive");
            }
            this.maxTotalCount = maxTotalCount;
            return this;
        }

        public Builder withMaxIdleCount(Integer maxIdleCount) {
            if (maxIdleCount <= 0) {
                throw new IllegalArgumentException("max idle count should be positive");
            }
            this.maxIdleCount = maxIdleCount;
            return this;
        }

        public Builder withMinIdleCount(Integer minIdleCount) {
            if (minIdleCount < 0) {
                throw new IllegalArgumentException("min idle count cannot be negative");
            }
            this.minIdleCount = minIdleCount;
            return this;
        }
    }
}
