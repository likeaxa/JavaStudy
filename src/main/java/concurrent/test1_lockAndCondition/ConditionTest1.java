package concurrent.test1_lockAndCondition;

/**
 * @author by xinjian.yao
 * @date 2020/6/29 16:59
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，
 * 相比使用Object的wait()、notify()，使用Condition1的await()、signal()这种方式实现线程间协作更加安全和高效。
 * 因此通常来说比较推荐使用Condition，Conditon中的await()对应Object的wait()；Condition中的signal()对应Object的notify()；
 * Condition中的signalAll()对应Object的notifyAll()
 * <p>
 * <p>
 * 接下来看个例子，重写生产者与消费者：
 */
public class ConditionTest1 {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        Produce pd = new Produce(ss);
        Consume cs = new Consume(ss);
        Thread t1 = new Thread(pd);
        Thread t2 = new Thread(cs);
        t1.start();
        t2.start();
    }

    /*
     * 馒头实体类
     */
    static class ManTou {
        private int id;

        public ManTou(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "ManTou " + getId();
        }
    }

    /*
     * 馒头框类
     */
    static class SyncStack {
        Lock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();
        int index = 0;
        ManTou[] mtArray = new ManTou[6];

        public void push(ManTou mt) {
            lock.lock();
            try {
                while (index == mtArray.length) {
                    try {
                        full.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                empty.signal();
                mtArray[index] = mt;
                index++;
                System.out.println("生产了" + mt);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void pop() {
            lock.lock();
            try {
                while (index == 0) {
                    try {
                        empty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                full.signal();
                index--;
                System.out.println("消费了" + mtArray[index]);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    /*
     * 生产者
     */
   static class Produce implements Runnable {
        SyncStack ss = null;

        public Produce(SyncStack ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for (int i = 0; i < 20; i++) {
                ManTou mt = new ManTou(i);
                if (ss != null) {
                    ss.push(mt);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
     * 消费者
     */
    static class Consume implements Runnable {
        SyncStack ss = null;

        public Consume(SyncStack ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for (int i = 0; i < 20; i++) {
                if (ss != null) {
                    ss.pop();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
