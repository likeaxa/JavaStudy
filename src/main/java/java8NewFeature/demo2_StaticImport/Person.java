package java8NewFeature.demo2_StaticImport;

/**
 * @Classname Person
 * @Date 2019/9/2 15:07
 * @Created by yaoxinjian
 */
public class Person {
    private String firstName;
    private String lastName;

    Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


}
