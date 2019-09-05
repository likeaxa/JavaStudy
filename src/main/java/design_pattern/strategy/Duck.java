package design_pattern.strategy;

/**
 * Strategy pattern （策略模式），使用组合（对象+行为接口），而不是继承
 *
 * 鸭子类 + 飞行行为 叫声行为 的组合
 */
abstract class Duck {

    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    public Duck(){

    }

    void fly(){
        flyBehavior.fly();
    }

    void quack(){
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
