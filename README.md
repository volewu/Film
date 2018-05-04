# Film
#### 一、项目简介：

> 一个采用 SpringBoot 架构的电影聚合 JavaWeb 项目，适用于 SpringBoot 练手项目

#### 二、运行环境

* IDEA
* JDK 8
* MySQL（[数据库链接：db_film](https://github.com/volewu/Film/blob/master/db_cms.sql)）

#### 三、项目技术

* Spring && Spring Boot && Spring Sectuity 

* Spring Data Jpa

* EsayUI + Bootstrap

* thymeleaf

* ckeditor

* 运行截图

  ![film](https://github.com/volewu/Film/blob/master/preview/film.gif?raw=true)

  ​

#### 四、姿势点

##### 1、 SpringSecurity 中得到登入的用户名

```html
th:text="${#httpServletRequest.remoteUser}"
```

##### 2、thymeleaf 问题

```html
/*<![CDATA[*/
中不扫描该注释中的代码

 /*]]>*/

//时间转换
${#dates.format(film.publishDate,'yyyy-MM-dd HH:mm:ss')}
```

##### 3、jpa 中格式化时间 [CustomDateSerializer.java](https://github.com/volewu/Film/blob/master/src/main/java/com/vole/film/util/CustomDateSerializer.java)

```java
@JsonSerialize(using = CustomDateSerializer.class)
public Date getPublishDate() {
        return publishDate;
    }
```
##### 4、jpa 中 Repository 自定义方法 [WebSiteInfoRepository.java](https://github.com/volewu/Film/blob/master/src/main/java/com/vole/film/repository/WebSiteInfoRepository.java)

```java
// 1 代表第一个参数
@Query(value = "select * from t_info where film_id=?1", nativeQuery = true)
List<WebSiteInfo> getByFilmId(Integer filmId);
```

##### 5、jpa 中模糊查询拼接 [FilmServiceImpl.java](https://github.com/volewu/Film/blob/master/src/main/java/com/vole/film/service/impl/FilmServiceImpl.java)

```java
 @Override
 public List<Film> list(Film film, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "publishDate");
        Page<Film> filmPage = filmRepository.findAll((root, criteriaQuery, cb) -> {
            Predicate predicate = cb.conjunction();
            if (film != null) {
                if (StringUtil.isNotEmpty(film.getName()))
                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + film.getName().trim() + "%"));
            }
            return predicate;
        }, pageable);
        return filmPage.getContent();
    }

//controller 调用时 page 参数要减一
filmService.list(film, page - 1, rows);
```

##### 6 、jQuery 中文档加载完毕后加载数据--模糊查询

```html
<script type="text/javascript">
$(document).ready(function(){

            $('#film').combobox({
                mode:'remote',
                url:'/admin/film/comboList',
                valueField:'id',
                textField:'name',
                delay:100
            });

            $('#webSite').combobox({
                mode:'remote',
                url:'/admin/webSite/comboList',
                valueField:'id',
                textField:'url',
                delay:100
            });

        });
</script>
```

##### 7. SQL 语法

```java
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
```

