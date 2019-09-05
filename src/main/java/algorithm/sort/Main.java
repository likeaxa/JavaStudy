package algorithm.sort;


import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Classname Main
 * @Date 2019/9/3 19:31
 * @Created by yaoxinjian
 */
public class Main {


    public static void main(String[] args) {
        int[] data = {4, 3, 2, 1, 5, 6, 9, 8};
        // 插入排序
        insertSort(data);
        // 希尔排序
        shellSort(data);
        // 冒泡排序
        bubbleSort(data);
        // 快速排序
        quickSort(data);
        // 选择排序
        selectSort(data);
        // 归并排序
        mergeSort(data);
        // 堆排序
        heapSort(data);
        Arrays.stream(data).forEach(System.out::print);
    }

    /**
     * 思想：每次将一个待排序的数据按照其关键字的大小插入到前面已经排序好的数据中的适当位置，
     * 直到全部数据排序完成。
     * 时间复杂度：O(n^2) O(n) O(n^2) （最坏 最好 平均）
     * 空间复杂度：O(1)
     * 稳定性： 稳定 每次都是在前面已排好序的序列中找到适当的位置，只有小的数字会往前插入，
     * 所以原来相同的两个数字在排序后相对位置不变。
     *
     * @param array
     */
    private static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            //往前找，直到找到比val小的元素，不然就查到最前面
            int j = i, val = array[i];
            for (; j > 0 && val < array[j - 1]; j--) {
                //不满足就把前面的数据往后移动
                array[j] = array[j - 1];
            }
            array[j] = val;
        }
    }

    /**
     * 思想：希尔排序根据增量值对数据按下标进行分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，
     * 每组包含的关键词越来越多，当增量减至1时，整体采用直接插入排序得到有序数组，算法终止。
     * 时间复杂度：O(n2) O(n) O(n1.5) （最坏，最好，平均）
     * 空间复杂度：O(1)
     * 稳定性：不稳定 因为是分组进行直接插入排序，原来相同的两个数字可能会被分到不同的组去，
     * 可能会使得后面的数字会排到前面，使得两个相同的数字排序前后位置发生变化。
     * 不稳定举例: 4 3 3 2 按2为增量分组，则第二个3会跑到前面
     *
     * @param array
     */
    private static void shellSort(int[] array) {
        int len;
        // 分成n/2组
        len = array.length / 2;
        while (len >= 1) {
            // 对每组进行排序
            for (int i = 1; i < array.length; i += len) {
                //往前找，直到找到比val小的元素，不然就查到最前面
                int j = i, val = array[i];
                while (j > 0 && val < array[j - 1]) {
                    //不满足就把前面的数据往后移动
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = val;
            }
            len /= 2;
        }
    }

    /**
     * 2.1.冒泡排序
     * 思想：对待排序元素的关键字从后往前进行多遍扫描，遇到相邻两个关键字次序与排序规则不符时，就将这两个元素进行交换。
     * 这样关键字较小的那个元素就像一个泡泡一样，从最后面冒到最前面来。
     * 时间复杂度：最坏：O(n2) 最好: O(n) 平均: O(n2)
     * 空间复杂度：O(1)
     * 稳定性：稳定，相邻的关键字两两比较，如果相等则不交换。所以排序前后的相等数字相对位置不变。
     * @param array
     */
    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1 - i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    /**
     * 思想：该算法是分治算法，首先选择一个基准元素,根据基准元素将待排序列分成两部分,一部分比基准元素小,
     * 一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
     * 基准元素的选择对快速排序的性能影响很大，
     * 所有一般会想打乱排序数组选择第一个元素或则随机地从后面选择一个元素替换第一个元素作为基准元素。
     *
     * 时间复杂度：最坏:O(n2) 最好: O(nlogn) 平均: O(nlogn)
     *
     * 空间复杂度：O(nlogn)用于方法栈
     *
     * 稳定性：不稳定 快排会将大于等于基准元素的关键词放在基准元素右边，加入数组 1 2 2 3 4 5 选择第二个2 作为基准元素，
     * 那么排序后 第一个2跑到了后面，相对位置发生变化。
     * @param array
     */
   private static void quickSort(int [] array){
        partiton(array,0,array.length-1);
    }

   private static void partiton(int[] array, int left, int right) {

        if(left<right){
            //在left到right中选择一个数把小于这个数的放左边，大于的放右边，返回这个数的角标
            int p = quickSort(array,left,right);
            partiton(array,left,p-1);
            partiton(array,p+1,right);

        }
    }
    private  static int quickSort(int[] array, int left, int right) {

        int val = array[left];

        while (left<right){
            //从右边开始扫，比val大的不用管
            while (left<right&&array[right]>val){
                right--;
            }
            //如果是遇见比val小的，把他和left置换
            if(left<right){
                array[left++]=array[right];
            }

            while (left<right&&array[left]<val){
                left++;
            }
            if (left<right){
                array[right--]=array[left];
            }
        }
        array[left]=val;
        return left;
    }

    /**
     * 思想：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后每次从剩余未排序元素中继续寻找最小（大）元素放到已排序序列的末尾。以此类推，
     * 直到所有元素均排序完毕
     *
     * 时间复杂度：最坏:O(n^2) 最好: O(n^2) 平均: O(n^2)
     *
     * 空间复杂度：O(1)
     *
     * 稳定性：不稳定 例如数组 2 2 1 3 第一次选择的时候把第一个2与1交换使得两个2的相对次序发生了改变。
     * @param array
     */
    private static void selectSort(int [] array ){

        for(int i = 0 ;i <= array.length-1;i++){
            // 每次找i到array.lenth-1 中最小的
            for(int j = i+1;j<array.length;j++){
                if(array[i]>array[j]){
                    int val = array[i];
                    array[i]=array[j];
                    array[j]=val;
                }
            }
        }


    }

    /**
     * 五.归并排序
     * 思想：归并排序采用了分治算法，首先递归将原始数组划分为若干子数组，
     * 对每个子数组进行排序。然后将排好序的子数组递归合并成一个有序的数组。
     * 时间复杂度：最坏:O(nlog2n) 最好: O(nlog2n) 平均: O(nlog2n)
     * 空间复杂度：O(n)
     * 稳定性：稳定
     * @param array
     */
    private  static void mergeSort(int [] array){
        mergeSort(array,0,array.length-1);
    }
    private  static void mergeSort(int[] array, int left, int right) {
        if(left>=right) {
            return;
        }
        int mid = (left+right)>>1;
        //左边排序
        mergeSort(array,left,mid);
        //右边排序
        mergeSort(array,mid+1,right);
        //归并
        mergeSort(array,left,mid,right);
    }

    private  static void mergeSort(int[] array, int left, int mid, int right) {
        int [] temp = new int[right+1];
        if (right + 1 - left >= 0) {
            // 把array复制到temp
            System.arraycopy(array, left, temp, left, right + 1 - left);
        }
        int rig = mid + 1;
        for(int i = left ;i <= right;i++){
            //左边拿完了拿右边
            if(left > mid ) {
                array[i] = temp[rig++];
            }
                //右边完了拿左边
            else if(rig>right) {
                array[i] = temp[left++];
            }
                //2边都有做比较
            else  if(temp[left]>temp[rig]) {
                array[i] = temp[rig++];
            }
            else {
                array[i]=temp[left++];
            }
        }
    }

    /**
     * 思想：堆排序是利用堆的性质进行的一种选择排序，先将排序元素构建一个最大堆,每次堆中取出最大的元素并调整堆。
     * 将该取出的最大元素放到已排好序的序列前面。这种方法相对选择排序，时间复杂度更低，效率更高。
     * 时间复杂度：最坏:O(nlog2n) 最好: O(nlog2n) 平均: O(nlog2n)
     * 空间复杂度：O(1)
     * 稳定性：不稳定 例如 5 10 15 10。 如果堆顶5先输出，则第三层的10(最后一个10)的跑到堆顶，然后堆稳定，
     * 继续输出堆顶，则刚才那个10跑到前面了，所以两个10排序前后的次序发生改变。
     * @param array
     */
    private static void heapSort(int[] array) {
        //初始化大根堆，从不是叶子节点的最后一个节点开始建立大根堆
        for(int i = (array.length-1)/2;i>=0;i--){
            filterHeap(array,i,array.length);
        }
        for (int i = array.length-1;i>0;i--){
            //每次都把第一个元素和第i个元素交换。
            int item  = array[0];
            array[0]=array[i];
            array[i]=item;
            //再调整arra[0~i]个元素，
            filterHeap(array,0,i);
        }

    }

    private static void filterHeap(int[] array, int root, int length) {
        int child = root * 2;
        int r = root;
        int item;
        while (child<length){
            //如果右还在比左孩子大 拿右孩子的
            if(child+1<length&&array[child]<array[child+1]){
                child++;
            }
            //如果孩子节点比当前节点大，调整位置，继续走
            if(array[r]<array[child]){
                item = array[r];
                array[r]=array[child];
                array[child]=item;
                r=child;
                child = r*2;
            }
            else {
                break;
            }
        }
    }
}
