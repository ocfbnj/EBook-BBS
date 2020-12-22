package cn.ocfbnj.ebookbbs.service.impl;

import cn.ocfbnj.ebookbbs.dao.UserDao;
import cn.ocfbnj.ebookbbs.dao.impl.UserDaoImpl;
import cn.ocfbnj.ebookbbs.pro.User;
import cn.ocfbnj.ebookbbs.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean registerUser(User user) {
        int num = userDao.saveUser(user);
        if (num > 0)
            return true;
        return false;
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            return false;
        }
        return true;
    }
}
