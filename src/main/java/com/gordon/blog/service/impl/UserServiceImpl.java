package com.gordon.blog.service.impl;

import com.gordon.blog.dao.UserRepository;
import com.gordon.blog.entity.User;
import com.gordon.blog.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gordon.zhang on 2018/2/10.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
        String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();
        user.setPassword(newPs);
        userRepository.save(user);
    }
}
