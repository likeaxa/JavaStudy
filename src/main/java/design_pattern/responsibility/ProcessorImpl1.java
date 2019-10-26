package design_pattern.responsibility;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:33
 */
public class ProcessorImpl1 extends AbstractProcessor {
    public ProcessorImpl1(Processor processor) {
        super(processor);
    }
    @Override
    public void process(String param) {
        System.out.println("processor 1 is processing:" + param);
        if (getNextProcessor() != null) {
            getNextProcessor().process(param);
        }

    }
}
