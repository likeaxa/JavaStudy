package design_pattern.strategy;

/**
 * 策略模式：多用组合，少用继承
 */
public class Test {
    public static void main(String[] args) {

        // 构造一只橡皮鸭，此时他不会飞
        RubberDuck rubber = new RubberDuck();

        // 组合飞行行为
        rubber.setFlyBehavior(()-> System.out.println("no way"));

        // 现在，橡皮鸭会飞了
        rubber.fly();

    }
}
