package concurrent.test5_countdownlatchAndcyclicbarrier;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 15:53
 */

import java.util.Map;
import java.util.concurrent.*;

/**
 * 理解 ：CyclicBarrier也是java.util.concurrent包下的一个类；从类名就可以看出，
 * 这是一个可以循环使用(Cylcic)的屏障(Barrier)，所做的事情就是让一组线程到达一个屏障(同步点)时被阻塞，
 * 直到这组线程中的最后一个到达屏障时，屏障才会打开，之前阻塞的线程继续运行
 * <p>
 * 如何使用:实例化CyclicBarrier对象时通过它的构造函数设置屏障要拦截的线程（调用barrier.await的次数）的数据量，
 * 每个线程通过调用CyclicBarrier实例的await方法告诉CyclicBarrier我已经到达屏障，并将自己阻塞。
 * 此外，如果在构造CyclicBarrier时设置了一个Runnable实现，那么最后一个barrier.await 的线程会执行这个方法，以完成一些预设工作
 * <p>
 * eg :一个Excel表记录了用户一个季度所有的银行流水，每个sheet记录了该用户每个月的银行流水情况，
 * 要统计该用户整个季度的银行流水状况时，可以先使用多线程统计每个sheet的银行流水，都执行完毕后，
 * 使用每个线程的计算结果来计算出该用户整个季度的银行流水状况。
 *
 * 注意 : 在线程池中使用CyclicBarrier时一定要注意线程的数量要多于CyclicBarrier实例中设置的阻塞线程的数量就会发生死锁。
 * 调用await()方法的次数一定要等于屏障中设置的阻塞线程的数量，否则也会死锁。
 *
 * 区别： 首先二者都能让一个或多个线程阻塞等待，都可以用在多个线程间的协调
 * ，起到线程同步的作用。但CountDownLatch是多个线程都进行了countDown之后才会触发时间，
 * 唤醒await在latch上的线程，执行完countDown操作之后会继续自己线程的工作。而CyclicBarrier是一个栅栏，
 * 用于同步所有调用await方法的线程，等到所有的方法都执行了await方法后，所有的线程才会返回各自执行自己的工作。
 * CountDownLatch计数器只能使用一次，而CyclicBarrier的计数器可以调用 reset() 方法重置，能处理更加复杂的业务场景。
 *
 */
public class cyclicbarrierTest1 implements Runnable {

    //创建一个CyclicBarrier实例,屏障数据设为3,处理完之后执行当前类的run方法
    private CyclicBarrier cb = new CyclicBarrier(3, this);

    /*创建线程池,只有三个月的数据,所以只需三个线程*/
    private Executor executor = Executors.newFixedThreadPool(3);

    /*创建一个ConcurrentHashMap,用来保存每个sheet计算出的结果*/
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<String, Integer>();

    public void count() {
        for (int i = 0; i < 3; i++) {

            /*每个线程用来处理单个sheet中的任务*/
            executor.execute(new Runnable() {

                public void run() {

                    /*此处加入复杂的逻辑处理代码*/
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);

                    try {
                        /*线程完成工作后调用await 设置屏障*/
                        cb.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }


    /*等到所有的线程到达屏障*/

    public void run() {
        int res = 0;
        /*根据之前多线程的结果计算出整个季度的银行流水*/
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            res += sheet.getValue();
        }

        sheetBankWaterCount.put("result", res);
        /*将结果输出,看看哪个线程调用这个方法*/
        System.out.println(Thread.currentThread().getName());
        System.out.println(res);
    }

    public static void main(String[] args) {

        cyclicbarrierTest1 test = new cyclicbarrierTest1();

        /*注意,此时不需要调用test.run(),最后一个await方法会调用run方法*/
        test.count();

    }

}
