package design_pattern.strategy;

/**
 * 绿头鸭，继承 Abstract Duck 类
 */
public class RubberDuck extends Duck{

    public RubberDuck(){
        //一开始，橡皮鸭并不会飞

    }

    // 橡皮鸭不同于一般鸭子的其他特征
    public void otherFeature(){

    }

}
