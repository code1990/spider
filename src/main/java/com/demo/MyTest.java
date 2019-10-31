package com.demo;

import com.util.TxtUtil;
import org.junit.Test;

/**
 * @program: spider
 * @Date: 2019-10-23 22:25
 * @Author: code1990
 * @Description:
 */
public class MyTest {
    private static final String path = "C:\\Users\\admin\\Desktop\\tmp123.txt";

    @Test
    public void testInfo(){
        for(String str: TxtUtil.readTxt(path)){
            String str1 = str.split("\t")[0];
            String str2 = str.split("\t")[1];
//            String str3 = str.split("\t")[2];
//            System.out.println(str1);
            System.out.println("#### "+str2);
            System.out.println();
//            System.out.println(str3);
        }
    }
}
