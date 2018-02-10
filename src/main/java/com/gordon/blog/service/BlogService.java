package com.gordon.blog.service;

import com.gordon.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by gordon.zhang on 2018/2/8.
 */
public interface BlogService {

    Blog saveBlog(Blog blog);

    void removeBlog(Long id);

    Blog getBlogById(Long id);

    Page<Blog> listBlogs(Pageable pageable);

    Page<Blog> listBlogsByCatalog(int catalogId,Pageable pageable);



}
