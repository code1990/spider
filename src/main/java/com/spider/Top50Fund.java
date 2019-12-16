package com.spider;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: spider
 * @Date: 2019-12-09 21:30
 * @Author: code1990
 * @Description: 爬取基金数据 按照每日增长率排名50
 */
public class Top50Fund {
    String Main_URL = "http://fund.eastmoney.com/data/fundranking.html#tall;c0;r;srzdf;pn50;ddesc;qsd20181207;" +
            "qed20191207;qdii;zq;gg;gzbd;gzfs;bbzt;sfbb";
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
    String date = sdf.format(new Date());
    int number =1;
   /* @Test
    public void testInfo(){
        WebDriver driver = SpiderUtil.getChromeDriver(Main_URL,true);
        WebElement table = driver.findElement(By.id("dbtable"));
        List<WebElement> list = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for(WebElement element:list){
            String text = element.getText();
            text = text.replaceAll("\n","\t");
            text = text.replaceAll(" ","\t");
            String[] textArray = text.split("\t");
            *//*String number = textArray[0];*//*
            String fundCode = textArray[1];
            String fundName = textArray[2];
            String fundDate = textArray[3];


        }
    }*/

    @Test
    public void getInfo() throws InterruptedException {
        WebDriver driver = SpiderUtil.getChromeDriver(Main_URL,true);
        WebElement table = driver.findElement(By.id("dbtable"));
        WebElement pagebar = driver.findElement(By.id("pagebar"));
        Set<String> set = new HashSet<>();
        for (int i = 1; i <=20 ; i++) {
            Thread.sleep(5000);
            List<WebElement> list = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
            Thread.sleep(5000);
            for(WebElement element:list){
                String text = element.getText();
                text = text.replaceAll("\n","\t");
                text = text.replaceAll(" ","\t");
                String[] textArray = text.split("\t");
                /*String number = textArray[0];*/
                String fundCode = textArray[1];
                String fundName = textArray[2];
                String fundDate = textArray[3];
                String fundRate = textArray[6];
                if(date.equals(fundDate.trim())){
                    set.add(fundCode);
                    System.out.println(number+"\t"+fundCode+"\t"+fundName+"\t"+fundRate);
                    number++;
                    if(set.size()==100){
                        break;
                    }
                    /*if(number==51){
                        break;
                    }*/
                }

            }
            int length = pagebar.findElements(By.tagName("label")).size();
            if (length == 9) {
                pagebar.findElements(By.tagName("label")).get(5).click();
            } else {
                pagebar.findElements(By.tagName("label")).get(i+1).click();
            }

            Thread.sleep(5000);
        }
    }

    @Test
    public void testInfo123(){

    }

}
