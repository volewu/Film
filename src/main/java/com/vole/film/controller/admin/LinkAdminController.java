package com.vole.film.controller.admin;

import com.vole.film.entity.Link;
import com.vole.film.run.StartupRunner;
import com.vole.film.service.LinkService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 编写者： vole
 * Time： 2018/4/27.17:27
 * 内容：友情链接 Controller 类
 */
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {

    @Resource
    private LinkService linkService;

    @Resource
    private StartupRunner startupRunner;

    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        List<Link> linkList = linkService.list(page - 1, rows);
        Long total = linkService.getCount();
        Map<String, Object> result = new HashMap<>();
        result.put("rows", linkList);
        result.put("total", total);
        return result;
    }

    @RequestMapping("/save")
    public Map<String, Object> save(Link link) throws Exception {
        Map<String, Object> result = new HashMap<>();
        linkService.save(link);
        result.put("success", true);
        startupRunner.loadData();
        return result;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        Map<String, Object> result = new HashMap<>();
        for (String anIdsStr : idsStr) {
            linkService.delete(Integer.parseInt(anIdsStr));
        }
        startupRunner.loadData();
        result.put("success", true);
        return result;
    }
}
