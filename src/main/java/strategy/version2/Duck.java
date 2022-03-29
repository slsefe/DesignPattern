package strategy.version2;

public abstract class Duck {

    /**
     * 不同的鸭子有不同的叫声
     */
    public void quack() {
    } // 默认呱呱叫

    /**
     * 所有的鸭子都会游泳
     */
    public void swim() {
    }

    /**
     * 有的鸭子会飞，有的鸭子不会飞
     */
    public void fly() {
    } // 默认会飞

    /**
     * 不同的鸭子有不同的外观
     */
    public abstract void display();
}
