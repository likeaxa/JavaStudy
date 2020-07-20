package regularExpression;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author by xinjian.yao
 * @date 2020/7/7 11:23
 */
public class Test1 {
    public static void main(String[] args) {

        Pattern compile = Pattern.compile("asc|desc");
        System.out.println(compile.matcher("asc").matches());
        System.out.println(compile.matcher("desc").matches());
        System.out.println(compile.matcher("aass").matches());
        System.out.println(compile.matcher("ascd").matches());
    }




}
