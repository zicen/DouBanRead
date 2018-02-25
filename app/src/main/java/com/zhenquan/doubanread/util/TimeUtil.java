package com.zhenquan.doubanread.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhenquan on 2018/2/25 0025.
 */

public class TimeUtil {
    /**
     * 根据时间毫秒值获得日期
     * @param millionSeconds
     * @return
     */
    public static  String getFormatTime(Long millionSeconds){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millionSeconds);
        Date date = c.getTime();
        System.out.println(sdf.format(date));
        return sdf.format(date);
    }

}
