package java8NewFeature.demo1_FunInterface;

/**
 * @Classname Formula
 * @Date 2019/9/2 14:26
 * @Created by yaoxinjian
 */

/**
 * 函数式接口是指只有一个接口只有一个抽象方法，但是可以有多个默认的实现方法
 * lambda 是函数式接口的一个签名 就是一个接口的实现 有点类似匿名类
 */
@FunctionalInterface
public interface Formula {

    double calculate(int a);

    /**
     * 接口可以用默认实现的方法
     * @param a
     * @return 对a开平方
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
