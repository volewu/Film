package com.vole.film.service.impl;

import com.vole.film.entity.WebSite;
import com.vole.film.repository.WebSiteRepository;
import com.vole.film.service.WebSiteService;
import com.vole.film.util.StringUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

/**
 * 编写者： vole
 * Time： 2018/5/2.10:22
 * 内容：收录电影网址Service实现类
 */
@Service
public class WebSiteServiceImpl implements WebSiteService {

    @Resource
    private WebSiteRepository webSiteRepository;

    @Override
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "id");
        Page<WebSite> webSitePage = webSiteRepository.findAll((root, criteriaQuery, cb) -> {
            Predicate predicate = cb.conjunction();
            if (webSite != null) {
                if (StringUtil.isNotEmpty(webSite.getName()))
                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + webSite.getName().trim() + "%"));
                if (StringUtil.isNotEmpty(webSite.getUrl()))
                    predicate.getExpressions().add(cb.like(root.get("url"), "%" + webSite.getUrl().trim() + "%"));
            }
            return predicate;
        }, pageable);
        return webSitePage.getContent();
    }

    @Override
    public Long getCount(WebSite webSite) {
        Long count = webSiteRepository.count((root, criteriaQuery, cb) -> {
            Predicate predicate=cb.conjunction();
            if(webSite!=null){
                if(StringUtil.isNotEmpty(webSite.getName())){
                    predicate.getExpressions().add(cb.like(root.get("name"), "%"+webSite.getName().trim()+"%"));
                }
                if(StringUtil.isNotEmpty(webSite.getUrl())){
                    predicate.getExpressions().add(cb.like(root.get("url"), "%"+webSite.getUrl().trim()+"%"));
                }
            }
            return predicate;
        });
        return count;
    }

    @Override
    public void save(WebSite webSite) {
        webSiteRepository.save(webSite);
    }

    @Override
    public void delete(Integer id) {
        webSiteRepository.delete(id);
    }

    @Override
    public List<WebSite> newestList(Integer page, Integer pageSize) {
        Pageable pageable=new PageRequest(page, pageSize,Sort.Direction.DESC,"id");
        return webSiteRepository.findAll(pageable).getContent();
    }
}
