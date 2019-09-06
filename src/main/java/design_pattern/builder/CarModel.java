package design_pattern.builder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Classname CarModel
 * @Date 2019/9/6 15:25
 * @Created by yaoxinjian
 */

/**
 * 车站的发动动作 和启动顺序
 */
public abstract class CarModel {
    // 这个参数是各个基本方法执行的顺序
    private ArrayList<String> sequence = new ArrayList<>();

    // 模型是启动开始跑了
    protected abstract void start();

    // 能发动，还要能停下来，那才是真本事
    protected abstract void stop();

    // 喇叭会出声音，是滴滴叫，还是哔哔叫
    protected abstract void alarm();

    // 引擎会轰隆隆地响，不响那是假的
    protected abstract void engineBoom();

    // 那模型应该会跑吧，别管是人推的，还是电力驱动，总之要会跑
    final public void run() {
        // 循环一边，谁在前，就先执行谁
        this.sequence.forEach(car->{
            if("start".equalsIgnoreCase(car)){
                this.start();
            }else if("stop".equalsIgnoreCase(car)){
                this.stop();
            }else if("alarm".equalsIgnoreCase(car)){
                this.alarm();
            }else if("engine boom".equalsIgnoreCase(car)){
                this.engineBoom();
            }
        });

    }
    final public void setSequence(ArrayList<String> sequence){
        this.sequence = sequence;
    }
}
