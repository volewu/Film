package com.vole.film.controller.admin;

import com.vole.film.entity.WebSite;
import com.vole.film.service.WebSiteInfoService;
import com.vole.film.service.WebSiteService;
import com.vole.film.util.StringUtil;

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

    @Resource
    private WebSiteInfoService webSiteInfoService;

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
        boolean flag = true;
        for (String anIdsStr : idsStr) {
            Integer webSiteId = Integer.parseInt(anIdsStr);
            if (webSiteInfoService.getByWebSiteId(webSiteId).size() > 0)
                flag = false;
            else
                webSiteService.delete(webSiteId);

        }
        if (flag) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("errorInfo", "电影动态信息中存在电影信息，不能删除！");
        }
        return result;
    }
    /**
     * 下拉框模糊查询用到
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/comboList")
    public List<WebSite> comboList(String q)throws Exception{
        if(StringUtil.isEmpty(q)){
            return null;
        }
        WebSite webSite=new WebSite();
        webSite.setUrl(q);
        return webSiteService.list(webSite, 0, 30); // 最多查询30条记录
    }

}
