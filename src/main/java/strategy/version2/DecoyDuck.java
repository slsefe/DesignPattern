package strategy.version2;

public class DecoyDuck extends Duck {

    @Override
    public void quack() {
        // 覆盖父类方法，不会叫
    }

    @Override
    public void fly() {
        // 覆盖父类方法，不会飞
    }

    @Override
    public void display() {
        // 外观是诱饵鸭
    }
}
