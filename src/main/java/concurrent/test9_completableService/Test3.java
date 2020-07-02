package concurrent.test9_completableService;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 21:33
 */

import java.util.Random;
import java.util.concurrent.*;

/**
 * 方法poll的作用是获取并移除表示下一个已完成任务的Future，如果不存在这样的任务，则返回null，方法poll是无阻塞的。
 * <p>
 * 可以理解为访问一次完成任务的队列  如果没有 返回null 不会阻塞等待
 */
public class Test3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> service = new ExecutorCompletionService<String>(executorService);
        service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("3 seconds pass.");
                return "3秒";
            }
        });
        System.out.println(service.poll());
        executorService.shutdown();
    }
}
