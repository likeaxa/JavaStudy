package Spring.demo1.dao.impl;

import Spring.demo1.dao.UserDao;

/**
 * @Classname UserDaoImpl
 * @Date 2019/9/2 10:35
 * @Created by yaoxinjian
 */
public class UserDaoImpl implements UserDao {

    // 模拟dao层 和数据库打交道

    @Override
    public void say() {
        System.out.println("i can say hello");
    }
}
