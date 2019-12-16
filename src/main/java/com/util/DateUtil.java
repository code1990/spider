package com.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: spider
 * @Date: 2019-12-16 22:41
 * @Author: code1990
 * @Description:
 */
public class DateUtil {
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    @Test
    public void testInfo() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM_dd");
        Date begin = sdf.parse("2016-01-01");
        Date end = sdf.parse("2016-12-31");
        List<Date> list = findDates(begin, end);
        for (int i = 0; i <list.size() ; i++) {
            String column="t_"+sdf2.format(list.get(i));
            String info ="@Column(name = \""+column+"\")\n" +
                    "    private String "+column+";";
            System.out.println(info);
        }
    }
}
