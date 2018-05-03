package com.vole.film.service.impl;

import com.vole.film.entity.WebSiteInfo;
import com.vole.film.repository.WebSiteInfoRepository;
import com.vole.film.service.WebSiteInfoService;
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
 * Time： 2018/5/3.09:17
 * 内容：电影动态信息 Service 实现类
 */
@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService{

    @Resource
    private WebSiteInfoRepository webSiteInfoRepository;

    @Override
    public void save(WebSiteInfo webSiteInfo) {
        webSiteInfoRepository.save(webSiteInfo);
    }

    @Override
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize) {
        Pageable pageable=new PageRequest(page, pageSize, Sort.Direction.DESC,"publishDate");
        Page<WebSiteInfo> pageWebSite=webSiteInfoRepository.findAll((root, query, cb) -> {
            Predicate predicate=cb.conjunction();
            if(webSiteInfo!=null){
                if(StringUtil.isNotEmpty(webSiteInfo.getInfo())){
                    predicate.getExpressions().add(cb.like(root.get("info"), "%"+webSiteInfo.getInfo().trim()+"%"));
                }
            }
            return predicate;
        }, pageable);
        return pageWebSite.getContent();
    }

    @Override
    public Long getCount(WebSiteInfo webSiteInfo) {
        return webSiteInfoRepository.count((root, query, cb) -> {
            Predicate predicate=cb.conjunction();
            if(webSiteInfo!=null){
                if(StringUtil.isNotEmpty(webSiteInfo.getInfo())){
                    predicate.getExpressions().add(cb.like(root.get("info"), "%"+webSiteInfo.getInfo().trim()+"%"));
                }
            }
            return predicate;
        });
    }

    @Override
    public List<WebSiteInfo> getByFilmId(Integer filmId) {
        return webSiteInfoRepository.getByFilmId(filmId);
    }

    @Override
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId) {
        return webSiteInfoRepository.getByWebSiteId(webSiteId);
    }

    @Override
    public void delete(Integer id) {
        webSiteInfoRepository.delete(id);
    }
}
