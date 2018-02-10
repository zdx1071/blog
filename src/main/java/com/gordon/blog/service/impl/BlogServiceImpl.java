package com.gordon.blog.service.impl;

import com.gordon.blog.dao.BlogRepository;
import com.gordon.blog.service.BlogService;
import com.gordon.blog.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

/**
 * Created by gordon.zhang on 2018/2/8.
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void removeBlog(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Page<Blog> listBlogs(Pageable pageable) {
        Page<Blog> page = null;
        page = blogRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Blog> listBlogsByCatalog(int catalogId, Pageable pageable) {
        Page<Blog> page = null;
        page = blogRepository.findByCatalogId(catalogId,pageable);
        return page;
    }
}
