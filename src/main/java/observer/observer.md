# 观察者模式

## 定义

观察者设计模式，简称观察者模式，也称为发布订阅模式。

在《设计模式：可复用面向对象软件的基础》一书中，定义如下：在多个对象之间，定义一个一对多的依赖，当一个对象的状态改变时，所有依赖这个对象的对象都会自动收到通知。

一般来说，被依赖的对象称为被观察者，依赖的对象称为观察者。在实际中存在多种不同的叫法，如主题Subject和观察者Observer，发布者Publisher和订阅者Subscriber，生产者Producer和消费者Consumer，事件发送者EventEmitter和事件监听者EventListener，调度者Dispatcher和监听者Listener。

## 代码实现

### 模板代码

demo package实现了同步阻塞观察者模式

## 应用场景

在不同的应用场景下，观察者模式可以分为：进程内的观察者模式 和 跨进程的观察者模式，其中进程内的观察者模式又可以分为：同步阻塞观察者模式 和 异步非阻塞观察者模式、

同步阻塞观察者模式：观察者和被观察者代码在同一个线程内运行，被观察者代码一直被阻塞，直到所有观察者代码执行完毕，被观察者才执行后续的操作。
异步非阻塞观察者模式：启用新的线程来执行观察者的代码。

跨进程的观察者模式的实现方式：
（1）在观察者的处理方法中调用外部接口来实现
（2）基于消息队列来实现

消息队列的缺点：需要引入一个新的系统，增加了维护成本
消息队列的优点：将被观察者和观察者彻底解耦，两者完全感知不到对方的存在，被观察者只负责发送消息到消息队列，观察者只负责从消息队列读取消息并处理。

## Guava EventBus 框架

Guava EventBus是一个提供了实现观察者模式的框架，支持 同步阻塞观察者模式 和 异步非阻塞观察者模式。

（1）EventBus和AsyncEventBus类

Guava EventBus框架对外暴露的所有可调用接口都封装在EventBus类中。EventBus类实现了同步阻塞观察者模式，AsyncEventBus类继承自EventBus类，实现了异步非阻塞观察者模式。

创建同步阻塞观察者：EventBus eventBus = new EventBus();
创建异步非阻塞观察者：EventBus asyncEventBus = new AsyncEventBus(Ececutors.newFixedThreadPool(8));

（2）register()方法

register()方法用户注册观察者，可以接受任何类型的观察者。
public void register(Object object);

（3）unregister()方法

从EventBus类中删除某个观察者。
public void unregister(Object object);

（4）post()方法

向观察者发送消息。
public void post(Object event);
与经典的观察者模式的不同之处在于，post()方法并非把消息发送给所有观察者，而是发送给匹配的观察者。

（5）@Subscribe注解
EventBus框架通过@Subscribe注解来标明是一个观察者，方法的入参类型即为监听的事件/消息类型。
```java
public class Observer {
    @Subscribe
    public void f1(MessageA event) {}
    @Subscribe
    public void f2(MessageB event) {}        
}
```

## 从零实现EventBus框架



