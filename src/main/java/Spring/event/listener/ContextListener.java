package Spring.event.listener;

import Spring.event.event.AbstractContextEvent;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 15:36
 */
public interface ContextListener <T extends AbstractContextEvent> extends  EventListener {
    /**
     * 监听到事件后的处理方法
     * @param event 具体的事件
     */
    void onApplicationEvent(T event);

}
