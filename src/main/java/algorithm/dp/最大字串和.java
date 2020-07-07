package algorithm.dp;

/**
 * @author by xinjian.yao
 * @date 2020/7/3 20:20
 */

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class 最大字串和 {
    public static void main(String[] args) {
        int[] num = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(num));
        System.out.println(divideAndRule(num, 0, num.length - 1));

    }

    /**
     * 动态规划求解
     * @param nums 数字
     * @return 最大字串的值
     */
    public static int maxSubArray(int[] nums) {

        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 分治发求解
     * @param nums 数组
     * @param left 左边的角标
     * @param right 右边的角标
     * @return 最大字串的值
     */
    public static int divideAndRule(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (right + left) / 2;
        int leftMax = divideAndRule(nums, left, mid);
        int rightMax = divideAndRule(nums, mid + 1, right);

        // 左串 右边最大的串值
        int leftest = nums[mid], tempLeft = nums[mid];
        for (int i = mid - 1; i >= left; i--) {
            tempLeft += nums[i];
            if (leftest < tempLeft) {
                leftest = tempLeft;
            }
        }

        // 右串 左边最大的串值
        int rightest = nums[mid + 1], tempRight = nums[mid + 1];
        for (int i = mid + 2; i <= right; i++) {
            tempRight += nums[i];
            if (rightest < tempRight) {
                rightest = tempRight;
            }
        }
        return Math.max(Math.max(leftMax, rightMax), rightest + leftest);

    }
}
