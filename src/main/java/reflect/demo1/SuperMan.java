package reflect.demo1;

/**
 * @Classname SuperMan
 * @Date 2019/8/29 14:51
 * @Created by yaoxinjian
 */
public class SuperMan implements Action {
    private boolean blueBriefs;

    public void fly()
    {
        System.out.println("超人会飞耶～～");
    }

    public boolean isBlueBriefs() {
        return blueBriefs;
    }
    public void setBlueBriefs(boolean blueBriefs) {
        blueBriefs = blueBriefs;
    }

    @Override
    public void walk(int m) {
        // TODO Auto-generated method stub
        System.out.println("超人会走耶～～走了" + m + "米就走不动了！");
    }
}
