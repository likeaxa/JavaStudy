package java8NewFeature.demo1_FunInterface;

import java8NewFeature.demo2_StaticImport.Person;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Classname Main
 * @Date 2019/9/2 14:32
 * @Created by yaoxinjian
 */
public class Main {
    public static void main(String[] args) {
        // java8 以前
        Formula test = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };
        System.out.println(test.calculate(4));
        System.out.println(test.calculate(100));

        // now you can use lambda expression
        Formula test1 = Math::sqrt;
        System.out.println(test1.calculate(4));
        System.out.println(test1.calculate(100));
        // 这是咱们定义的函数式接口 事实上java已经内置了一些函数式接口
        // 方便我们使用

        // Predicate 接口 接受一个类型-->返回一个boolean
        Predicate<String> predicate = (s) -> s.length() > 0;
        // test()方法接受接口定义的方法，也就是我们用lambda对它进行签名的方法
        // true
        predicate.test("foo");
        // negate()式Predicate的一个默认实现方法，可以直接使用
        predicate.negate().test("foo");

        //Function接口可以理解为来一个T 返回一个R
        // 通俗的说 就是给我一个类型的值 比如（string） 然后我返回一个我想要的类型
        // 可以式string 或是别的 integer

        // 这里采用静态导入的方法
        Function<String, Integer> toInteger = Integer::valueOf;
        // andThen()一个默认实现方法 进去看就知道了
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        // "123"
        backToString.apply("123");

        // Consumer 是消费一个参数 也就是来一个类型 我什么都不返回
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
        greeter.accept(new Person("Luke", "Skywalker"));

        // 其他的以此类推
        // Suppliers 接口不接受一个参数 返回一个参数
        // 通俗的说 就是调用我的方法 我什么都不要，然后我返回给你一个值

    }
}
