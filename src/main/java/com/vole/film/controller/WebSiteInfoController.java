package com.vole.film.controller;

import com.vole.film.entity.WebSiteInfo;
import com.vole.film.service.WebSiteInfoService;
import com.vole.film.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.annotation.Resource;


/**
 * 编写者： vole
 * Time： 2018/5/4.11:10
 * 内容：电影网站动态 controller
 */
@Controller
@RequestMapping("/webSiteInfo")
public class WebSiteInfoController {

    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 分页查询 电影网站动态信息
     *
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/{id}")
    public ModelAndView list(@PathVariable(value = "id", required = false) Integer page) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(null, page - 1, 20);
        Long total = webSiteInfoService.getCount(null);
        mav.addObject("webSiteInfoList", webSiteInfoList);
        mav.addObject("pageCode", PageUtil.genPagination("/webSiteInfo/list", total, page, 20));
        mav.addObject("title", "电影网站动态信息列表");
        mav.addObject("mainPage", "webSiteInfo/list");
        mav.addObject("mainPageKey", "#f");
        mav.setViewName("index");
        return mav;
    }

}
