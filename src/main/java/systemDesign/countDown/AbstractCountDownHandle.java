package systemDesign.countDown;

import lombok.Data;

import java.util.List;

/**
 * @author by xinjian.yao
 * @date 2020/1/17 19:07
 */
@Data
public abstract class AbstractCountDownHandle implements CountDownHandle {

    private List<ActivityBean> activityBeanList;

    AbstractCountDownHandle(List<ActivityBean> valList){
        this.activityBeanList  = valList;
    }


    @Override
    public Boolean registeredActivity(ActivityBean activityBean) {
        return activityBeanList.add(activityBean);
    }

    @Override
    public Boolean removeActivity(ActivityBean activityBean) {
        return activityBeanList.remove(activityBean);
    }

    @Override
    public abstract void updateTimeDiscount() ;
}
