package concurrent.test10_forkJoin;

/**
 * @author by xinjian.yao
 * @date 2020/7/2 21:55
 */

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * eg
 * 任务拥有返回值。随机生成一个数组，每个元素均是0-999之间的整数，统计该数组中每个数字出现1的次数的和。
 *
 * 实现方法，将该数组不断的分成更小的数组，直到每个子数组的长度为1，即只包含一个元素。
 * 此时，统计该元素中包含1的个数。最后汇总，得到数组中每个数字共包含了多少个1。
 */
public class Test2 {
    public final static ForkJoinPool mainPool = new ForkJoinPool();

    public static void main(String[] args) {
        int n = 26;
        int[] a = new int[n];
        Random rand = new Random();
        System.out.println("before:");
        for (int i = 0; i < n; i++) {
            a[i] = rand.nextInt(1000);
            System.out.print(a[i] + " ");
        }
        SubTask task = new SubTask(a, 0, n);
        int count = mainPool.invoke(task);
        System.out.println();
        System.out.println("after:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n数组中共出现了" + count + "个1");
    }
   static class SubTask extends RecursiveTask<Integer> {

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
        protected Integer compute() {
            int result = 0;
            if (end - beg > 1) {
                int mid = (beg + end) / 2;
                SubTask t1 = new SubTask(a, beg, mid);
                SubTask t2 = new SubTask(a, mid, end);
                invokeAll(t1, t2);
                try {
                    result = t1.get() + t2.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                result = count(a[beg]);
            }
            return result;
        }

        // 统计一个整数中出现了几个1
        private int count(int n) {
            int result = 0;
            while (n > 0) {
                if (n % 10 == 1) {
                    result++;
                }
                n = n / 10;
            }
            return result;
        }
    }
}
