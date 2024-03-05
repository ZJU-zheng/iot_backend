package cn.edu.zju.cs.bs.service.impl;

import cn.edu.zju.cs.bs.mapper.UserMapper;
import cn.edu.zju.cs.bs.pojo.User;
import cn.edu.zju.cs.bs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User selectByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        return user;
    }

    @Override
    public User selectByEmail(String email) {
        User user = userMapper.selectByEmail(email);
        return user;
    }

    @Override
    public void register(String username, String email, String password) {
        userMapper.insertUser(username,email,password);
    }

    @Override
    public void updateEmail(String username, String email) {
        userMapper.updateEmail(username, email);
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        userMapper.updatePassword(username, oldPassword, newPassword);
    }
}
