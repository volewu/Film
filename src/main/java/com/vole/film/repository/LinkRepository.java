package com.vole.film.repository;

import com.vole.film.entity.Link;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 编写者： vole
 * Time： 2018/4/27.10:33
 * 内容：友情链接  Repository 接口
 */
public interface LinkRepository extends JpaRepository<Link,Integer> {

}
