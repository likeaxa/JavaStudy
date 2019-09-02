package Spring.demo1.service.impl;

import Spring.demo1.dao.UserDao;
import Spring.demo1.service.UserService;

/**
 * @Classname UserServiceImpl
 * @Date 2019/9/2 10:39
 * @Created by yaoxinjian
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    // 模拟@Autowired

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 模拟平时处理业务的service层
     */
    @Override
    public void say() {
        userDao.say();
    }
}
