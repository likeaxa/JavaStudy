package concurrent.test7_futureAndfuturetask;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 19:53
 */

/**
 * Future
 * 在执行多个任务的时候，使用Java标准库提供的线程池是非常方便的。我们提交的任务只需要实现Runnable接口，就可以让线程池去执行：
 * Runnable接口有个问题，它的方法没有返回值。如果任务需要一个返回结果，那么只能保存到变量，还要提供额外的方法读取，非常不便。
 * 所以，Java标准库还提供了一个Callable接口，和Runnable接口比，它多了一个返回值：
 * 并且Callable接口是一个泛型接口，可以返回指定类型的结果。
 * <p>
 * 现在的问题是，如何获得异步执行的结果？
 * <p>
 * 仔细看ExecutorService.submit()方法，可以看到，它返回了一个Future类型，一个Future类型的实例代表一个未来能获取结果的对象：
 * 当我们提交一个Callable任务后，我们会同时获得一个Future对象，然后，我们在主线程某个时刻调用Future对象的get()方法，就可以获得异步执行的结果。
 * 在调用get()时，如果异步任务已经完成，我们就直接获得结果。如果异步任务还没有完成，那么get()会阻塞，直到任务完成后才返回结果。
 * <p>
 * <p>
 * FutureTask
 * FutureTask 实现了 RunnableFuture
 * 而 RunnableFuture 集成了Runnable 和 Future接口
 * 所以FutureTask 结合了 2者的特性 功能更加的强大
 */
public class Future {
    // 定义阻塞队列
    private static BlockingQueue<Runnable> query = new LinkedBlockingDeque<>(2);
    // 定义池饱和时的拒绝策略
    // 默认的策略 throw RejectedExecutionException
    private static RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    // 创建线程的改成
    private static ThreadFactory threadFactory = new DefaultThreadFactory("test");
    // 创建线程池 最大线程数要大于核心线程数 不然会报错
    public static ExecutorService executorService = new ThreadPoolExecutor(3, 4,
            1L, TimeUnit.SECONDS, query,
            threadFactory, rejectedExecutionHandler
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task = new Task();
        // Future
        java.util.concurrent.Future<String> future = executorService.submit(task);
        String result = future.get();
        System.out.println(result);

        //FutureTask
        FutureTask<String> futureTask = new FutureTask<>(task);
        executorService.submit(futureTask);
        System.out.println(futureTask.get());

        executorService.shutdown();
    }

    static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {

            return UUID.randomUUID().toString();
        }
    }

}
