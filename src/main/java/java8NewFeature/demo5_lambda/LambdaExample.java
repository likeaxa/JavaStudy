package java8NewFeature.demo5_lambda;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jerrysheh
 *
 * lambda表达式的作用：1. 简化匿名类
 *                     2. 事件处理
 *                     3. 对列表进行迭代
 */
public class LambdaExample {

    class jump implements Runnable {
        public void run(){
            System.out.println("jump now");
        }
    }

    /**
     * 1. 使用 lambda 表达式简化匿名类
     */
    @Test
    public void simplifyAnonymousClass(){
        //不使用匿名类
        Runnable r = new jump();
        Thread t1 = new Thread(r);
        t1.start();

        //使用匿名类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("swim now");
            }
        }).start();

        //使用 lambda 表达式
        new Thread( () -> System.out.println("go away now")).start();
    }

    /**
     * 2. 使用 lambda 表达式做事件处理
     */
    @Test
    public void eventHandle(){
        // Java 8之前：
        JButton show =  new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });

        // Java 8方式：
        show.addActionListener((e) -> System.out.println("Light, Camera, Action !! Lambda expressions Rocks"));
    }

    /**
     * 3. 使用 lambda 表达式对列表进行迭代
     */
    @Test
    public void iterateList(){
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        System.out.println("----------------");

        // Java 8之后：
        List<String> features2 = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(s -> System.out.println(s));

        System.out.println("----------------");

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示
        // 看起来像C++的作用域解析运算符
        features2.forEach(System.out::println);
    }

}


