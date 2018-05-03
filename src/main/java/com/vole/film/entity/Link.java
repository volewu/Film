package com.vole.film.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 编写者： vole
 * Time： 2018/4/25.17:36
 * 内容：友情链接实体
 */
@Entity
@Table(name = "t_link")
public class Link {

    @Id
    @GeneratedValue
    private Integer id; //编号

    @Column(length = 500)
    private String name; //名称

    @Column(length = 500)
    private String url;//地址

    private Integer sort;//排序

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                '}';
    }
}
