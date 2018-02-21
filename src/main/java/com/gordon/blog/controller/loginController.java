package com.gordon.blog.controller;

import com.gordon.blog.entity.Blog;
import com.gordon.blog.entity.User;
import com.gordon.blog.service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * Created by gordon.zhang on 2018/2/10.
 */
@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    private BlogService blogService;

    @RequestMapping
    public String login(){
        return "login";
    }


    @PostMapping
    public String loginUser(String username,String password,boolean rememberMe,Model model) throws Exception{
        //将用户信息封装到UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password,rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            User user=(User) subject.getPrincipal();
            int pageIndex = 0;
            int pageSize = 10;
            Pageable pageable = new PageRequest(pageIndex,pageSize);
            Page<Blog> blogList = blogService.listBlogsByUserId(user.getUid(),pageable);
            model.addAttribute("user", user);
            model.addAttribute("blogList",blogList);
            return "userspace/u";/**/
        } catch(Exception e) {
            return "login";//返回登录页面
        }
    }


}
