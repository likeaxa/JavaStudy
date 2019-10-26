package design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 22:55
 */
public class SubjectImpl implements Subject {
    private final List<Observer> list = new ArrayList<>();
    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void notifyAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));

    }
}
