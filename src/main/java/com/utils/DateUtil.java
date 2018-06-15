package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: ehrMidware
 * @Package: com.utils
 * @Author: longhai
 * @CreateDate: 2018/5/23 17:50
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class DateUtil {

    public static SimpleDateFormat dataTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date){


        return dataTimeFormat.format(date);
    }
}
