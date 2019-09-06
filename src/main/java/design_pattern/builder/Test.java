package design_pattern.builder;

import Spring.demo1.main.test;

import java.util.ArrayList;

/**
 * @Classname Test
 * @Date 2019/9/6 15:37
 * @Created by yaoxinjian
 */
public class Test {
    @org.junit.Test
    public void BMWMModelTest() {
        BMWModel test = new BMWModel();
        ArrayList<String> sequence = new ArrayList<String>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        test.setSequence(sequence);
        test.run();
    }

    @org.junit.Test
    public void BenzModelTest() {
        BenzBuilder  benzBuilder = new BenzBuilder();
        ArrayList<String> sequence = new ArrayList<String>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");

        benzBuilder.setSequence(sequence);
        BenzModel test = (BenzModel)benzBuilder.getCarModel();
        test.setSequence(sequence);
        test.run();
    }
}
