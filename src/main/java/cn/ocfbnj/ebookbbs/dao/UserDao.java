package cn.ocfbnj.ebookbbs.dao;

import cn.ocfbnj.ebookbbs.domain.User;

public interface UserDao {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    void registerUser(User user);
}
