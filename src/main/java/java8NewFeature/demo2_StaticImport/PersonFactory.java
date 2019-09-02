package java8NewFeature.demo2_StaticImport;

/**
 * @Classname PersonFactory
 * @Date 2019/9/2 15:07
 * @Created by yaoxinjian
 */
public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
