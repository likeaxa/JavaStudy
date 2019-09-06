package design_pattern.singleton;

/**
 * @Classname Singleton3
 * @Date 2019/9/6 11:33
 * @Created by yaoxinjian
 */

/**
 * 这种方式基于classloder机制避免了多线程的同步问题，不过，instance在类装载时就实例化，
 * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用getInstance方法，
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，
 * 这时候初始化instance显然没有达到lazy loading的效果
 */
public class Singleton3 {
    private static Singleton3 instance = new Singleton3();
    static {
        System.out.println("创建了一个对象");
    }
    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return instance;
    }

}
