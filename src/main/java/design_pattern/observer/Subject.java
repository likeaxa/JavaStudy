package design_pattern.observer;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 22:55
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyAllObserver(String orderNo);
}
