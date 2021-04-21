package algorithm.dp;

/**
 * @author ：xinjian.yao
 * @date : 2021/4/21
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 **/
public class dp_91 {

    public static void main(String[] args) {
        dp_91 dp91 = new dp_91();
        System.out.println(dp91.numDecodings1("226"));
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            char current = s.charAt(i - 1);
            if (current != '0') {
                dp[i] += dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + current - '0') <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    public int numDecodings1(String s) {
        int cur = 0, pre1 = 1, pre2 = 1;
        for (int i = 1; i <= s.length(); i++) {
            cur = 0 ;
            char current = s.charAt(i - 1);
            if (current != '0') {
                cur += pre1;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + current - '0') <= 26) {
                cur += pre2;
            }
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
