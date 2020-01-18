package systemDesign.countDown;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public Boolean registeredActivity(ActivityBean activityBean) {
        List<Boolean> resultList = new ArrayList<>();
        new Thread(() -> {
            resultList.add(activityBeanList.add(activityBean));
        }).start();
        return resultList.stream().allMatch(val -> val.equals(Boolean.TRUE));
    }

    @Override
    public Boolean removeActivity(ActivityBean activityBean) {
        List<Boolean> resultList = new ArrayList<>();
        new Thread(() -> {
            resultList.add(activityBeanList.remove(activityBean));
        }).start();
        return resultList.stream().allMatch(val -> val.equals(Boolean.TRUE));

    }

    @Override
    public abstract void updateTimeDiscount();
}
