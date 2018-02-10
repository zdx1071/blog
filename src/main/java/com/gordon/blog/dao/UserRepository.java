package com.gordon.blog.dao;

import com.gordon.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gordon.zhang on 2018/2/10.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsername(String username);
}
