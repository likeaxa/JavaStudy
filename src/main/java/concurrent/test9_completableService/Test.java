package concurrent.test9_completableService;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 21:20
 */

import java.util.concurrent.*;

/**
 * CompletionService的功能是以异步的方式一边生产新的任务，
 * 一边处理已完成任务的结果，
 * 这样可以将执行任务与处理任务分离开来进行处理。
 *
 * 就是线程池池跑很多线程 然后你又想结束一个任务就处理一个任务 就可以采用这个类
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(5);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(service);
        for (int i = 0; i < 5; i++) {
            completionService.submit(new ReturnAfterSleepCallable(i));
        }
        System.out.println("after submit");
        for (int i = 0; i < 5; i++) {
            // 这个方法是阻塞的
            System.out.println("result: " + completionService.take().get());
        }
        System.out.println("after get");
        service.shutdown();
    }

    private static class ReturnAfterSleepCallable implements Callable<String> {
        int sleep;

        public ReturnAfterSleepCallable(int sleep) {
            this.sleep = sleep;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(sleep);
            return System.currentTimeMillis() + ",sleep=" + String.valueOf(sleep);
        }
    }
}
