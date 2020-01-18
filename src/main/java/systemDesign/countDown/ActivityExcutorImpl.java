package systemDesign.countDown;

/**
 * @author by xinjian.yao
 * @date 2020/1/18 16:10
 */
public class ActivityExcutorImpl implements   ActivityExecutor {
    @Override
    public void startTimeExecutor(ActivityBean activityBean) {
        System.out.println(activityBean.getActivityName()+"活动开始了");
    }

    @Override
    public void endTimeExecutor(ActivityBean activityBean) {
        System.out.println(activityBean.getActivityName()+"活动结束了");
    }
}
