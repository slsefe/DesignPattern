# builder 模式

## 为什么需要builder模式

- 直接使用构造函数或者配合set方法就能创建对象，为什么还需要构建者模式呢？
- 他们的区别在什么地方？
- 什么时候使用构造函数创建对象，什么时候使用构建者模式创建对象？

builder解决的问题：当对象的参数过多、或者可选参数过多时，为了满足不同的需求，需要创建多个构造函数。

## 代码改造

- 需求：实现一个资源池配置类，包括必选参数资源池名称name，可选参数最大数量、最大空闲数量、最小空闲数量。
- 可选参数有默认值。
---
方案一：将必选参数和可选参数都作为构造函数的入参，分别进行判断，可选参数不为null的话覆盖默认值。

```java
public class ResourcePoolConfig {

    private static final int DEFAULT_MAX_TOTAL_COUNT = 8;
    private static final int DEFAULT_MAX_IDLE_COUNT = 8;
    private static final int DEFAULT_MIN_IDLE_COUNT = 0;

    private String name;
    private int maxTotalCount = DEFAULT_MAX_TOTAL_COUNT;
    private int maxIdleCount = DEFAULT_MAX_IDLE_COUNT;
    private int minIdleCount = DEFAULT_MIN_IDLE_COUNT;

    public ResourcePoolConfig(String name, Integer minIdleCount, Integer maxIdleCount, Integer maxTotalCount) {

        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.name = name;

        if (maxTotalCount != null) {
            if (maxTotalCount <= 0) {
                throw new IllegalArgumentException("max total count should be positive");
            }
            this.maxTotalCount = maxTotalCount;
        }

        if (maxIdleCount != null) {
            if (maxIdleCount < 0) {
                throw new IllegalArgumentException("max idle count can not be negative");
            }
            this.maxIdleCount = maxIdleCount;
        }

        if (minIdleCount != null) {
            if (minIdleCount < 0) {
                throw new IllegalArgumentException("min idle count can not be negative");
            }
            this.minIdleCount = minIdleCount;
        }
    }
}
```

优点：代码实现简单

缺点：当可选参数增多时，参数顺序容易弄乱；当只传必选参数时，创建对象时传入了多个不必要的null。

---
方案二：对于必选参数，通过构造方法传入；对于可选参数，通过setter方法进行赋值

```java
public class ResourcePoolConfig {

    private static final int DEFAULT_MAX_TOTAL_COUNT = 8;
    private static final int DEFAULT_MAX_IDLE_COUNT = 8;
    private static final int DEFAULT_MIN_IDLE_COUNT = 0;

    private String name;
    private int maxTotalCount = DEFAULT_MAX_TOTAL_COUNT;
    private int maxIdleCount = DEFAULT_MAX_IDLE_COUNT;
    private int minIdleCount = DEFAULT_MIN_IDLE_COUNT;

    public ResourcePoolConfig(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.name = name;
    }

    public void setMaxTotalCount(int maxTotalCount) {
        if (maxTotalCount <= 0) {
            throw new IllegalArgumentException("max total count should be positive");
        }
        this.maxTotalCount = maxTotalCount;
    }

    public void setMaxIdleCount(int maxIdleCount) {
        if (maxIdleCount < 0) {
            throw new IllegalArgumentException("max idle count can not be negative");
        }
        this.maxIdleCount = maxIdleCount;
    }

    public void setMinIdleCount(int minIdleCount) {
        if (minIdleCount < 0) {
            throw new IllegalArgumentException("min idle count can not be negative");
        }
        this.minIdleCount = minIdleCount;
    }
}
```

- 优点：去掉了冗长的函数调用和参数列表，提高了可读性 和 易用性
- 缺点：
  - （1）当存在很多必填字段时，还是无法避免冗长的函数调用和参数列表 
  - （2）当配置项之间存在依赖关系，比如 maxTotalCount >= maxIdleCount >= minIdleCount时，校验逻辑没有地方存放 
  - （3）对外暴露了set方法，如果希望类是不可变对象，就不能暴露set方法

---

方案三：使用构造者模式
  
实现： 
- （1）在ResourcePoolConfig类中创建一个名为Builder的公用静态内部类，内部类的属性和原来的类完全相同；
- （2）在ResourcePoolConfig类中创建一个构造方法，该方法接收Builder类作为参数，在构造方法中将Builder类的属性赋值给ResourcePoolConfig对象；
- （3）在Builder类创建无参的build方法，该方法调用ResourcePoolConfig的入参为Builder构造方法，返回创建的ResourcePoolConfig对象；
- （4）在内部类中对所有字段（必填字段&可选字段）提供set方法，set方法接收字段，完成校验和赋值逻辑，并返回该Builder对象本身；
- （5）使用`Object.Builder().setValue().build()`创建对象。

```java
public class ResourcePoolConfig {

  private final String name;
  private final int maxTotalCount;
  private final int maxIdleCount;
  private final int minIdleCount;

  private ResourcePoolConfig(Builder builder) {
    this.name = builder.name;
    this.maxTotalCount = builder.maxTotalCount;
    this.maxIdleCount = builder.maxIdleCount;
    this.minIdleCount = builder.minIdleCount;
  }

  public static class Builder {
    private static final int DEFAULT_MAX_TOTAL_COUNT = 8;
    private static final int DEFAULT_MAX_IDLE_COUNT = 8;
    private static final int DEFAULT_MIN_IDLE_COUNT = 0;

    private String name;
    private int maxTotalCount = DEFAULT_MAX_TOTAL_COUNT;
    private int maxIdleCount = DEFAULT_MAX_IDLE_COUNT;
    private int minIdleCount = DEFAULT_MIN_IDLE_COUNT;

    public ResourcePoolConfig build() {
      if (maxIdleCount > maxTotalCount || minIdleCount > maxIdleCount) {
        throw new IllegalArgumentException("max total count >= max idle count >= min idle count");
      }
      return new ResourcePoolConfig(this);
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
```

- 优点：
  - （1）避免了构造方法冗长的参数列表
  - （2）统一处理各个属性之间的校验
  - （3）避免了对外暴露set方法
- 缺点：
  - （1）代码有一定重复，比如原有对象的成员变量和Builder对象的成员变量重复

### lombok中的@Builder注解是如何工作的？

lombok中的@Builder注解可以很方便地完成对一个对象的构建者模式，除了上述的功能之外，还有：

（1）在主类中提供了builder()方法来获得静态内部类对象，可以使用Object.builder()而不是Object.Builder()来获得内部类；

（2）在内部类中重写了toString()方法。

（3）没有自定义的校验和异常处理逻辑

## 与工厂模式的区别

工厂模式：使用工厂类来创建相关类型的对象。

构建者模式：使用builder类来创建对象，只针对单个复杂对象，通过设置不同的可选参数，定制化地创建对象。
