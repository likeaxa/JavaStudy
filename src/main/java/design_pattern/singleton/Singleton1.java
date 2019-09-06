package design_pattern.singleton;

/**
 * @Classname Singleton1
 * @Date 2019/9/6 11:21
 * @Created by yaoxinjian
 */

/**
 * 这种写法lazy loading很明显，但是致命的是在多线程不能正常工作
 */
public class Singleton1 {
    private static Singleton1 instance;
    private static int count = 1;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
            System.out.println("创建第" + count++ + "个对象");
        }
        return instance;
    }
}
