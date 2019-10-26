package design_pattern.observer;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 22:57
 */
public class OrderObserver implements Observer {
    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 状态更新为【已支付】");
    }
}
