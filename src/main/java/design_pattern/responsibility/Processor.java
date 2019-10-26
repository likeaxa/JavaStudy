package design_pattern.responsibility;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:09
 */
public interface Processor {
    Processor getNextProcessor();

    void process(String param);
}
