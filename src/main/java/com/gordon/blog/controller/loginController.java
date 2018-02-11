package com.gordon.blog.controller;

import com.gordon.blog.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by gordon.zhang on 2018/2/10.
 */
@Controller
@RequestMapping("/login")
public class loginController {


    @RequestMapping
    public String login(){
        return "login";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String loginUser(String username,String password,Model model) throws Exception{
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            User user=(User) subject.getPrincipal();
            model.addAttribute("user", user);
            return "/userspace/u";
        } catch(Exception e) {
            return "login";//返回登录页面
        }
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        session.removeAttribute("user");
        return "login";
    }
}
