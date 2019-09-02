package DynamicPoxy.demo1;

import java.lang.reflect.Proxy;

/**
 * @Classname Main
 * @Date 2019/8/29 22:54
 * @Created by yaoxinjian
 */
public class Main {
    public static void main(String[] args) {
        Students students = new StudentsImpl();
        ClassLoader classLoader = students.getClass().getClassLoader();
        Class<?>[] interfaces = students.getClass().getInterfaces();
        InvocationImpl invocation = new InvocationImpl(students);
        Students proxy = (Students) Proxy.newProxyInstance(classLoader, interfaces, invocation);
        System.out.println("动态代理d对象的类型 ：" + proxy.getClass().getName());
        proxy.write();
        System.out.println("=======================");
        proxy.read();
    }
}
