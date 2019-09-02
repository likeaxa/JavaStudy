package Spring.demo1.main;

import Spring.demo1.ioc.ClassPathXmlApplicationContext;
import Spring.demo1.service.UserService;

/**
 * @Classname test
 * @Date 2019/9/2 11:24
 * @Created by yaoxinjian
 */
public class test {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.say();
    }
}
