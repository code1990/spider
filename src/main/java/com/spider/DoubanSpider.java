package com.spider;

import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/11/20
 */
public class DoubanSpider {
    static final String Main_URL = "https://search.douban.com/book/subject_search?search_text=";
    @Test
    public void getInfo() throws Exception{
        String path = "C:\\Users\\Administrator\\Desktop\\ebook\\douban\\";
        String kw = "量化";
        String text = java.net.URLEncoder.encode(kw,"UTF-8");
        int start =345;
        int end = 660;
        int step=15;
        boolean show = true;
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <=end ; ) {
            String pageUrl = Main_URL+text+"&cat=1001&start="+i;
            System.out.println(pageUrl);
            WebDriver driver = SpiderUtil.getChromeDriver(pageUrl,show);
            WebElement element = null;
            element = driver.findElement(By.id("root"));
            List<WebElement> list = element.findElements(By.className("sc-bZQynM"));
            System.out.println(list.size());
            for (int j = 0; j <list.size() ; j++) {
                String info = list.get(j).getText().replaceAll("\n","\t")+"\n";
                System.out.print(i+"\t"+info);
                sb.append(i+"\t"+info);
            }
            driver.quit();
            System.out.println(sb.toString());
            TxtUtil.writeTxt(path+kw+".txt",sb.toString());
            i+=15;
        }


    }
    @Test
    public void testInfo123(){
        String str = "C:\\Users\\Administrator\\Desktop\\ebook\\douban\\temp.txt";
        for(String s:TxtUtil.readTxt(str)){
            String[] array = s.split("\t");
            String comment =array[2];
            if(comment.startsWith("(")){
                comment = 0+comment;
            }
            comment = comment.replace("(","\t").replace(")","");
            System.out.println(comment);
            System.out.println(array.length);
        }
        String sss = "6.3(13人评价)";
        String sss2 = "(评价人数不足)";

        if(sss2.startsWith("(")){
            System.out.println(0+sss2.replace("(","\t").replace(")",""));
        }else{
            System.out.println(sss.replace("(","\t").replace(")",""));
        }


    }
}

