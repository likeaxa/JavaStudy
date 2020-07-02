package concurrent.test2_semaphore;

/**
 * @author by xinjian.yao
 * @date 2020/6/30 16:33
 */

import java.util.concurrent.Semaphore;

/**
 * Semaphore 字面意思是信号量的意思，它的作用是控制访问特定资源的线程数目
 *  重要方法 ：
 *  acquire() 表示阻塞并获取许可
 * release() 表示释放许可
 * 主要用途：可用于流量控制，限制最大的并发访问数。 控制有多少线程可以访问
 */
public class semaphoreTest {
    static class TaskThread extends Thread {

        Semaphore semaphore;

        public TaskThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(getName() + " acquire");
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(getName() + " release ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        Semaphore semaphore = new Semaphore(2);
        // 只能有2个线程进行
        for (int i = 0; i < threadNum; i++) {
            new TaskThread(semaphore).start();
        }
    }
}
