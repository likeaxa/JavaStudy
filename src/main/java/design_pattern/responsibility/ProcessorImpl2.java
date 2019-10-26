package design_pattern.responsibility;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:34
 */
public class ProcessorImpl2 extends AbstractProcessor {
    public ProcessorImpl2(Processor next) {
        super(next);
    }

    @Override
    public void process(String param) {
        System.out.println("processor 2 is processing:" + param);
        if (getNextProcessor() != null) {
            getNextProcessor().process(param);
        }
    }
}
