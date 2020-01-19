package systemDesign.countDown;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by xinjian.yao
 * @date 2020/1/18 17:10
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        StratCountDownHandle test =
                new StratCountDownHandle(
                        new ArrayList<>(),
                        new ActivityExcutorImpl());
        ActivityBean activityBean = new ActivityBean();
        activityBean.setActivityName("n元n件");
        activityBean.setStartTime(LocalDateTime.now());
        ActivityBean activityBean1 = new ActivityBean();
        activityBean1.setActivityName("满减");
        activityBean1.setStartTime(LocalDateTime.of(2020, 1, 18, 17, 27));
        test.registeredActivity(activityBean);
        test.registeredActivity(activityBean1);
        new Thread(test).start();

        Thread.sleep(2000);

        ActivityBean activityBean2 = new ActivityBean();
        activityBean2.setActivityName("拼团");
        activityBean2.setStartTime(LocalDateTime.now());
        test.registeredActivity(activityBean2);


    }
}
