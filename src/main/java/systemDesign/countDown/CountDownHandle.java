package systemDesign.countDown;

/**
 * @author by xinjian.yao
 * @date 2020/1/17 17:51
 */
public interface CountDownHandle {

    /**
     * 注册活动
     * @param activityBean
     * @return
     */
    Boolean registeredActivity(ActivityBean activityBean);

    /**
     * 删除活动
     * @param activityBean
     * @return
     */
    Boolean removeActivity(ActivityBean activityBean);

    /**
     * 更新距离活动的时间
     * @return
     */
    void updateTimeDiscount();
}
