package cn.ocfbnj.ebookbbs.test.xd;

import cn.ocfbnj.ebookbbs.dao.UserDao;
import cn.ocfbnj.ebookbbs.dao.impl.UserDaoImpl;
import cn.ocfbnj.ebookbbs.pro.User;
import cn.ocfbnj.ebookbbs.service.UserService;
import cn.ocfbnj.ebookbbs.service.impl.UserServiceImpl;
/*
 *@Public class Test 测试类
 */

public class Test {
    private UserDao userDao = new UserDaoImpl();
    private UserService userService = new UserServiceImpl();

    protected void testUserDao() {
        System.out.println("查询：");
        User user = userDao.queryUserByUsernameAndPassword("zhangsan", "123456");
        if (user == null) {
            System.out.println("用户名或密码错误");
        } else
            System.out.println(user.toString());

        System.out.println("添加用户：");
        User user1 = new User();
        user1.setUsername("huawei");
        user1.setPassword("huawei");
        user1.setEmail("huawei@huawei.com");

        int num = userDao.saveUser(user1);
        if (num == 0) {
            System.out.println("注册失败");
        } else
            System.out.println("注册成功");
    }

    protected void testUserService() {
        User user1 = new User();
        user1.setUsername("huawei" + "ak");
        user1.setPassword("huawei");
        user1.setEmail("huawei@huawei.com");
        boolean num = userService.existsUsername(user1.getUsername());
        if (num == false) {
            System.out.println("该用户名是不存在的。");
            if (userService.registerUser(user1))
                System.out.println("注册成功");
            else
                System.out.println("注册失败");
        } else {
            System.out.println("该用户存在");
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        //test.testUserDao();
        test.testUserService();

    }
}
