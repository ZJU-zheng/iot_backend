package cn.edu.zju.cs.bs.service;

import cn.edu.zju.cs.bs.pojo.User;

public interface UserService {
    User selectByUsername(String username);

    User selectByEmail(String email);

    void register(String username, String email, String password);

    void updateEmail(String username, String email);

    void updatePassword(String username, String oldPassword, String newPassword);
}
