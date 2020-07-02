package concurrent.test8_completableFuture;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 20:33
 */

/**
 * 在Java中CompletableFuture用于异步编程，异步编程是编写非阻塞的代码，运行的任务在一个单独的线程，与主线程隔离，并且会通知主线程它的进度，成功或者失败。
 * 在这种方式中，主线程不会被阻塞，不需要一直等到子线程完成。主线程可以并行的执行其他任务。
 * CompletableFuture 的强大在于它实现的CompletionStage 这个接口的丰富
 * 这个接口提供了很多异步编程需要的api
 * eg 2个线程的 and or 操作 异常处理什么的
 * <p>
 * 串行关系：
 * CompletionStage接口里面描述串行关系，主要是thenApply、thenAccept、thenRun和thenCompose这四
 * 个系列的方法。
 * 聚合关系：
 * CompletionStage接口里面描述AND汇聚关系，主要是thenCombine、thenAcceptBoth和runAfterBoth系列
 * OR 聚合关系 ：
 * CompletionStage接口里面描述OR汇聚关系，主要是applyToEither、acceptEither和runAfterEither系列的 接口，
 * 这些接口的区别也是源自fn、consumer、action这三个核心参数不同。
 * 异常处理 ：
 * exceptionally()的使用非常类似于
 * try{}catch{}中的catch{}，但是由于支持链式编程方式，所以相对更简单。既然有try{}catch{}，那就一定还
 * 有try{}finally{}，whenComplete()和handle()系列方法就类似于try{}finally{}中的finally{}，无论是否发生异
 * 常都会执行whenComplete()中的回调函数consumer和handle()中的回调函数fn。whenComplete()和
 * handle()的区别在于whenComplete()不支持返回结果，而handle()是支持返回结果的。
 */
public class Test {
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
        // 定义一个需要异步执行的程序
        CompletableFuture aa = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A");
        }, executorService);
        // 定义一个需要异步执行 并且时有返回值的异步程序
        CompletableFuture bb = CompletableFuture.supplyAsync(() -> {
            System.out.println("B");
            return "B";
        }, executorService);

        // a 和 b  聚合 执行完成才执行 c
        CompletableFuture completableFuture = aa.thenCombineAsync(bb, (a, b) -> {
            System.out.println("C ->" + a);
            System.out.println("C ->" + b);

            return "C";
        }, executorService);
        String s = (String) completableFuture.get();
        System.out.println(s);
        executorService.shutdown();
    }

}
