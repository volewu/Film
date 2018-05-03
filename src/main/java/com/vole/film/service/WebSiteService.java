package com.vole.film.service;

import com.vole.film.entity.WebSite;

import java.util.List;

/**
 * 编写者： vole
 * Time： 2018/5/2.10:21
 * 内容：收录电影网址 Service 接口
 */
public interface WebSiteService {

    /**
     * 分页查询收录电影网址
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<WebSite> list(WebSite webSite, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     *
     * @return
     */
    Long getCount(WebSite webSite);

    /**
     * 添加或者修改收录电影网址
     *
     * @param webSite
     */
    void save(WebSite webSite);

    /**
     * 根据 id 删除收录电影网址
     *
     * @param id
     */
    void delete(Integer id);

    List<WebSite> newestList(Integer page, Integer pageSize);
}
