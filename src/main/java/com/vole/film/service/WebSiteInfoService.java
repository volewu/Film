package com.vole.film.service;

import com.vole.film.entity.WebSiteInfo;

import java.util.List;

/**
 * 编写者： vole
 * Time： 2018/5/3.08:44
 * 内容：电影动态信息 Service 接口
 */
public interface WebSiteInfoService {

    /**
     * 添加或者修改电影动态信息
     * @param webSiteInfo
     */
    void save(WebSiteInfo webSiteInfo);

    /**
     * 分页查询电影动态信息
     * @param webSiteInfo
     * @param page
     * @param pageSize
     * @return
     */
    List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     * @param webSiteInfo
     * @return
     */
    Long getCount(WebSiteInfo webSiteInfo);

    /**
     * 根据电影id查询动态信息
     * @param filmId
     * @return
     */
    List<WebSiteInfo> getByFilmId(Integer filmId);

    /**
     * 根据电影网址id查询电影动态信息
     * @param webSiteId
     * @return
     */
    List<WebSiteInfo> getByWebSiteId(Integer webSiteId);

    /**
     * 根据 id 删除电影动态信息
     * @param id
     */
    void delete(Integer id);

}
