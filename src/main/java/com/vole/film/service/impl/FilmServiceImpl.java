package com.vole.film.service.impl;

import com.vole.film.entity.Film;
import com.vole.film.repository.FilmRepository;
import com.vole.film.service.FilmService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 编写者： vole
 * Time： 2018/4/27.10:36
 * 内容：电影 Service 接口实现类
 */
@Service("filmService")
public class FilmServiceImpl implements FilmService{

    @Resource
    private FilmRepository filmRepository;

    @Override
    public void save(Film film) {
        filmRepository.save(film);
    }
}
