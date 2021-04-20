package algorithm.KMP;

/**
 * @author ：xinjian.yao
 * @date : 2021/4/20
 * 学习 KMP 算法链接
 * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 * KMP 算法的核心主要是算 next 数组
 * next数组含义：代表在模式串P中，当前下标对应的字符之前的字符串中，有多大长度的相同前缀后缀。
 * 例如如果next [j] = k，代表在模式串P中，下标为j的字符之前的字符串中有最大长度为k 的相同前缀后缀。
 * <p>
 * 怎么求 next 数组，
 * next[0] = -1
 * 如果知道 next[j] = p 怎么求 next[j+1] 呢
 * 1 如果 needle[j+1] = needle[p+1]  则 next[j+1] = p+1
 * 2 如果 needle[j+1] != needle[p+1]并且 p>-1 则领 p = next[p] 然后举行执行1过程。
 * 否则 next[j+1] = -1
 **/
public class KMPTest {

    public static void main(String[] args) {
        KMPTest test = new KMPTest();
        String matchStr = "ADCABD";
        String needStr = "AB";
        int[] next = new int[needStr.length()];
        test.getNext(needStr, next);
        System.out.println(test.match(matchStr, needStr,next));
    }

    public int match(String matchStr, String needStr, int[] next) {
        char[] haystack = matchStr.toCharArray();
        char[] needle = needStr.toCharArray();
        int p = needle.length;
        int k = -1;
        for (int i = 0; i < haystack.length; i++) {
            while (k > -1 && haystack[i] != needle[k + 1]) {
                k = next[k];
            }
            if (haystack[i] == needle[k + 1]) {
                k++;
            }
            if (k == p - 1) {
                return i - p + 1;
            }
        }
        return -1;
    }

    public void getNext(String str, int[] next) {
        char[] needle = str.toCharArray();
        next[0] = -1;
        for (int j = 1, p = -1; j < needle.length; j++) {
            while (p > -1 && needle[j] != needle[p + 1]) {
                p = next[p];
            }
            if (needle[j] == needle[p + 1]) {
                p++;
            }
            next[j] = p;
        }
    }
}
