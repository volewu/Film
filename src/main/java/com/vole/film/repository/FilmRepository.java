package com.vole.film.repository;

import com.vole.film.entity.Film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 编写者： vole
 * Time： 2018/4/27.10:33
 * 内容：电影 Repository 接口
 */
public interface FilmRepository extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {

    /**
     * 根据 id 获取上一个数据
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM t_film where id <?1 ORDER BY id DESC limit 1", nativeQuery = true)
    Film getLastFilm(Integer id);

    /**
     * 根据 id 获取下一个数据
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM t_film where id >?1 ORDER BY id ASC limit 1", nativeQuery = true)
    Film getNextFilm(Integer id);

    /**
     * 随机取几条数据
     * @param n
     * @return
     */
    @Query(value = "SELECT * FROM t_film ORDER BY rand() limit ?1",nativeQuery = true)
    List<Film> randomList(Integer n);
}
