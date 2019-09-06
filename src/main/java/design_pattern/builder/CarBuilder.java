package design_pattern.builder;

import java.util.ArrayList;

/**
 * @Classname CarBuilder
 * @Date 2019/9/6 15:43
 * @Created by yaoxinjian
 */

/**
 * 车子发动顺序建造者
 */
public abstract class CarBuilder {
    public abstract  void setSequence(ArrayList<String> sequence);
    public abstract CarModel getCarModel();
}
