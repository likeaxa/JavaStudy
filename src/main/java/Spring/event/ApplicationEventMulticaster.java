package Spring.event;

import Spring.event.event.AbstractContextEvent;
import Spring.event.listener.ContextListener;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 15:44
 */
public interface ApplicationEventMulticaster {

    void addContextListener(ContextListener<?> listener);

    void removeContextListener(ContextListener<?> listener);

    void removeAllListeners();

    void multicastEvent(AbstractContextEvent event);

}
