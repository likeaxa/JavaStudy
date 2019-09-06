package design_pattern.singleton;

/**
 * @Classname Singleton2
 * @Date 2019/9/6 11:28
 * @Created by yaoxinjian
 */

/**
 * 这种写法能够在多线程中很好的工作，
 * 而且看起来它也具备很好的lazy loading，
 * 但是，遗憾的是，效率很低，99%情况下不需要同步。
 */
public class Singleton2 {
    private static Singleton2  instance;
    private static int count = 1;
    private Singleton2(){}
    public static synchronized  Singleton2 getInstance(){
        if (instance == null) {
            instance = new Singleton2();
            System.out.println("创建第" + count++ + "个对象");
        }
        return instance;
    }
}
