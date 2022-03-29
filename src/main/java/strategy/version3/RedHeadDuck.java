package strategy.version3;

public class RedHeadDuck extends Duck implements Flyable, Quackable {
    @Override
    public void display() {
        // 外观是红头鸭
    }

    @Override
    public void quack() {
        // 呱呱叫
    }
}
