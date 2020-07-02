package concurrent.test1_lockAndCondition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by xinjian.yao
 * @date 2020/6/29 16:30
 */

/**
 * Synchronized 是jvm层面的实现 jvm在执行代码时，当一个线程访问一个对象的Synchronized方法或者代码块的时候，就持有了锁，
 * 除非执行完或者遇到异常（发生异常JVM虚拟机会自动释放锁），才能释放锁。
 * 而Lock 时jdk 自己定义的接口 主要的实现有ReentrantLock
 * Lock不会自己手动的加索和释放锁，必须手动执行lock.unLock()方法来释放锁，否则锁就永远不会得到释放。
 */

/**
 * 用锁的最佳实现 :
 * 1. 永远只在更新对象的成员变量时加锁
 * 2. 永远只在访问可变的成员变量时加锁
 * 3. 永远不在调用其他对象的方法时加锁
 */
public class LockTest1 {

    public static void main(String[] args) {
        LockObject lo = new LockObject();
        MyThread t1 = new MyThread(lo);
        MyThread t2 = new MyThread(lo);
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t2, "B");
        ta.start();
        tb.start();
    }

    static class LockObject {
        Lock lock = new ReentrantLock();

        public void LockFuc() {
            // 加锁
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "得到了锁");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        }
    }

    static class MyThread implements Runnable {
        LockObject lo = null;

        public MyThread(LockObject lo) {
            this.lo = lo;
        }

        @Override
        public void run() {
            lo.LockFuc();
        }

    }
}
