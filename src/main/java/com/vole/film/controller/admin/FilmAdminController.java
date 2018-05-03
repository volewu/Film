package com.vole.film.controller.admin;

import com.vole.film.entity.Film;
import com.vole.film.run.StartupRunner;
import com.vole.film.service.FilmService;
import com.vole.film.service.WebSiteInfoService;
import com.vole.film.util.DateUtil;
import com.vole.film.util.StringUtil;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 编写者： vole
 * Time： 2018/4/26.17:01
 * 内容：电影内容后台 controller 层
 */
@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Resource
    private StartupRunner startupRunner;

    @Value("${imageFilePath}")
    private String imageFilePath;

    @RequestMapping("/save")
    public Map<String, Object> save(Film film, @RequestParam("imageFile") MultipartFile file) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename(); // 获取文件名
            String suffixName = fileName.substring(fileName.lastIndexOf(".")); // 获取文件的后缀
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName));
            film.setImageName(newFileName);
        }
        film.setPublishDate(new Date());
        filmService.save(film);
        startupRunner.loadData();
        result.put("success", true);
        return result;
    }

    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload") MultipartFile file
            , String CKEditorFuncNum) throws Exception {
        System.err.println(imageFilePath);
        String fileName = file.getOriginalFilename();// 获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));// 获取文件的后缀
        String newFileName = DateUtil.getCurrentDateStr() + suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName));

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/static/filmImage/" + newFileName + "','')");
        sb.append("</script>");
        return sb.toString();
    }

    @RequestMapping("/list")
    public Map<String, Object> list(Film film, @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "rows", required = false) Integer rows) throws Exception {
        Map<String, Object> result = new HashMap<>();
        List<Film> filmList = filmService.list(film, page - 1, rows);
        Long total = filmService.getCount(film);
        result.put("rows", filmList);
        result.put("total", total);
        return result;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        Map<String, Object> result = new HashMap<>();
        boolean flag = true;
        for (String anIdsStr : idsStr) {
            Integer filmId = Integer.parseInt(anIdsStr);
            if (webSiteInfoService.getByFilmId(filmId).size() > 0)
                flag = false;
            else
                filmService.delete(filmId);
        }
        if (flag) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("errorInfo", "电影动态信息中存在电影信息，不能删除！");
        }
        startupRunner.loadData();
        return result;
    }

    @RequestMapping("findById")
    public Film findById(Integer id) throws Exception {
        return filmService.findById(id);
    }

    /**
     * 下拉框模糊查询用到
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/comboList")
    public List<Film> comboList(String q)throws Exception{
        if(StringUtil.isEmpty(q)){
            return null;
        }
        Film film=new Film();
        film.setName(q);
        return filmService.list(film, 0, 30); // 最多查询30条记录
    }

}
