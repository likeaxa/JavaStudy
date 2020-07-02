package concurrent.test1_lockAndCondition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by xinjian.yao
 * @date 2020/6/29 16:50
 */

/**
 * lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，
 * 而线程B只有在等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程
 * <p>
 * 也就是说 采用这个方法获取锁时，如果一个线程已经获取到了锁，其他的线程只有等待到获取锁的线程释放，
 * 但是别的线程可以调用等待线程的interrupt()方法终止等待。
 */
public class LockTest3 {
    public static void main(String args[]) {
        LockObject lo = new LockObject();
        MyThread t1 = new MyThread(lo);
        MyThread t2 = new MyThread(lo);
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t2, "B");

        ta.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tb.start();
        tb.interrupt();

    }

    static class LockObject {
        Lock lock = new ReentrantLock();

        public void LockFuc() throws InterruptedException {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "得到了锁");
                while (true) {
                    ;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
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
            try {
                lo.LockFuc();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断");
            }

        }

    }
}
