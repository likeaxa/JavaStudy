package java8NewFeature.demo4_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Classname Main
 * @Date 2019/9/2 16:23
 * @Created by yaoxinjian
 */
public class Main {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("ddd2");
        stringList.add("aaa2");
        stringList.add("bbb1");
        stringList.add("aaa1");
        stringList.add("bbb3");
        stringList.add("ccc");
        stringList.add("bbb2");
        stringList.add("ddd1");

        // 使用Filter 进行过滤
        stringList
                .stream()
                .filter(val -> val.startsWith("a"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("=============================================");
        // 使用Sorted(排序) 如果你不指定一个自定义的 Comparator 则会使用默认排序。
        stringList.stream()
                .sorted()
                .filter(val -> val.startsWith("a"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("=============================================");

        // Map(映射) map 会将元素根据指定的 Function 接口来依次将元素转成另外的对象
        stringList
                .stream()
                .map(String::toUpperCase)
                .sorted(String::compareTo)
                .forEach(System.out::println);
        System.out.println("=============================================");

        // Match(匹配)
        // Stream提供了多种匹配操作，允许检测指定的Predicate是否匹配整个Stream。
        // 所有的匹配操作都是 最终操作 ，并返回一个 boolean 类型的值。
        // 测试 Match (匹配)操作
        boolean anyStartsWithA =
                stringList
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));
        // true
        System.out.println(anyStartsWithA);

        boolean allStartsWithA =
                stringList
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));
        // false
        System.out.println(allStartsWithA);

        boolean noneStartsWithZ =
                stringList
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));
        // true
        System.out.println(noneStartsWithZ);
        System.out.println("=============================================");

        //测试 Count (计数)操作
        long startsWithB =
                stringList
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();
        System.out.println(startsWithB);
        System.out.println("=============================================");
        //测试 Reduce (规约)操作
        Optional<String> reduced =
                stringList
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
        System.out.println("=============================================");


    }
}
