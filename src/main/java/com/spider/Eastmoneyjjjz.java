package com.spider;

import com.google.gson.JsonObject;
import com.util.HttpClientUtil;
import com.util.TxtUtil;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wei
 * @description
 * @date 2019/11/21
 * http://fundf10.eastmoney.com/jjjz_004851.html
 */
public class Eastmoneyjjjz {
    private static final String MAIN_URL = "http://fundf10.eastmoney.com/jjjz_";
//    String path = "C:\\Users\\Administrator\\Desktop\\fundcodename.txt";
    String path = "C:\\Users\\admin\\Desktop\\fundcodename.txt";
//    List<String> list = TxtUtil.readTxt(path);
    List<String> list = new ArrayList<>();//TxtUtil.readTxt(path);
    @Test
    public void getInfo(){
        for (int i = 0; i < 1; i++) {
            String[] strArray = list.get(i).split("\t");
            System.out.println("===========>"+i);
            String fundCode = "004851";//strArray[0];
            String fundName = strArray[1];
            String baseInfo = fundCode+"\t"+fundName+"\t";
            String url = MAIN_URL+fundCode+".html";
            WebDriver driver = SpiderUtil.getChromeDriver(url, false);
            String maxPage = driver.findElement(By.className("pagebtns")).getText().split("\n")[7];
            System.out.println(maxPage);
            int pageSize = 1;
            StringBuilder sb = new StringBuilder();
            while (pageSize<new Integer(maxPage)){
                WebElement element =null;
                element = driver.findElement(By.id("jztable"));
                List<WebElement> list = element.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
                //净值日期	单位净值	累计净值	日增长率	申购状态	赎回状态	分红送配
                for (int j = 0; j < list.size(); j++) {
                    System.out.println(list.get(j).getText());
                    sb.append(baseInfo+list.get(j).getText().replaceAll(" ","\t")+"\n");
                }
                driver.findElement(By.className("pagebtns")).findElements(By.tagName("label")).get(7).click();
//                driver.findElement(By.linkText("下一页")).click();
                pageSize++;
            }
            System.out.println(sb.toString());


        }




    }
    @Test
    public void testClick(){
        String url = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=110022&sdate=2018-01-01&edate=2018-01-31&per=31";
        WebDriver driver = SpiderUtil.getChromeDriver(url,false);
        String str = driver.findElement(By.tagName("tbody")).getText();

        System.out.println(str);
    }

    @Test
    public void testAPi(){
        long times = System.currentTimeMillis();
        String fundCode = "004851";
        int pageIndex = 1;
        int maxSize = 31;
        String url = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=110022&sdate=2018-01-01&edate=2018-01-31&per=31";
        String resultJson = HttpClientUtil.get(url);
        System.out.println(resultJson);

    }
}

