package com.gordon.blog.controller;

import com.gordon.blog.entity.SysRole;
import com.gordon.blog.entity.User;
import com.gordon.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon.zhang on 2018/2/17.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;
    @PostMapping
    public String register(User user,Model model)
    {
        try {
            userService.saveUser(user);
            model.addAttribute(user);
            return "redirect:login";/**/
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:register";/**/
        }
    }
}
