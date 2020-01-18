package systemDesign.countDown;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author by xinjian.yao
 * @date 2020/1/17 19:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StratCountDownHandle extends AbstractCountDownHandle {

    private ActivityExecutor activityExecutor;

    private static final Long TIME_DISTANCE = 2L;

    public StratCountDownHandle(List<ActivityBean> valList,
                                ActivityExecutor activityExecutor) {
        super(valList);
        this.activityExecutor = activityExecutor;
    }


    @Override
    public void updateTimeDiscount() {
        super.getActivityBeanList().forEach(activityBean -> {
            LocalDateTime startTime = activityBean.getStartTime();
            long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), startTime);
            activityBean.setStartTimeDistance(seconds);
        });
    }

    private void sleepMinStartDistance() throws InterruptedException {
        if (super.getActivityBeanList().isEmpty()) {
            return;
        }
        super.getActivityBeanList().sort(Comparator.comparing(ActivityBean::getStartTimeDistance));
        Long sleepTime = super.getActivityBeanList().get(0).getStartTimeDistance();
        Thread.sleep(sleepTime * 1000);
    }

    private List<ActivityBean> activityCanStart() {

        List<ActivityBean> resultList = new ArrayList<>();
        super.getActivityBeanList().forEach(activityBean -> {
            if (activityBean.getStartTimeDistance() >= TIME_DISTANCE) {
                return;
            }
            resultList.add(activityBean);
        });
        return resultList;
    }

    private void callbackActivityExecutor(List<ActivityBean> activityBeanList) {
        activityBeanList.forEach(activityBean -> {
            activityExecutor.startTimeExecutor(activityBean);
            super.removeActivity(activityBean);
        });
    }

    public void executor() throws InterruptedException {
        while (true){
            inits();
            // 更新时间
            updateTimeDiscount();
            // 活动倒计时
            callbackActivityExecutor(activityCanStart());
        }

    }

    private void inits() throws InterruptedException {
        // 更新时间
        updateTimeDiscount();
        // 活动倒计时
        callbackActivityExecutor(activityCanStart());
        //睡眠
        sleepMinStartDistance();
    }


}
