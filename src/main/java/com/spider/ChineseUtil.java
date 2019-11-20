package com.spider;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author wei
 * @description
 * @date 2019/11/20
 */
public class ChineseUtil {

    @Test
    public void decodeChinese() throws UnsupportedEncodingException {
        String str =java.net.URLDecoder.decode("%E9%87%8F%E5%8C%96","utf-8");
        System.out.println(str);
        String str2 = java.net.URLEncoder.encode("量化","utf-8");
        System.out.println(str2);
    }
}

