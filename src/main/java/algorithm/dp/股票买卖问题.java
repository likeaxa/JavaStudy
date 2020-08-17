package algorithm.dp;

/**
 * @author ：xinjian.yao
 * @date : 2020/7/28
 **/
public class 股票买卖问题 {

    public static void main(String[] args) {
        System.out.println(solve1(new int[]{7, 1, 5, 3, 6, 4}));
    }


    /**
     * 一次买卖求最大
     *
     * @param prices
     * @return
     */
    public static int solve(int[] prices) {
        if (prices.length == 0) return 0;

        int res = 0;
        int[][] prifit = new int[prices.length + 1][3];
        prifit[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            int a = prifit[i][0] = prifit[i - 1][0];
            int b = prifit[i][1] = Math.max(prifit[i - 1][1], prifit[i - 1][0] - prices[i]);
            int c = prifit[i][2] = prifit[i - 1][1] + prices[i];
            res = Math.max(res, Math.max(Math.max(a, b), c));
        }
        return res;
    }

    /**
     * 无限次买卖
     */
    public static int solve1(int[] prices) {
        int cur = prices[0];
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > cur) {
                res += prices[i] - cur;

            }
            cur = prices[i];
        }
        return res;
    }


}
