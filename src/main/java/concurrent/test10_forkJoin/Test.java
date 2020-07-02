package concurrent.test10_forkJoin;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 21:40
 */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * fork/join框架是用多线程的方式实现分治法来解决问题。fork指的是将问题不断地缩小规模，join是指根据子问题的计算结果，得出更高层次的结果。
 *
 * fork/join框架的使用有一定的约束条件：
 *
 * 1. 除了fork()  和  join()方法外，线程不得使用其他的同步工具。线程最好也不要sleep()
 *
 * 2. 线程不得进行I/O操作
 *
 * 3. 线程不得抛出checked exception
 *
 * 此框架有几个核心类：ForkJoinPool是实现了工作窃取算法的线程池。ForkJoinTask是任务类，
 * 他有2个子类：RecursiveAction无返回值，RecursiveTask有返回值，
 * 在定义自己的任务时，一般都是从这2类中挑一个，通过继承的方式定义自己的新类。
 * 由于ForkJoinTask类实现了Serializable接口，因此，定义自己的任务类时，
 * 应该定义serialVersionUID属性。
 * .
 * ForkJoinPool实现了工作窃取算法（work-stealing），线程会主动寻找新创建的任务去执行，从而保证较高的线程利用率。
 * 它使用守护线程（deamon）来执行任务，因此无需对他显示的调用shutdown()来关闭。
 * 一般情况下，一个程序只需要唯一的一个ForkJoinPool，因此应该按如下方式创建它：
 */
public class Test {

    /**
     * eg 将一个数组中每一个元素的值加1。具体实现为：将大数组不断分解为更短小的子数组，当子数组长度不超过10的时候，对其中所有元素进行加1操作。
     */
    public final static ForkJoinPool mainPool = new ForkJoinPool();

    public static void main(String[] args) {
        int n = 26;
        int[] a = new int[n];
        System.out.println("before:");
        for (int i = 0; i < n; i++) {
            a[i] = i;
            System.out.print(a[i] + " ");
        }
        SubTask task = new SubTask(a, 0, n);
        mainPool.invoke(task);
        System.out.println();
        System.out.println("after:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }
   static class SubTask extends RecursiveAction {

        private static final long serialVersionUID = 1L;

        private int[] a;
        private int beg;
        private int end;

        public SubTask(int[] a, int beg, int end) {
            super();
            this.a = a;
            this.beg = beg;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - beg > 10) {
                int mid = (beg + end) / 2;
                SubTask t1 = new SubTask(a, beg, mid);
                SubTask t2 = new SubTask(a, mid, end);
                invokeAll(t1, t2);
            } else {
                for (int i = beg; i < end; i++) {
                    a[i] = a[i] + 1;
                }
            }
        }
    }
}
