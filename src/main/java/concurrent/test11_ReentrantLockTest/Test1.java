package concurrent.test11_ReentrantLockTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：xinjian.yao
 * @date : 2020/7/25
 **/
public class Test1 {
    private ReentrantLock lock = new ReentrantLock(true);
    public void fairLock(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()  + "正在持有锁");
        }finally {
            System.out.println(Thread.currentThread().getName()  + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test1 test = new Test1();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            test.fairLock();
        };
        Thread[] thread = new Thread[10];
        for(int i = 0;i < 10;i++){
            thread[i] = new Thread(runnable);
        }
        for(int i = 0;i < 10;i++){
            thread[i].start();
        }
    }

}
