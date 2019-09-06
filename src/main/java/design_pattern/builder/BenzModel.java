package design_pattern.builder;

/**
 * @Classname BenzModel
 * @Date 2019/9/6 15:34
 * @Created by yaoxinjian
 */

/**
 * 奔驰的发动动作
 */
public class BenzModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("奔驰的开起声音是这样的。。。");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰的停车声音是这样的。。。");

    }

    @Override
    protected void alarm() {
        System.out.println("奔驰的喇叭声音是这样的。。。");

    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰的停止声音是这样的。。。");

    }
}
