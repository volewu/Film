package com.vole.film.entity;

/**
 * 编写者： vole
 * Time： 2018/4/25.17:32
 * 内容：用户管理类，采用 SpringSecurity 管理，把认证放入内存中去，不用映射
 */
public class Manager {

    private Integer id; // 编号

    private String userName; // 用户名

    private String password; // 密码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
