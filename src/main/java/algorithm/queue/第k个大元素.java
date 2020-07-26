package algorithm.queue;

import java.util.PriorityQueue;

/**
 * @author ：xinjian.yao
 * @date : 2020/7/26
 **/
public class 第k个大元素 {
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4};
        int[] test2 = {7, 2, 3, 4,8,9,10};
        KthLargest kthLargestTest1 = new KthLargest(2, test1);
        KthLargest kthLargestTest2 = new KthLargest(4, test2);
        System.out.println(kthLargestTest1.queue);
        System.out.println(kthLargestTest2.queue);
    }

    public static class KthLargest {
        final PriorityQueue<Integer> queue;
        final Integer k;

        public KthLargest(int k, int[] a) {
            this.k = k;
            this.queue = new PriorityQueue<>();
            for (int i : a) {
                add(i);
            }
        }

        private void add(int i) {
            if (queue.size() < k) {
                queue.offer(i);
            } else if (queue.peek() < i) {
                queue.poll();
                queue.offer(i);
            }
        }
    }
}
