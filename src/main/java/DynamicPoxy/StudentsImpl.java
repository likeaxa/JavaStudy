package DynamicPoxy;

/**
 * @Classname StudentsImpl
 * @Date 2019/8/29 22:47
 * @Created by yaoxinjian
 */
public class StudentsImpl implements Students {

    @Override
    public void write() {
        System.out.println("I can write");
    }

    @Override
    public void read() {
        System.out.println("I can read");
    }
}
