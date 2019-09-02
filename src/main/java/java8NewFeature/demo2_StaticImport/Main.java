package java8NewFeature.demo2_StaticImport;

/**
 * @Classname Main
 * @Date 2019/9/2 15:10
 * @Created by yaoxinjian
 */

/**
 * 使用静态导入的构造方法
 */
public class Main {
    public static void main(String[] args) {
        // 使用 `Person::new` 来获取Person类构造函数的引用，
        // Java编译器会自动根据`PersonFactory.create`方法的参数类型来选择合适的构造函数
        // 反过来 相当于create的实现由Person的构造方法完成
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("first", "last");
        System.out.println(person.getFirstName()+"--"+person.getLastName());
    }
}
