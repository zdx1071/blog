package com.gordon.blog.dao;

import com.gordon.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gordon.zhang on 2018/2/8.
 */
public interface BlogRepository extends JpaRepository<Blog,Long>{


    Page<Blog> findByCatalogId(Long catalogId, Pageable pageable);

    Page<Blog> findByUserUid(Long userUid, Pageable pageable);

}
