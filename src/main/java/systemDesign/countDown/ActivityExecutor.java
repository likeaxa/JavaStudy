package systemDesign.countDown;

/**
 * @author by xinjian.yao
 * @date 2020/1/17 19:28
 */
public interface ActivityExecutor {

    /**
     * 活动开始时间到了执行的方法
     * @param activityBean
     */
    void startTimeExecutor(ActivityBean activityBean);

    /**
     * 活动结束时间执行的方法
     * @param activityBean
     */
    void endTimeExecutor(ActivityBean activityBean);
}
