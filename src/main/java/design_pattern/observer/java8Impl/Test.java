package design_pattern.observer.java8Impl;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:02
 */
public class Test {
    @org.junit.Test
    public void test(){
        NewSubject subject = new NewSubject() {
        };
        subject.registerObserver((String orderNo) -> System.out.println("订单 " + orderNo + " 状态更新为【已支付】"));
        subject.registerObserver((String orderNo) -> System.out.println("订单 " + orderNo + " 已通知库房发货！"));
        subject.nofityAllObserver("002");
    }
}
