package com.gordon.blog.controller;

import com.gordon.blog.entity.Catalog;
import com.gordon.blog.service.BlogService;
import com.gordon.blog.entity.Blog;
import com.gordon.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by gordon.zhang on 2018/2/8.
 */
@Controller
@RequestMapping
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CatalogService catalogService;

    @RequestMapping("/blogs")
    public String listBlogsByOrder(@RequestParam(value = "order",required = false,defaultValue = "new")String order,
                                   @RequestParam(value = "pageIndex",required = false,defaultValue = "0")int pageIndex,
                                   @RequestParam(value = "pageSize",required = false,defaultValue = "10")int pageSize,
                                   Model model)
    {
        Page<Blog> page = null;

        if (order.equals("new")){
            Sort sort = new Sort(Sort.Direction.DESC,"publishTime");
            Pageable pageable = new PageRequest(pageIndex,pageSize,sort);
            page = blogService.listBlogs(pageable);
        }else if(order.equals("hot")){
            Sort sort = new Sort(Sort.Direction.DESC,"readSize");
            Pageable pageable = new PageRequest(pageIndex,pageSize,sort);
            page = blogService.listBlogs(pageable);
        }

        List<Blog> list = page.getContent();

        model.addAttribute("order",order);
        model.addAttribute("page",page);
        model.addAttribute("blogList",list);

        List<Catalog> catalogs = catalogService.findAll();
        model.addAttribute("catalogs",catalogs);

        return "index";
    }

    @RequestMapping("/blogs/catalogId/{id}")
    public String listBlogsByCatalog(@PathVariable("id") int catalogId,
                                     @RequestParam(value = "pageIndex",required = false,defaultValue = "0") int pageIndex,
                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
                                     Model model)
    {
        Page<Blog> page = null;

        Pageable pageable = new PageRequest(pageIndex,pageSize);
        page = blogService.listBlogsByCatalog(catalogId,pageable);

        List<Blog> list = page.getContent();

        model.addAttribute("catalogId",catalogId);
        model.addAttribute("page",page);
        model.addAttribute("blogList",list);

        List<Catalog> catalogs = catalogService.findAll();
        model.addAttribute("catalogs",catalogs);

        return "index";
    }

}