package com.gordon.blog.controller;

import com.gordon.blog.entity.Blog;
import com.gordon.blog.entity.User;
import com.gordon.blog.service.BlogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by gordon.zhang on 2018/2/9.
 */
@Controller
@RequestMapping("/userspace")
public class UserController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/u")
    public String userSpace(
            @RequestParam(value = "pageIndex",required = false,defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
            Model model){
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        Pageable pageable = new PageRequest(pageIndex,pageSize);
        Page<Blog> blogList = blogService.listBlogsByUserId(user.getUid(),pageable);
        model.addAttribute("user", user);
        model.addAttribute("blogList",blogList);
        return "userspace/u";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model){
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        model.addAttribute("user", user);
        return "userspace/profile";
    }


}
