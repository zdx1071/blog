package com.gordon.blog.controller;

import com.gordon.blog.entity.Catalog;
import com.gordon.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by gordon.zhang on 2018/2/9.
 */
@Controller
@RequestMapping
public class MainController {



    @RequestMapping("/")
    public String index(Model model){
        return "redirect:/blogs";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
}