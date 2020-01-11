package com.spider;

import com.util.HttpClientUtil;
import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class BdSearchInfo {
    private String kw = "Spring+Boot";
    private String userName = System.getProperty("user.name");
    private String catalogTxt = "C:\\Users\\" + userName + "\\Desktop\\ebook\\douban\\"+ kw + "_catalog.txt";
    private String path = "C:\\Users\\" + userName + "\\Desktop\\ebook\\douban\\search\\";
    private String subKw = "pdf 百度云";
    private String okTxt = path + kw + "_ok.txt";
    private String titleTxt = path + kw + "_title.txt";
    private String titleSearchTxt = path + kw + "_titleSearch.txt";
    private String baiduSearchTxt = path + kw + "_baiduSearch.txt";
    private String yunTxt = path + kw + "_yun.txt";
    private String urlTxt = path + kw + "_url.txt";

    @Test
    public void setInfo111(){
//        BingSearchUtil.writeTxt(urlTxt, "0");
        BingSearchUtil.writeTxt(okTxt, "0");
    }
    @Test
    public void getInfo() throws Exception{
        //获取所有的书名
        getBookTitle();
        //保存所有的搜索URL
        saveSearchUrl();
        //遍历所有的URL 获取第一页URL 保存
        getUrlInfo();

    }
    @Test
    public void getBaiduYun()throws Exception{
        //遍历所有的url
        BingSearchUtil.writeTxt(okTxt, "0");
        getBaiduYunUrl();
    }

    public void getUrlInfo() throws Exception{
        List<String> titleSearchList = BingSearchUtil.readTxt(titleSearchTxt);
        int start = new Integer(BingSearchUtil.readTxt(okTxt).get(0).trim());
        WebDriver driver = BingSearchUtil.getChrome(true);
        for (int i = start; i < titleSearchList.size(); i++) {
            String url = titleSearchList.get(i);
            System.out.println(i);
            getResultList(url,driver);
            BingSearchUtil.writeTxt(okTxt, i+"");
        }
        BingSearchUtil.writeTxt(okTxt, "0");
        driver.quit();
    }

    private void saveSearchUrl() {
        List<String> titleList = BingSearchUtil.readTxt(titleTxt);
        List<String> titleSearchList = new ArrayList<>();
        for (int i = 0; i <titleList.size() ; i++) {
            String title = titleList.get(i);
            String text = null;
            try {
                text = java.net.URLEncoder.encode("'"+title+"' "+subKw, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //只搜索第一页
            String url = "https://www.baidu.com/s?ie=utf-8&wd="+text;
            titleSearchList.add(url);
        }
        BingSearchUtil.writeTxt(titleSearchTxt,titleSearchList);
        BingSearchUtil.writeTxt(okTxt,"0");
    }


    private void getResultList(String url, WebDriver driver) throws Exception {
        driver.get(url);
        Thread.sleep(1000);
        WebElement parent = driver.findElement(By.id("content_left"));
        List<WebElement> list = parent.findElements(By.className("c-container"));
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            String urls = list.get(i).findElement(By.tagName("h3")).findElement(By.tagName("a")).getAttribute("href");
            System.out.println(urls);
            urlList.add(urls);
        }
        BingSearchUtil.writeAppendTxt(baiduSearchTxt,urlList);
    }

    public void getBookTitle(){
        List<String> list = TxtUtil.readTxt(catalogTxt);
        List<String> titleList = new ArrayList<>();
        Set<String> set = new HashSet<String>();
        for (int i = 0; i <list.size() ; i++) {
            String str = list.get(i);
            if (str.startsWith(">")){
                if(set.add(str)){
                    titleList.add(str.replaceAll(">",""));
                }
            }
        }
        BingSearchUtil.writeTxt(titleTxt,titleList);

    }

    public void getBaiduYunUrl() throws Exception{
        int start = new Integer(BingSearchUtil.readTxt(okTxt).get(0).trim());
        List<String> urlList = BingSearchUtil.readTxt(baiduSearchTxt);
        WebDriver driver = BingSearchUtil.getChrome(true);
        Set<String> set = new HashSet<>();
        for (int i = start; i <urlList.size() ; i++) {
            String url = urlList.get(i);
            driver.get(url);
            Thread.sleep(1000);
            String str = driver.findElement(By.tagName("body")).getAttribute("outerHTML");
            boolean flag = str.contains("pan.baidu.com/s");
            if (flag){
                String[] array = str.replaceAll(">",">\n").split("\n");
                for (int j = 0; j <array.length ; j++) {
                    String info = array[j];
                    if(info.contains("pan.baidu.com/s")){
                        info = dealWithStr(info);
                        if(set.add(info)){
                            System.out.println(info);
                            BingSearchUtil.writeAppendTxt(driver.getTitle()+"\t"+yunTxt, info);
                        }
                    }
                }
            }
            BingSearchUtil.writeTxt(okTxt,i+"");
        }
        driver.quit();
    }
    public String dealWithStr(String str){
        if(str.contains("</span>")){
            str = str.replaceAll("<span\\s*[^>]*>","").replace("</span>","");
        }
        if(str.contains("</div>")){
            str = str.replaceAll("<div\\s*[^>]*>","").replace("</div>","");
        }
        if(str.contains("</a>")){
            str = str.replaceAll("<a\\s*[^>]*>","").replace("</a>","");
        }
        if(str.contains("</strong>")){
            str = str.replaceAll("<strong\\s*[^>]*>","").replace("</strong>","");
        }
        return str.trim();
    }
}
