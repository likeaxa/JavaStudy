package Spring.event.event;

/**
 * @author by xinjian.yao
 * @date 2020/5/8 15:32
 */
public class AbstractContextEvent implements Event {

    // AbstractContextEvent是容器事件的基本抽象类，因为事件也可以携带数据，因此这里定义了一个timestamp属性，用来记录事件的发生时间
    private static final long serialVersionUID = -6159391039546783871L;

    private final long timestamp = System.currentTimeMillis();

    public final long getTimestamp() {
        return this.timestamp;
    }
}
