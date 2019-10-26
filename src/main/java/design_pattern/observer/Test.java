package design_pattern.observer;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 22:59
 */
public class Test {
    @org.junit.Test
    public void test(){
        Subject subject = new SubjectImpl();
        subject.registerObserver(new OrderObserver());
        subject.registerObserver(new StockObserver());
        subject.notifyAllObserver("001");
    }
}
