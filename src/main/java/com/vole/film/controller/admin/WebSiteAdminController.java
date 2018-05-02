package com.vole.film.controller.admin;

import com.vole.film.entity.WebSite;
import com.vole.film.service.WebSiteService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 编写者： vole
 * Time： 2018/5/2.10:38
 * 内容：
 */
@RestController
@RequestMapping("/admin/webSite")
public class WebSiteAdminController {

    @Resource
    private WebSiteService webSiteService;

    @RequestMapping("/list")
    public Map<String, Object> list(WebSite webSite, @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<WebSite> webSiteList = webSiteService.list(webSite, page - 1, rows);
        Long total = webSiteService.getCount(webSite);
        result.put("rows", webSiteList);
        result.put("total", total);
        return result;
    }

    @RequestMapping("/save")
    public Map<String, Object> save(WebSite webSite) throws Exception {
        Map<String, Object> result = new HashMap<>();
        webSiteService.save(webSite);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        System.out.println(ids);
        String[] idsStr = ids.split(",");
        Map<String, Object> result = new HashMap<>();
        for (String anIdsStr : idsStr) {
            webSiteService.delete(Integer.parseInt(anIdsStr));
        }
        result.put("success", true);
        return result;
    }

}
