package design_pattern.builder;

import java.util.ArrayList;

/**
 * @Classname BenzBuilder
 * @Date 2019/9/6 15:46
 * @Created by yaoxinjian
 */

/**
 * 奔驰的发动建造类
 */
public class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();
    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}
