package Spring.event.listener;

import Spring.event.event.AbstractContextEvent;
import Spring.event.event.ContextStartEvent;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 15:39
 */
public class ContextStartEventListener implements ContextListener<AbstractContextEvent>  {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextStartEvent) {
            System.out.println("容器启动。。。，启动时间为：" + event.getTimestamp());
        }
    }
}
