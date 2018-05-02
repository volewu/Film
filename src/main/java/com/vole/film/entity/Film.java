package com.vole.film.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 编写者： vole
 * Time： 2018/4/26.08:39
 * 内容：电影内容实体
 */
@Entity
@Table(name = "t_film")
public class Film {

    @Id
    @GeneratedValue
    private Integer id; //编号

    @Column(length = 200)
    private String name;//电影名称

    @Column(length = 500)
    private String title;// 电影标题

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;//电影详细内容

    @Column(length = 300)
    private String imageName;//电影图片

    private Integer hot;//是否热门电影 1 热门  0 非热门

    private Date publishDate;//发布日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
