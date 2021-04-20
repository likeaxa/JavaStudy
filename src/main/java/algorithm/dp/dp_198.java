package algorithm.dp;

/**
 * @author ：xinjian.yao
 * @date : 2021/4/20
 * 题目描述
 * 假如你是一个劫匪，并且决定抢劫一条街上的房子，每个房子内的钱财数量各不相同。如果
 * 你抢了两栋相邻的房子，则会触发警报机关。求在不触发机关的情况下最多可以抢劫多少钱。
 * 输入输出样例
 * 输入是一个一维数组，表示每个房子的钱财数量；输出是劫匪可以最多抢劫的钱财数量。
 * Input: [2,7,9,3,1]
 * Output: 12
 * 在这个样例中，最多的抢劫方式为抢劫第 1、3、5 个房子。
 * 题解：
 * 定义一个数组 dp，dp[i] 表示抢劫到第 i 个房子时，可以抢劫的最大数量。我们考虑 dp[i]，
 * 此时可以抢劫的最大数量有两种可能，一种是我们选择不抢劫这个房子，此时累计的金额即为
 * dp[i-1]；
 * 另一种是我们选择抢劫这个房子，那么此前累计的最大金额只能是 dp[i-2]，因为我们不
 * 能够抢劫第 i-1 个房子，否则会触发警报机关。因此本题的状态转移方程为 dp[i] = max(dp[i-1],
 * nums[i] + dp[i-2])。
 **/
public class dp_198 {


    public static void main(String[] args) {
        int[] test1 = new int[]{2, 7, 9, 3, 1};
        int[] test2 = new int[]{1, 2, 3, 1};
        dp_198 test = new dp_198();
        System.out.println(test.rob1(test1));
        System.out.println(test.rob1(test2));
    }

    /**
     * 空间复杂度 O(n)
     *
     * @param nums nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int j = 2; j < nums.length; j++) {
            dp[j] = Math.max(dp[j - 1], nums[j] + dp[j - 2]);
        }
        return dp[nums.length - 1];

    }

    /**
     * 优化空间复杂度
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int pre2 = nums[0];
        int pre1 = Math.max(nums[0], nums[1]);
        int cur = Math.max(pre1, pre2);
        for (int j = 2; j < nums.length; j++) {
            cur = Math.max(pre1, nums[j] + pre2);
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
