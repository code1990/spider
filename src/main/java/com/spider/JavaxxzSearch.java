package com.spider;

import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/11/19
 */
public class JavaxxzSearch extends SpiderUtil {

    private static final String MAIN_URL = "http://www.javaxxz.com/search.php";
    @Test
    public void getSpark() throws Exception{
        String kw = "go";
        String path = "C:\\Users\\Administrator\\Desktop\\ebook\\";
        WebDriver driver = SpiderUtil.getChromeDriver(MAIN_URL);
        WebElement element = driver.findElement(By.id("scform_srchtxt"));
        element.sendKeys(kw);
        driver.findElement(By.id("scform_submit")).click();
        Thread.sleep(1000);
        element = driver.findElement(By.id("threadlist"));
        List<WebElement> list = element.findElements(By.className("xs3"));
        StringBuilder sb = new StringBuilder();
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            String str = list.get(i).getText();
            if(str.startsWith("【N")){
                String href = list.get(i).findElement(By.tagName("a")).getAttribute("href");
                String url = getReadUrl(href);
                urlList.add(url);
                System.out.println(str +"\t"+url+"\t");
                sb.append(str +"\t"+url+"\n");
            }

        }
        sb.append("==============\n");
//        for(String s:urlList){
//            String str = "";
//            try{
//                str = MeiniguiSpider.getUrl(s);
//                System.out.println(str);
//            }catch (Exception e){
//                e.printStackTrace();
//            }finally {
//                sb.append(s+"\n");
//            }
//        }
        sb.append("****************\n");
        System.out.println(sb.toString());
        TxtUtil.writeTxt(path+kw+".txt",sb.toString());
        driver.quit();
    }

    public String getReadUrl(String str ){
        String url = "";
        for(String s:str.split("&")){
            if(s.contains("tid")){
                String tid = s.split("=")[1];
                url = "http://www.javaxxz.com/thread-"+tid+"-1-1.html";
                break;
            }
        }
        return url;
    }
}

