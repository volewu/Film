package com.vole.film.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 编写者： vole
 * Time： 2018/4/26.08:56
 * 内容：字符串工具类
 * 姿势：trim()----trim 掉了字符串两端 Unicode 编码小于等于 32(\u0020) 的所有字符;截取中间的非空白字符
 */
public class StringUtil {

    /**
     * 判断是否是空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断是否不是空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }

    /**
     * 过滤掉集合中的空格元素
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String l : list) {
            if (isNotEmpty(l))
                resultList.add(l);
        }
        return resultList;
    }

    /**
     * 格式化模糊查询
     * @param str
     * @return
     */
    public static String formatLike(String str) {
        if (isNotEmpty(str))
            return "%" + str + "%";
        else
            return null;
    }

    public static void main(String[] args) {
        String str1 = "  abc  ";
        str1 = str1.trim();
        String str2 = "abc";
        System.out.println(str1 == str2);//比较的是内存地址中堆栈的引用
        System.out.println(Objects.equals(str1, str2));//equals 方法的引用,值的引用
    }


}
