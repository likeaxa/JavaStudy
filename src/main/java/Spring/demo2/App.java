package Spring.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author by xinjian.yao
 * @date 2019/11/12 17:44
 */
public class App {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:bean1.xml");

        System.out.println("context 启动成功");


        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);


        // 这句将输出: hello world
        System.out.println(messageService.getMessage());

    }

}
