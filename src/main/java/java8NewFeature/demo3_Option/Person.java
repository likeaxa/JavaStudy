package java8NewFeature.demo3_Option;

/**
 * @Classname Person
 * @Date 2019/9/2 15:07
 * @Created by yaoxinjian
 */
public class Person {
    private String firstName;
    private String lastName;

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

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
