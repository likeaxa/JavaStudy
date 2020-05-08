package Spring.event.listener;

import Spring.event.event.AbstractContextEvent;
import Spring.event.event.ContextRunningEvent;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 15:40
 */
public class ContextRunningEventListener implements ContextListener<AbstractContextEvent> {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextRunningEvent) {
            System.out.println("容器开始运行。。。");
            try {
                Thread.sleep(3000);
                System.out.println("容器运行结束。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
