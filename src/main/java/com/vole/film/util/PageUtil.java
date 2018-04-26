package com.vole.film.util;

/**
 * 编写者： vole
 * Time： 2018/4/26.09:25
 * 内容：分页工具类 bootstrap 版
 */
public class PageUtil {

    /**
     * 生成分页代码
     * @param targetUrl   目标地址
     * @param totalNum    总记录数
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @return string
     */
    public static String genPagination(String targetUrl, long totalNum, int currentPage, int pageSize) {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        if (totalPage == 0) {
            return "未查询到数据";
        } else {
            StringBuffer pageCode = new StringBuffer();
            pageCode.append("<li><a href='" + targetUrl + "/1'>首页</a></li>");
            if (currentPage > 1) {
                pageCode.append("<li><a href='" + targetUrl + "/" + (currentPage - 1) + "'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            for (int i = currentPage - 2; i <= currentPage + 2; i++) {
                if (i < 1 || i > totalPage) {
                    continue;
                }
                if (i == currentPage) {
                    pageCode.append("<li class='active'><a href='" + targetUrl + "/" + i + "'>" + i + "</a></li>");
                } else {
                    pageCode.append("<li><a href='" + targetUrl + "/" + i + "'>" + i + "</a></li>");
                }
            }
            if (currentPage < totalPage) {
                pageCode.append("<li><a href='" + targetUrl + "/" + (currentPage + 1) + "'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("<li><a href='" + targetUrl + "/" + totalPage + "'>尾页</a></li>");
            return pageCode.toString();
        }
    }
}
