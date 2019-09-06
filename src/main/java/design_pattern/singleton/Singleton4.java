package design_pattern.singleton;

/**
 * @Classname Singleton4
 * @Date 2019/9/6 14:13
 * @Created by yaoxinjian
 */
public class Singleton4 {
    private volatile  static Singleton4 instance = null;
    private  Singleton4(){}
    public static Singleton4 getInstance(){
        if(instance==null){
            synchronized (Singleton4.class){
                if(null==instance){
                    instance  = new Singleton4();
                    System.out.println("创建了一个对象");
                }
            }
        }
        return instance;
    }
}
