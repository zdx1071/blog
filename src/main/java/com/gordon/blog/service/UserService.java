package com.gordon.blog.service;

import com.gordon.blog.entity.User;

/**
 * Created by gordon.zhang on 2018/2/10.
 */
public interface UserService {

    User findByUsername(String username);
}
