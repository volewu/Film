package com.vole.film.repository;

import com.vole.film.entity.WebSite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 编写者： vole
 * Time： 2018/5/2.10:17
 * 内容：收录电影网址 Repostory 接口
 */
public interface WebSiteRepository extends JpaRepository<WebSite, Integer>, JpaSpecificationExecutor<WebSite> {
}
