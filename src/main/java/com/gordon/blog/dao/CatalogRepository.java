package com.gordon.blog.dao;

import com.gordon.blog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gordon.zhang on 2018/2/9.
 */
public interface CatalogRepository extends JpaRepository<Catalog,Long> {
}
