package com.hwhl.shengxian.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 获取当前时间类
 *
 * @Author: yang
 * @Date: 2018/5/1
 * @Version: 1.0
 */
public class FormatDate {

    /**
     * 获取时间
     * @param date
     * @return
     */
    public static String getDateFromat(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
