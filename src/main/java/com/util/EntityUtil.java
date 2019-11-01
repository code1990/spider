package com.util;

import org.junit.Test;

import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/11/1
 */
public class EntityUtil {
    private static final String path = "C:\\Users\\Administrator\\Desktop\\tmp.txt";

    @Test
    public void getList(){
        List<String> list = TxtUtil.readTxt(path);
        int half = list.size()/2;
        for (int i = 0; i < half; i++) {
            System.out.println("/**"+list.get(i)+"*/");
            System.out.println("@Column(name = \""+list.get(i+half)+"\")");
            System.out.println("private String "+list.get(i+half)+";");
        }
    }
}

