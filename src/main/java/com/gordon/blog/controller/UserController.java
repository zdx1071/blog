package com.gordon.blog.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gordon.zhang on 2018/2/9.
 */
@Controller
@RequestMapping("/userInfo")
public class UserController {

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
