package com.vole.film.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 编写者： vole
 * Time： 2018/4/26.08:49
 * 内容：网站动态更新电影信息实体
 */
@Entity
@Table(name = "t_info")
public class WebSiteInfo {

    @Id
    @GeneratedValue
    private Integer id;// 编号

    @ManyToOne
    @JoinColumn(name = "filmId")
    private Film film;// 电影

    @ManyToOne
    @JoinColumn(name = "webSiteId")
    private WebSite webSite;// 电影

    @Column(length = 1000)
    private String info;// 信息

    @Column(length = 500)
    private String url;// 具体网址

    private Date publishDate;// 发布日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
