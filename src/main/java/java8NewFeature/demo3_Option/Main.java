package java8NewFeature.demo3_Option;

import java.util.Optional;

/**
 * @Classname Main
 * @Date 2019/9/2 15:37
 * @Created by yaoxinjian
 */
public class Main {
    public static void main(String[] args) {

        //of（）：为非null的值创建一个Optional
        Optional<String> optional = Optional.of("bam");
        // isPresent（）： 如果值存在返回true，否则返回false
        optional.isPresent();
        //get()：如果Optional有值则将其返回，否则抛出NoSuchElementException
        optional.get();
        //orElse（）：如果有值则将其返回，否则返回指定的其它值
        optional.orElse("fallback");
        //ifPresent（）：如果Optional实例有值则为其调用consumer，否则不做处理
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));

        Person person = new Person("111","222");
        Person person2 = new Person("333","222");
        Person person3 = new Person("444","222");
        person2.setPerson(person3);
        person.setPerson(person2);
        String s = preMethod(person);
        System.out.println(s);

        String s1 = optionalMethod(person);
        System.out.println(s1);

    }
    private static String optionalMethod(Person person){
        // 这样就很优雅了
        return  Optional.ofNullable(person)
                .map(person1 -> person.getPerson())
                .map(person1 -> person.getPerson())
                .map(person1 -> person.getFirstName())
                .orElseThrow(()->new RuntimeException("null"));
    }
    private static  String preMethod(Person person){
        // 以前你判断空指针你可能这么判断
        // 当代码很多时 看起来只会很恶心
        //
        Person person1 = person.getPerson();
        if(null!=person1){
            Person person2 = person1.getPerson();
            if(null!=person2){
                String firstName = person2.getFirstName();
                if(null!=firstName){
                    return firstName;
                }
            }
        }
        return "null";
    }
}
