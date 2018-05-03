package com.vole.film.controller.admin;

import com.vole.film.entity.WebSiteInfo;
import com.vole.film.service.WebSiteInfoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 编写者： vole
 * Time： 2018/5/3.09:21
 * 内容：电影动态信息 controller
 */
@RestController
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoController {

    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 分页查询电影动态信息
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(WebSiteInfo webSiteInfo, @RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(webSiteInfo, page - 1, rows);
        Long total = webSiteInfoService.getCount(webSiteInfo);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rows", webSiteInfoList);
        resultMap.put("total", total);
        return resultMap;
    }

    /**
     * 添加电影动态信息
     * @param link
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String,Object> save(WebSiteInfo webSiteInfo)throws Exception{
        webSiteInfo.setPublishDate(new Date());
        Map<String,Object> resultMap=new HashMap<>();
        webSiteInfoService.save(webSiteInfo);
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 删除电影动态信息
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam(value="ids")String ids)throws Exception{
        String []idsStr=ids.split(",");
        Map<String,Object> resultMap=new HashMap<>();
        for (String anIdsStr : idsStr) {
            webSiteInfoService.delete(Integer.parseInt(anIdsStr));
        }
        resultMap.put("success", true);
        System.out.println();
        return resultMap;
    }
}
