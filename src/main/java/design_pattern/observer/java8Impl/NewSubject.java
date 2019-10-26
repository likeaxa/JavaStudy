package design_pattern.observer.java8Impl;

import design_pattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:00
 */
public interface NewSubject {
    List<Observer> list = new ArrayList<>();

    default void registerObserver(Observer o) {
        list.add(o);
    }

    default void nofityAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));
    }
}
