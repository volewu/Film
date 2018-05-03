# Film
#### 一、简介：

基于 Java  的电影聚合系统，采用 Springboot , MySQL , Thymeleaf .....(正在召唤ing.....)

#### 二、姿势点

##### 1、 SpringSecurity 中得到登入的用户名

```html
th:text="${#httpServletRequest.remoteUser}"
```

##### 2、thymeleaf 中不扫描该注释中的代码

```html
/*<![CDATA[*/


 /*]]>*/
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

