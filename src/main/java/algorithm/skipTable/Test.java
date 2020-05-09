package algorithm.skipTable;

import java.util.Random;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 19:34
 */
public class Test {
    public static void main(String[] args) {
        //测试随机数生成的结果对二取模，结果是否是接近于0.5
        Random r = new Random(47);
        int t = 1, a = 1;
        while (a < 10000000) {
            a++;
            if (r.nextInt() % 2 == 0)
                t++;
        }
        System.out.println(t * 1.0 / a);

        SkipList<String> list = new SkipList<>();
        list.put(1.0, "1.0");
        System.out.println(list);
        list.put(2.0, "2.0");
        System.out.println(list);
        list.put(3.0, "3.0");
        System.out.println(list);
        list.put(4.0, "4.0");
        System.out.println(list);
        list.put(4.0, "5.0");
        System.out.println(list);
//        list.delete(3.0);
//        list.delete(3.5);
//        list.delete(4.0);
        System.out.println(list);
        System.out.println("查找4.0" + list.get(4.0));
        System.out.println("查找4.0" + list.get(3.0));
    }
}
