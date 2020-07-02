package concurrent.test9_completableService;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 21:26
 */

/**
 * take()方法取得最先完成任务的Future对象，谁执行时间最短谁最先返回。
 *
 * 就是等完成队列有任务了 进行处理。 没有就等到有任务完成为止
 */
public class Test2 {
    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
            for (int i = 0; i < 10; i++) {
                completionService.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        int sleepValue = new Random().nextInt(5);
                        System.out.println("sleep = " + sleepValue + ", name: " + Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(sleepValue);
                        return "test: " + sleepValue + ", " + Thread.currentThread().getName();
                    }
                });
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(completionService.take().get());
            }
            executorService.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
