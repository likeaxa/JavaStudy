package design_pattern.responsibility;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:34
 */

public class Test {
    @org.junit.Test
    public void test(){
        Processor p1 = new ProcessorImpl1(null);
        Processor p2 = new ProcessorImpl2(p1);
        p2.process("something happened");
    }
}
