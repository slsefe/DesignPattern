# 单例模式

- 为什么要使用单例模式？
- 如何实现单例模式？
- 单例模式存在哪些问题？
- 单例与静态类的区别是什么？
- 单例模式有哪些替代方案？

### 什么是单例模式

一个类只允许创建一个对象（实例），那么这个类就是单例类，这种设计模式就叫做单例设计模式。

### 单例模式应用场景

#### 1、资源访问冲突

package original 

Logger类实现了一个往文件中打印日志的Logger类。

当多个对象同时往一个文件中写日志的时候会出现问题。

解决方案：

（1）使用类级别的锁
（2）分布式锁：实现一个分布式锁
（3）并发队列：多个线程同时往并发队列中写日志，一个单独的线程负责将并发队列中的数据，写入到日志文件中。
（4）使用单例对象

将原有的构造方法修改称为私有方法；增加一个静态变量，用于返回当前对象；增加一个静态方法，用于返回当前对象。 实现见package singleton。

#### 2、表示全局唯一类

package common

### 单例实现方法：
（1）实现私有构造方法，避免外界通过new关键字创建实例；
（2）对象创建时的线程安全问题；
（3）考虑是否支持延迟加载；
（4）getInstance方法的性能，是否加锁；

- 饿汉式

**在类加载的时候，就创建并初始化静态实例。特点：线程安全，但是不支持延迟加载。**
实现：IdGenerator01.java

- 懒汉式

懒汉式与饿汉式的区别是：在类加载的时候，创建静态变量但是不进行初始化，当需要的时候再进行初始化，支持延迟加载。

优点：支持延迟加载
缺点：加锁，并发高时会有性能问题
为什么要对getInstance()加锁？如果不加锁的话，当多个线程同时调用时，可能会创建多个实例对象，这样就不是单例的了。
实现：IdGenerator02.java

- 双重检测式

饿汉式：不支持延迟加载
懒汉式：支持延迟加载，但是有性能问题，不支持高并发
双重检测：对懒汉式的实现进行改造，只有在创建instance的时候才在类上加锁，一旦创建了instance就不再加锁。
实现：IdGenerator03.java


- 静态内部类

实现：IdGenerator04.java

使用静态内部类的方式创建单例对象。
在单例对象类中创建一个私有的静态内部类，在静态内部类中创建一个私有的静态属性，返回单例对象。
在单例对象中通过公共方法调用静态内部类。

- 枚举

最简单的实现方式：基于枚举类型，通过java枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
实现：IdGenerator05.java

### 单例模式存在哪些问题

- 单例类违反了"面向接口而非实现编程"的设计原则，单例类放弃了继承和多态这两个特性
- 单例类隐藏了类之间的依赖关系
- 单例类对代码的扩展性不友好
- 单例类对代码的可测试性不友好，单例类中的成员变量实际上是全局变量，在进行单元测试的时候需要注意。
- 单例类不支持有参数的构造函数

### 单例有哪些替代解决方案

1. 静态方法

```java
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static AtomicLong id = new AtomicLong(0);

    public static long getId() {
        return id.incrementAndGet();
    }

    public static void main(String[] args) {
        long id = IdGenerator.getId();
        System.out.println(id);
    }
}
```

缺点：不支持延迟加载

2. 通过工厂模式来保证的类对象的全局唯一性
3. 通过IOC容器来保证类对象的全局唯一性
4. 通过程序员自己来保证，在编写代码的时候不要创建两个类对象

### 如何理解单例模式中的唯一性

单例：一个类只允许创建唯一一个对象（实例）。

对象唯一性的作用范围是什么？线程 or 进程?

单例对象在进程中是唯一的，在进程之间是不唯一的，即两个进程可能分别有各自的单例对象。

一个进程有多个线程，单例对象在一个进程的多个线程之间是唯一的。

我们上述实现的经典的单例模式是进程唯一的。

### 如何实现线程唯一的单例

进程唯一指的是：进程内唯一、进程间不唯一、线程内唯一、线程间唯一。

线程唯一指的是：进程内不唯一、进程间不唯一、线程内唯一、线程间不唯一。

实现：通过hashmap存储对象，key是线程ID，value是对象。这样，不同的线程对应不同的对象，同一个线程只能对应一个对象。

```java
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);
    
    private static final ConcurrentHashMap<Long, IdGenerator> instances = new ConcurrentHashMap<>();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        long currentThreadId = Thread.currentThread().getId();
        instances.putIfAbsent(currentThreadId, new IdGenerator());
        return instances.get(currentThreadId);
    }
    
    public long getId() {
        return id.incrementAndGet();
    }
}
```

### 如何实现集群环境下的单例

集群相当于多个进程构成的一个集合，"集群唯一"：进程内唯一、进程间唯一。

实现方式：将单例对象序列化存储到外部共享存储区（比如文件）。 进程在使用这个单例对象的时候，需要先从外部共享存储区将它读取到内
存，并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区。
为了保证任何时刻，在进程间都只有一份对象存在，一个进程在获取到对象之后，需要对对象加锁，避免其他进程再将其获取。
在进程使用完这个对象之后，还需要显式地将对象从内存中删除，并且释放对象的加锁。

```java
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator instance;
    private static SharedObjectStorage storage = new FileStorage();
    private static DistributedLock lock = new DistributedLock();

    private IdGenerator() {
    }

    public synchronized static IdGenerator getInstance() {
        if (instance == null) { // 从外部共享存储区读取对象
            lock.lock();
            instance = storage.load(IdGenerator.class);
        }
        return instance;
    }

    public synchronized void freeInstance() {
        storage.save(this, IdGenerator.class);
        instance = null; // 释放对象
        lock.unlock();
    }

    public long getId() {
        return id.incrementAndGet();
    }

    public static void main(String[] args) {
        IdGenerator idGenerator = IdGenerator.getInstance();
        long id = IdGenerator.getId();
        IdGenerator.freeInstance();
    }
}
```


### 如何实现一个多例模式

多例模式和单例模式相对应，多例模式：一个类可以创建多个对象，但是个数是有限制的。
实现：通过一个map来存储对象类型与对象之间的对应关系，来控制对象的个数。
实现：在类中维护一个map，其中保存允许创建的多例对象。在创建对象时，首先从map中去拿，如果拿不到，就创建一个对象并放到map中，然后返回该对象。

以Logger类为例，不同的class可以创建不同的对象，同一个class只能创建唯一的对象。

```java
public class Logger {
    private static final ConcurrentHashMap<String, Logger> instances = new ConcurrentHashMap<>();

    private Logger() {
    }

    public static Logger getInstance(String name) {
        instances.putIfAbsent(name, new Logger());
        return instances.get(name);
    }

    public void log() {
    }

    public static void main(String[] args) {
        Logger l1 = Logger.getInstance("User.class");
        Logger l2 = Logger.getInstance("User.class");
        Logger l3 = Logger.getInstance("Order.class");
    }
}
```



多例模式与工厂模式的区别：多例模式创建的对象都是同一个类的对象，工厂模式创建的是不同子类的对象。

多例模式与享元模式的区别：todo
