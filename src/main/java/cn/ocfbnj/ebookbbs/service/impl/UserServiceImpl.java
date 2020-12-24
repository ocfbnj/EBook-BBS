package cn.ocfbnj.ebookbbs.service.impl;

import cn.ocfbnj.ebookbbs.dao.UserDao;
import cn.ocfbnj.ebookbbs.dao.impl.UserDaoImpl;
import cn.ocfbnj.ebookbbs.domain.User;
import cn.ocfbnj.ebookbbs.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user) {
        User u = userDao.findByUsername(user.getUsername());

        if (u != null) {
            return false;
        }

        userDao.registerUser(user);

        return true;
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
