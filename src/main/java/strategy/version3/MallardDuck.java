package strategy.version3;

public class MallardDuck extends Duck implements Flyable, Quackable {
    @Override
    public void display() {
        // 外观是绿头鸭
    }

    @Override
    public void quack() {
        // 呱呱叫
    }
}
