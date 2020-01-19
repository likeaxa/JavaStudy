package systemDesign.countDown;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.apache.xmlbeans.impl.schema.StscState.start;

/**
 * @author by xinjian.yao
 * @date 2020/1/17 19:07
 */
@Data
public abstract class AbstractCountDownHandle implements CountDownHandle {

    private List<ActivityBean> activityBeanList;

    AbstractCountDownHandle(List<ActivityBean> valList) {
        this.activityBeanList = valList;
    }

    public static Boolean activityIsExits = Boolean.TRUE;
    private static Lock lock = new ReentrantLock();

    @Override
    public Boolean registeredActivity(ActivityBean activityBean) {
//        List<Boolean> resultList = new ArrayList<>();
//        new Thread(() -> {
//            lock.lock();
//            resultList.add(activityBeanList.add(activityBean));
//            lock.unlock();
//        }).start();
//        activityIsExits = !activityBeanList.isEmpty();
//        return resultList.stream().allMatch(val -> val.equals(Boolean.TRUE));
        boolean result = activityBeanList.add(activityBean);
        activityIsExits = !activityBeanList.isEmpty();
        return result;
    }

    @Override
    public Boolean removeActivity(ActivityBean activityBean) {
//        List<Boolean> resultList = new ArrayList<>();
//        new Thread(() -> {
//            lock.lock();
//            resultList.add(activityBeanList.remove(activityBean));
//            lock.unlock();
//        }).start();
//        return resultList.stream().allMatch(val -> val.equals(Boolean.TRUE));
        boolean result = activityBeanList.remove(activityBean);
        activityIsExits = !activityBeanList.isEmpty();
        return result;

    }

    @Override
    public abstract void updateTimeDiscount();
}
