package cn.ocfbnj.ebookbbs.service;

import cn.ocfbnj.ebookbbs.domain.User;

public interface UserService {
    boolean register(User user);

    User login(User user);
}
