package com.spider;

import com.util.TxtUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wei
 * @description
 * @date 2019/12/17
 */
public class JdbcFieldTest {
    static Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    @Test
    public void testInfo12388() {
        String path = "E:\\2020-01-07\\bigdata\\spark_x.md";
        List<String> list = BingSearchUtil.readTxt(path);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (!str.contains("![img]") && !str.contains("尚硅谷")) {
                if (!str.contains("—————————————————————————————") && !str.contains("www.atguigu.com")) {
//                    System.out.println(list.get(i));
                    list2.add(list.get(i));
                }
            }
        }
        BingSearchUtil.writeTxt(path, list2);
    }

    public static boolean isContainChinese(String str) {

        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    @Test
    public void getInfos() {
        String path = "C:\\Users\\Administrator\\Desktop\\spring.txt";
        for (String str : BingSearchUtil.readTxt(path)) {
            String[] array = str.split("\t");
            if (array[1].toLowerCase().contains("spring")) {
                if (array[1].toLowerCase().contains("boot") || array[1].toLowerCase().contains("cloud")) {
                    if (isContainChinese(array[1])) {
                        System.out.println(str);
                    }
                } else {
                    if (isContainChinese(array[1])) {
                        System.out.println(str);
                    }
                }

            }

        }
    }
}

