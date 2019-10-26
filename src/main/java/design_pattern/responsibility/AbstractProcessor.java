package design_pattern.responsibility;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:10
 */
public abstract  class AbstractProcessor implements Processor  {
    private Processor next;
    public AbstractProcessor(Processor processor) {
        this.next = processor;
    }
    @Override
    public Processor getNextProcessor() {

        return next;
    }

    @Override
    public abstract void process(String param) ;
}
