package strategy.version2;

public class RubberDuck extends Duck {

    @Override
    public void quack() {
        // 吱吱叫
    }

    @Override
    public void fly() {
        // 覆盖，不会飞
    }

    @Override
    public void display() {
        // 橡皮鸭
    }
}
