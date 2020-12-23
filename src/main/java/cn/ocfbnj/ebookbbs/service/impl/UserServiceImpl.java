package cn.ocfbnj.ebookbbs.service.impl;

import cn.ocfbnj.ebookbbs.dao.UserDao;
import cn.ocfbnj.ebookbbs.dao.impl.UserDaoImpl;
import cn.ocfbnj.ebookbbs.domain.User;
import cn.ocfbnj.ebookbbs.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean registerUser(User user) {
        int num = userDao.saveUser(user);
        return num > 0;
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
