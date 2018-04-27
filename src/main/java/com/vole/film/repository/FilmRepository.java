package com.vole.film.repository;

import com.vole.film.entity.Film;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 编写者： vole
 * Time： 2018/4/27.10:33
 * 内容：电影 Repository 接口
 */
public interface FilmRepository extends JpaRepository<Film,Integer> {

}
