package concurrent.test1_lockAndCondition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by xinjian.yao
 * @date 2020/6/29 16:38
 */

/**
 * tryLock()方法是有返回值的，它表示用来尝试获取锁，
 * 如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false，也就说这个方法无论如何都会立即返回。
 * 在拿不到锁时不会一直在那等待
 * tryLock(long time, TimeUnit unit)方法和tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时会等待一定的时间，
 * 在时间期限之内如果还拿不到锁，就返回false。如果如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
 * tryLock(long time, TimeUnit unit) 第一个变量时等待多长的时间，第二个参数是个枚举类，代表等待的时间单位（秒，分钟，小时）
 */
public class LockTest2 {
    public static void main(String args[]) {
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
            // 尝试加锁
            if (lock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + "得到了锁");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了锁");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + "没有得到锁");
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
