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
        String str = "C:\\Users\\admin\\Desktop\\temp.txt";
        for(String s:TxtUtil.readTxt(str)){
            String[] array = s.split("\t");
            String comment =array[2];
            if(comment.startsWith("(")){
                comment = 0+comment;
            }
            comment = comment.replace("(","\t");
            comment = comment.replace(")","");
            comment = comment.replace("人评价","");
            comment = comment.replace("目前无","0");
            comment = comment.replace("评价人数不足","0");
            String press="";
            if(array.length==4){
                press=array[3];
            }
            System.out.println(array[0]+"\t"+array[1]+"\t"+comment+"\t"+press);
        }
    }
}

