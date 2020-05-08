package Spring.event;

import Spring.event.event.ContextDestroyEvent;
import Spring.event.event.ContextRunningEvent;
import Spring.event.event.ContextStartEvent;
import Spring.event.listener.ContextDestroyEventListener;
import Spring.event.listener.ContextRunningEventListener;
import Spring.event.listener.ContextStartEventListener;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 15:56
 */
public class Test {
    @org.junit.Test
    public void testContextLifecycleEventInSync() {
        // 新建SimpleApplicationEventMulticaster对象，并添加容器生命周期监听器
        ApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.addContextListener(new ContextStartEventListener());
        eventMulticaster.addContextListener(new ContextRunningEventListener());
        eventMulticaster.addContextListener(new ContextDestroyEventListener());
        // 发射容器启动事件ContextStartEvent
        eventMulticaster.multicastEvent(new ContextStartEvent());
        // 发射容器正在运行事件ContextRunningEvent
        eventMulticaster.multicastEvent(new ContextRunningEvent());
        // 发射容器正在运行事件ContextDestroyEvent
        eventMulticaster.multicastEvent(new ContextDestroyEvent());
    }

}
