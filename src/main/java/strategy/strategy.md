# 策略模式

## 版本一

### 需求

- 有多种不同类型的鸭子
- 所有的鸭子都会叫
- 所有的鸭子都会游泳
- 不同种类的鸭子有不同的外观

### 设计

- 使用抽象超类实现共性方法
- 使用继承和多态在子类中实现自定义方法

```java
public abstract class Duck {
    public void quack() {} // 所有的鸭子都会呱呱叫
    public void swim() {} // 所有的鸭子都会游泳
    public abstract void display(); // 不同的鸭子有不同的外观
}

public class MallardDck extends Duck {
    @Override
    public void display() {} // 外观是绿头
}

public class RedHeadDuck extends Duck {
    @Override
    public void display() {} // 外观是红头
}
```

## 版本二

### 需求变动

- 给鸭子增加会飞的功能，注意：不是所有的鸭子都会飞
- 增加了RubberDuck橡皮鸭，橡皮鸭的叫声是"吱吱"而非"呱呱"，橡皮鸭不会飞
- 增加DecoyDuck诱饵鸭，诱饵鸭是木头假鸭，不会飞也不会叫

### 设计

- 将quack()方法修改称为抽象方法，由子类自己实现
- 给Duck超类增加fly抽象方法，由子类自己实现

```java
public abstract class Duck {
    public void quack() {} // 默认呱呱叫
    public void swim() {} // 所有的鸭子都会游泳
    public void fly() {} // 默认会飞
    public abstract void display(); // 不同的鸭子有不同的外观
}

public class MallardDuck extends Duck {
    @Override
    public void display() {} // 外观是绿头
}

public class RedHeadDuck extends Duck {
    @Override
    public void display() {} // 外观是红头
}

public class RubberDuck extends Duck {
    @Override
    public void quack() {} // 重写父类方法，吱吱叫
    @Override
    public void fly() {} // 重写父类方法，不会飞
    @Override
    public void display() {} // 外观是橡皮鸭
}

public class DecoyDuck extends Duck {
    @Override
    public void quack() {} // 重写父类方法，不会叫
    @Override
    public void fly() {} // 重写父类方法，不会飞
    @Override
    public void display() {} // 外观是诱饵鸭
}
```

### 缺点

- 改动牵一发而动全身，如果有了新的子类

## 版本三

需求和版本二一致

### 设计

- 将fly从超类中抽取出来，构造一个Flyable接口，该接口提供一个fly方法，并提供默认实现，会飞的鸭子实现该接口
- 将quack方法从超类抽取出来，构造一个Quackable接口，会叫的鸭子实现该接口

```java
public interface Flyable {
    default void fly() {} // 提供默认实现
}

public interface Quackable {
    void quack();
}

public abstract class Duck {
    public void swim() {} // 所有的鸭子都会游泳
    public abstract void display(); // 不同的鸭子有不同的外观
}

public class MallardDuck extends Duck implements Flyable, Quackable {
    @Override
    public void display() {} // 外观是绿头鸭
    @Override
    public void fly() {} // 绿头鸭会飞
    @Override
    public void quack() {} // 绿头鸭呱呱叫
}

public class RedHeadDuck extends Duck implements Flyable, Quackable {
    @Override
    public void display() {} // 外观是红头鸭
    @Override
    public void fly() {} // 红头鸭会飞
    @Override
    public void quack() {} // 红头鸭呱呱叫
}

public class RubberDuck extends Duck implements Quackable {
    @Override
    public void display() {} // 外观是橡皮鸭
    @Override
    public void quack() {} // 橡皮鸭会吱吱叫，不会飞
}

public class DecoyDuck extends Duck {
    @Override
    public void display() {
        // 外观是诱饵鸭，诱饵鸭不会飞也不会叫
    }
}
```

### 缺点

- 接口没有提供默认实现的代码无法复用，如果有很多种会呱呱叫的鸭子，则需要在每个子类中都实现一遍相同的quack方法。
- Java8之后接口可以有默认实现，默认实现对现有接口的修改和之前的实现不兼容的问题，缓解了子类重复实现的问题
- 但是，如果接口存在多种实现方式，比如鸭子既可以呱呱叫，也可以吱吱叫的代码重复问题，还是无法解决。

## 版本四

### 设计

- 设计思想：多用组合，少用继承。
- 设计原则：找到应用中可能需要变化的部分，把它们取出，并封装起来。
- 将鸭子的行为从Duck类中取出，即将fly和quack两个经常变化的方法从Duck类中抽取出来。
- 对于fly和quack，分别创建一组相关的行为类，实现各自的动作。
- 利用接口代表每个行为，使用FlyBehavior表示飞行行为，使用QuackBehavior表示发出叫声这个行为，不同的行为实现同一个接口。

不同的飞行行为：

```java
public interface FlyBehavior {
    void fly();
}

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {} // 实现鸭子飞行
}

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {} // 什么都不做，不会飞
}
```

不同的叫声行为：

```java
public interface QuackBehavior {
    void quack();
}

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        // 呱呱叫
    }
}

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        // 橡皮鸭子吱吱叫
    }
}

public class MuteQuack implements QuackBehavior{
    @Override
    public void quack() {
        // 什么都不做，不会叫
    }
}
```

整合鸭子的行为：

```java
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
    
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void swim() {
    } // 所有的鸭子都会游泳

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public abstract void display(); // 不同的鸭子有不同的外观
}
```

绿头鸭子类，呱呱叫，会飞

```java
public class MallardDuck extends Duck {
    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        // 绿头鸭
    }
}
```

橡胶鸭，吱吱叫，不会飞

```java
public class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        // 橡胶鸭
    }
}
```

诱饵鸭，不会叫，不会飞

```java
public class DecoyDuck extends Duck {
    public DecoyDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        // 诱饵鸭
    }
}
```
