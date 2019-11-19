package com.spider;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * @author wei
 * @description
 * @date 2019/11/19
 */
public class JavaxxzSearch extends SpiderUtil {

    private static final String MAIN_URL = "http://www.javaxxz.com/search.php?mod=forum&searchid=44&orderby=lastpost&ascdesc=desc&searchsubmit=yes&kw=";
    private static  String KEY_WORD = "";
    @Test
    public void getSpark(){
        KEY_WORD = "spark";
        WebDriver driver = SpiderUtil.getChromeDriver(MAIN_URL+KEY_WORD);
        System.out.println(driver.getTitle());
    }
}

