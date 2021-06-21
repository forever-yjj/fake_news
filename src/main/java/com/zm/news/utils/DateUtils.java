package com.zm.news.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @projectName: news-ssm
 * @package: com.zm.news.utils
 * @className: DateUtils
 * @author: ZM
 * @description: 日期处理工具类
 * @date: 2021/1/20 11:45
 * @version: 1.0
 */
public class DateUtils {

    public static Date stringToDate(String str) {
        //创建SimpleDateFormat对象实例并定义好转换格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static void main(String[] args) {
        Date date = stringToDate("2021-01-12");
        System.out.println(date.toString());
    }
}
