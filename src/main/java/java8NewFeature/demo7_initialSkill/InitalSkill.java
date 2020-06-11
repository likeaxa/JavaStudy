package java8NewFeature.demo7_initialSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by xinjian.yao
 * @date 2020/6/10 9:50
 */
public class InitalSkill {
    /**
     * 主要是平时用java8 的一些小技巧
     */
    public static void main(String[] args) {
        /**
         * 在 Java 中，Set/ List/ Map 等集合对象没有在生命期间初始化值的简单方法 (Java 11 支持了该类操作)。
         * 开发人员要么将值显式地传送到集合内，要么为常量集合创建一个静态块。
         * 使用双括号初始化，可以在声明过程中以更少的精力和时间初始化集合
         */
        List<String> testList = new ArrayList<String>() {{
            add("test1");
            addAll(Arrays.asList("test2", "test3"));
        }};
        System.out.println(testList);
    }
}
