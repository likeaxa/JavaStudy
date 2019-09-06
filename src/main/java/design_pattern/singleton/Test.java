package design_pattern.singleton;

/**
 * @Classname Test
 * @Date 2019/9/6 11:25
 * @Created by yaoxinjian
 */
public class Test {

    @org.junit.Test
    public void Singleton1Test(){
        // 多线程下并发有问题 创建了多个对象
        int threadSum = 10000;
        for(int i = 0;i<threadSum;i++){
            new Thread(()->{
                Singleton1.getInstance();
            }).start();
        }
    }
    @org.junit.Test
    public void Singleton2Test(){
        // 多线程没有问题 效率低下
        int threadSum = 10000;
        for(int i = 0;i<threadSum;i++){
            new Thread(()->{
                Singleton2.getInstance();
            }).start();
        }
    }
    @org.junit.Test
    public void Singleton3Test(){
        // 没有达到lazy loading的效果。
        int threadSum = 10000;
        for(int i = 0;i<threadSum;i++){
            new Thread(()->{
                Singleton3.getInstance();
            }).start();
        }
    }
    @org.junit.Test
    public void Singleton4Test(){
        // 没有达到lazy loading的效果。
        int threadSum = 10000;
        for(int i = 0;i<threadSum;i++){
            new Thread(()->{
                Singleton4.getInstance();
            }).start();
        }
    }
}
