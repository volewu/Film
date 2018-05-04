package com.vole.film.service;

import com.vole.film.entity.Film;

import java.util.List;

/**
 * 编写者： vole
 * Time： 2018/4/27.10:35
 * 内容：电影信息 Service 接口
 */
public interface FilmService {

    /**
     * 添加或者修改电影
     *
     * @param film
     */
    void save(Film film);

    /**
     * 分页查询电影信息
     *
     * @param film
     * @param page
     * @param pageSize
     * @return
     */
    List<Film> list(Film film, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     * @return
     */
    Long getCount(Film film);

    /**
     * 根据 id 删除收录电影信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据 id 查找电影信息
     * @param id
     */
    Film findById(Integer id);

    /**
     * 根据 id 获取上一个数据
     * @param id
     */
    Film getLastFilm(Integer id);

    /**
     * 根据 id 获取下一个数据
     * @param id
     */
    Film getNextFilm(Integer id);

    /**
     * 随机取几条数据
     * @return
     */
    List<Film> randomList(Integer n);

}
