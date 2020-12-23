package cn.ocfbnj.ebookbbs.service;

import cn.ocfbnj.ebookbbs.domain.User;

public interface UserService {
    public boolean registerUser(User user);

    /**
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用。
     *
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    public boolean existsUsername(String username);
}
