package com.gordon.blog.service;


import com.gordon.blog.entity.Catalog;

import java.util.List;

/**
 * Created by gordon.zhang on 2018/2/9.
 */
public interface CatalogService {

    List<Catalog> findAll();

}
