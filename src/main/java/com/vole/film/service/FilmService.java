package com.vole.film.service;

import com.vole.film.entity.Film;

/**
 * 编写者： vole
 * Time： 2018/4/27.10:35
 * 内容：电影 Service 接口
 */
public interface FilmService {

    /**
     * 添加或者修改电影
     * @param film
     */
    void save(Film film);

}
