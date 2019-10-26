package design_pattern.responsibility.java8Impl;

import java.util.function.Consumer;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:51
 */
public class Test {
    @org.junit.Test
    public void test(){
        Consumer<String> p1 = param -> System.out.println("processor 1 is processing:" + param);
        Consumer<String> p2 = param -> System.out.println("processor 2 is processing:" + param);
        p2.andThen(p1).accept("something happened");
    }
}
