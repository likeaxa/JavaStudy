package design_pattern.builder;

/**
 * @Classname BMWModel
 * @Date 2019/9/6 15:35
 * @Created by yaoxinjian
 */

/**
 * 宝马的发动动作
 */
public class BMWModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("宝马的开起声音是这样的。。。");
    }

    @Override
    protected void stop() {
        System.out.println("宝马的停车声音是这样的。。。");
    }

    @Override
    protected void alarm() {
        System.out.println("宝马的喇叭声音是这样的。。。");
    }

    @Override
    protected void engineBoom() {
        System.out.println("宝马的停止声音是这样的。。。");
    }
}
