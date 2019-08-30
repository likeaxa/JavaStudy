package reflect.demo2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Classname UserCaseTracker
 * @Date 2019/8/29 17:04
 * @Created by yaoxinjian
 */
public class UserCaseTracker {
    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
    private static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        //遍历cl中的所有方法

        for(Method m : cl.getDeclaredMethods()) {
            UserCase uc = m.getAnnotation(UserCase.class);
            if(uc != null) {
                //获取UserCase的属性
                System.out.println("Found Use Case: " + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for(int i : useCases) {
            System.out.println("Warning: Missing use case  " + i);
        }
    }
}
