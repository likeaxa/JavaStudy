package reflect.demo3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Classname TypeDeleted
 * @Date 2019/8/30 11:28
 * @Created by yaoxinjian
 */

// 观察泛型擦除
public class TypeDeleted {
    /**
     * 在jdk定义一个List<Integer> 的list 理论上是不能够添加不是Integer类型的对象的
     * 但是通过反射获取list的add方法 然后通过反射去add 是可以添加不同类型的元素的
     * 也就是说这时候的泛型是没有效果的
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> testList = new ArrayList<>();
        testList.add(111);
        // Objects是一个工具类 Object 才是所有的对象隐式继承的类
        Method add = testList.getClass().getDeclaredMethod("add", Object.class);
        add.invoke(testList,"sss");
        add.invoke(testList,false);
        add.invoke(testList,new Object());
        // 遍历输出所有的元素 发现不是list 没有达到使用泛型的效果
        for (Object s :testList){
            System.out.println(s);
        }

    }

}
