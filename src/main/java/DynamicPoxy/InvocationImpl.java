package DynamicPoxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Classname InvocationImpl
 * @Date 2019/8/29 22:49
 * @Created by yaoxinjian
 */
public class InvocationImpl implements InvocationHandler {
    private Object students;

    InvocationImpl(){

    }
    public InvocationImpl(Object students){
        this.students = students;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method invoke");
        Object invoke = method.invoke(students, args);
        System.out.println("after method invoke ");
        return invoke;
    }
}
