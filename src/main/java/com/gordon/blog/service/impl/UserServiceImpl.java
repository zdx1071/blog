package com.gordon.blog.service.impl;

import com.gordon.blog.dao.UserRepository;
import com.gordon.blog.entity.User;
import com.gordon.blog.service.UserService;
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
}
