package Spring.event;

import Spring.event.event.AbstractContextEvent;
import Spring.event.listener.ContextListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 15:48
 */
public class SimpleApplicationEventMulticaster implements ApplicationEventMulticaster {

    // 是否异步发布事件
    private boolean async = false;
    // 线程池
    private Executor taskExecutor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    // 事件监听器列表
    private List<ContextListener<?>> contextListeners = new ArrayList<ContextListener<?>>();


    @Override
    public void addContextListener(ContextListener<?> listener) {
        contextListeners.add(listener);
    }

    @Override
    public void removeContextListener(ContextListener<?> listener) {
        contextListeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        contextListeners.clear();
    }

    @Override
    public void multicastEvent(AbstractContextEvent event) {
        doMulticastEvent(contextListeners, event);
    }

    private void doMulticastEvent(List<ContextListener<?>> contextListeners, AbstractContextEvent event) {
        for (ContextListener contextListener : contextListeners) {
            // 异步广播事件
            if (async) {
                taskExecutor.execute(() -> invokeListener(contextListener, event));
                // new Thread(() -> invokeListener(contextListener, event)).start();
                // 同步发布事件，阻塞的方式
            } else {
                invokeListener(contextListener, event);
            }
        }
    }

    private void invokeListener(ContextListener contextListener, AbstractContextEvent event) {
        contextListener.onApplicationEvent(event);
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

}
