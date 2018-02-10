package com.gordon.blog.service.impl;

import com.gordon.blog.dao.CatalogRepository;
import com.gordon.blog.entity.Catalog;
import com.gordon.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gordon.zhang on 2018/2/9.
 */
@Service
public class CatalogServiceImpl implements CatalogService{

    @Autowired
    private CatalogRepository catalogRepository;


    @Override
    public List<Catalog> findAll() {
        return catalogRepository.findAll();
    }
}
