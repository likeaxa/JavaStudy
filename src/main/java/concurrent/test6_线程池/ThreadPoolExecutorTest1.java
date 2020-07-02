package concurrent.test6_线程池;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 16:46
 */

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
 * 建议使用ThreadPoolExecutor 创建线程池 因为通过Executors 创建线程池 存在最大线程不可控 会出现oom问题
 *
 * 构造方法的参数解析 ：
 * corePoolSize 为线程池的基本大小。
 * maximumPoolSize 为线程池最大线程大小。
 * keepAliveTime 和 unit 则是线程空闲后的存活时间。
 * workQueue 用于存放任务的阻塞队列。
 * handler 当队列和最大线程池都满了之后的饱和策略。
 *
 * 线程池的状态 ：
 * RUNNING 自然是运行状态，指可以接受任务执行队列里的任务
 * SHUTDOWN 指调用了 shutdown() 方法，不再接受新任务了，但是队列里的任务得执行完毕。
 * STOP 指调用了 shutdownNow() 方法，不再接受新任务，同时抛弃阻塞队列里的所有任务并中断所有正在执行任务。
 * TIDYING 所有任务都执行完毕，在调用 shutdown()/shutdownNow() 中都会尝试更新为这个状态。
 * TERMINATED 终止状态，当执行 terminated() 后会更新为这个状态。
 *
 *
 * 线程池的拒绝策略：
 * CallerRunsPolicy：提交任务的线程自己去执行该任务。
 * AbortPolicy：默认的拒绝策略，会throws	RejectedExecutionException。
 * DiscardPolicy：直接丢弃任务，没有任何异常抛出。
 * DiscardOldestPolicy：丢弃最老的任务，其实就是把最早进入工作队列的任务丢弃，然后把新任务加入到工作队列。
 */
public class ThreadPoolExecutorTest1 {
    // 定义阻塞队列
    private static BlockingQueue<Runnable> query = new LinkedBlockingDeque<>(2);
    // 定义池饱和时的拒绝策略
    // 默认的策略 throw RejectedExecutionException
    private static RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    // 创建线程的改成
    private static ThreadFactory threadFactory = new DefaultThreadFactory("test");
    // 创建线程池 最大线程数要大于核心线程数 不然会报错
    public static Executor executor = new ThreadPoolExecutor(3, 4,
            1L, TimeUnit.SECONDS, query,
            threadFactory, rejectedExecutionHandler
    );

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("test");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName());
                    e.printStackTrace();
                }
            });
        }

    }
}


