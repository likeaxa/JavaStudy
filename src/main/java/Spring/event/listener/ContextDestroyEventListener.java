package Spring.event.listener;

import Spring.event.event.AbstractContextEvent;
import Spring.event.event.ContextDestroyEvent;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 15:41
 */
public class ContextDestroyEventListener implements ContextListener<AbstractContextEvent> {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextDestroyEvent) {
            System.out.println("容器销毁。。。，销毁时间为：" + event.getTimestamp());
        }
    }
}
