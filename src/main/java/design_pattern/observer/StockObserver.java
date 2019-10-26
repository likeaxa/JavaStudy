package design_pattern.observer;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 22:58
 */
public class StockObserver implements Observer {
    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 已通知库房发货！");
    }
}
