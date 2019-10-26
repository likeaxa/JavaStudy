package design_pattern.responsibility.java8Impl;

import java.util.function.Consumer;

/**
 * @author by xinjian.yao
 * @date 2019/10/26 23:50
 */
@FunctionalInterface
public interface NewProcessor {
    Consumer<String> process(String param);
}
