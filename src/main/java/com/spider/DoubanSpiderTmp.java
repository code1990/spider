package com.spider;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wei
 * @description
 * @date 2019/11/20
 */
public class DoubanSpiderTmp {
    private String Main_URL = "https://search.douban.com/book/subject_search?search_text=";
    private String userName = System.getProperty("user.name");
    private String path = "C:\\Users\\" + userName + "\\Desktop\\ebook\\douban\\";
    private String kw = "spark";
    private String nearkw = "spring";
    private String pathTxt = path + kw + ".txt";
    private String urlTxt = path + kw + "_url.txt";
    private String hrefTxt = path + kw + "_href.txt";
    private String okTxt = path + kw + "_ok.txt";
    private String sortTxt = path + kw + "_sort.txt";

    @Test
    public void getResult() throws Exception {
        saveBookUrl();
        getBookList();
        getBookInfo();
    }


    @Test
    public void restart() {
        BingSearchUtil.writeTxt(okTxt, "0");
    }

    public void saveBookUrl() throws Exception {
        int allPage = 134;
        int currentPage = 0;
        String text = java.net.URLEncoder.encode(kw, "UTF-8");
        List<String> urlList = new ArrayList<>();
        for (int i = currentPage; i < allPage; i++) {
            String pageUrl = Main_URL + text + "&cat=1001&start=";
            int pageNumber = i * 15;
            pageUrl = pageUrl + pageNumber;
            urlList.add(pageUrl);
            System.out.println(pageUrl);
        }
        BingSearchUtil.writeTxt(urlTxt, urlList);
        BingSearchUtil.writeTxt(okTxt, "0");
    }

    public void getBookList() throws Exception {
        List<String> readList = BingSearchUtil.readTxt(urlTxt);
        int start = new Integer(BingSearchUtil.readTxt(okTxt).get(0).trim());
        WebDriver driver = BingSearchUtil.getChrome(true);
        for (int i = start; i < readList.size(); i++) {
            String url = readList.get(i);
            int pageNumber = i + 1;
            List<String> resultList = getResultList(driver, url, pageNumber);
            if(resultList.size()==0){
                BingSearchUtil.writeTxt(okTxt, "0");
                break;
            }
            BingSearchUtil.writeAppendTxt(pathTxt, resultList);
            BingSearchUtil.writeTxt(okTxt, i + "");
        }
        driver.quit();
    }

    private void getBookInfo() throws Exception {
        List<String> readList = BingSearchUtil.readTxt(pathTxt);
        readList = filterList(readList);
        int start = new Integer(BingSearchUtil.readTxt(okTxt).get(0).trim());
        WebDriver driver = BingSearchUtil.getChrome(true);
        for (int i = start; i < readList.size(); i++) {
            String[] infoArray = readList.get(i).split("\t");
            List<String> resultList = getResultList(driver, infoArray[1],infoArray[0], i);
            BingSearchUtil.writeAppendTxt(sortTxt, resultList);
            BingSearchUtil.writeTxt(okTxt, i + "");
        }
        driver.quit();
    }

    public List<String> getResultList(WebDriver driver, String url, int pageNumber) throws InterruptedException {
        System.out.println(pageNumber + ">>" + url);
        List<String> resultList = new ArrayList<>();
        driver.get(url);
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.id("root"));
        boolean flag = element.findElement(By.className("paginator")).getAttribute("outerHTML").contains("thispage");
        System.out.println("&&&" + flag);
        if (flag) {
            List<WebElement> elementList = element.findElements(By.className("sc-bZQynM"));
            for (int j = 0; j < elementList.size(); j++) {
                WebElement div = elementList.get(j);
                WebElement a = div.findElement(By.className("title")).findElement(By.tagName("a"));
                String href = a.getAttribute("href");
                String text = a.getText();
                if (isContainChinese(text) && text.toLowerCase().contains(kw.replaceAll("\\+"," "))) {
                    System.out.println(pageNumber + ">>" + text + ">>" + href);
                    resultList.add(text+"\t"+href);
                }
            }
        }
        return resultList;
    }

    private List<String> getResultList(WebDriver driver, String url,String title,int index) throws Exception {
        List<String> resultList = new ArrayList<>();
        driver.get(url);
        Thread.sleep(3000);
        String num = getSubject(url);
        String id = "dir_" + num + "_short";
        boolean flag = driver.findElement(By.className("article")).getAttribute("outerHTML").contains(id);
        if(flag){
            driver.findElement(By.id(id)).findElement(By.tagName("a")).click();
            Thread.sleep(3000);
            id = "dir_" + num + "_full";
            WebElement element = driver.findElement(By.id(id));
            String[] array = element.getText().split("\n");
            resultList.add(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+title);
            for (int i = 0; i < array.length; i++) {
                String str = array[i].trim();
                System.out.println(array[i].trim());
                if (!str.contains("(收起)")) {
                    resultList.add(array[i].trim());
                }
            }
            resultList.add(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+title);
        }else{
            //如果没有目录信息 则写入下一个 方便重启
            BingSearchUtil.writeTxt(okTxt, (index+1) + "");
            System.out.println(">>>>>>>>>>>>无目录，读取下一个");
        }
        return resultList;
    }

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public String getSubject(String url) {
        url = url.substring(0, url.length() - 1);
        int length = url.lastIndexOf("/");
        return url.substring(length + 1);
    }

    //通过HashSet踢除重复元素
    public static List filterList(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

}

