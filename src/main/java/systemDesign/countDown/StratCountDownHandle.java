package systemDesign.countDown;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by xinjian.yao
 * @date 2020/1/17 19:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StratCountDownHandle extends AbstractCountDownHandle implements Runnable{

    private ActivityExecutor activityExecutor;

    private static final Long TIME_DISTANCE = 2L;



    StratCountDownHandle(List<ActivityBean> valList,
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
        if (super.getActivityBeanList().isEmpty() || activityIsExits) {
            return;
        }
        super.getActivityBeanList().sort(Comparator.comparing(ActivityBean::getStartTimeDistance));
        Long sleepTime = super.getActivityBeanList().get(0).getStartTimeDistance();
        if (sleepTime <= 0) {
            return;
        }
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
        while (true) {

            // 当没有活动进来时  查询卡在这里的死循环下
            while (activityIsExits) {
                inits();
                // 更新时间
                updateTimeDiscount();
                // 活动倒计时
                callbackActivityExecutor(activityCanStart());
            }

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


    @Override
    public void run() {
        // 更新时间
        updateTimeDiscount();
        // 活动倒计时
        callbackActivityExecutor(activityCanStart());
        //睡眠
        try {
            sleepMinStartDistance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
