package strategy.version1;

public abstract class Duck {

    /**
     * 所有的鸭子都会呱呱叫
     */
    public void quack() {
    }

    /**
     * 所有的鸭子都会游泳
     */
    public void swim() {
    }

    /**
     * 不同的鸭子有不同的外观
     */
    public abstract void display();
}
