package systemDesign.countDown;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * @author by xinjian.yao
 * @date 2020/1/17 19:02
 */
public class StratCountDownHandle extends AbstractCountDownHandle {


    public StratCountDownHandle(List<ActivityBean> valList) {
        super(valList);
    }

    @Override
    public void updateTimeDiscount() {
       super.getActivityBeanList().forEach(activityBean -> {
           LocalDateTime startTime = activityBean.getStartTime();
           long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), startTime);
           activityBean.setStartTimeDistance(seconds);
       });
    }

}
