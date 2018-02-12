package com.gordon.blog.controller;

import com.gordon.blog.entity.User;
import com.gordon.blog.service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by gordon.zhang on 2018/2/9.
 */
@Controller
@RequestMapping("/userspace")
public class UserController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/u")
    public String userSpace(Model model){
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        model.addAttribute("user", user);
        return "userspace/u";
    }

    @RequestMapping("/userList")
    public String userInfo(){
        return "userInfo";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String userAdd(){
        return "userInfoAdd";
    }

    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){
        return "userInfoDel";
    }
}
