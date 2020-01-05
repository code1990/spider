package com.spider;

import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/11/20
 */
public class DoubanSpiderNew {
    private String Main_URL = "https://search.douban.com/book/subject_search?search_text=";
    private String userName = System.getProperty("user.name");
    private String path = "C:\\Users\\" + userName + "\\Desktop\\ebook\\douban\\";
    private String kw = "时间";
    private String pathTxt = path + kw + ".txt";
    private String urlTxt = path + kw + "_url.txt";
    private String okTxt = path + kw + "_ok.txt";
    private String sortTxt = path + kw + "_sort.txt";

    @Test
    public void bookInfo(){
//        sortTxt();
    }
    //    @Test
//    public void getInfo() throws Exception {
//
//
//        String text = java.net.URLEncoder.encode(kw, "UTF-8");
//        int start = 0;
//        int end = 660;
//        int step = 15;
//        StringBuilder sb = new StringBuilder();
//        List<String> resultList = BingSearchUtil.readTxt(pathTxt);
//        sb.append(getListStr(resultList));
//        WebDriver driver = BingSearchUtil.getChrome(false);
//        for (int i = start; i <= end; ) {
//            String pageUrl = Main_URL + text + "&cat=1001&start=" + i;
//            System.out.println(pageUrl);
//            driver.get(pageUrl);
//            Thread.sleep(5000);
//            WebElement element = null;
//            element = driver.findElement(By.id("root"));
//            List<WebElement> list = element.findElements(By.className("sc-bZQynM"));
//            System.out.println(list.size());
//            for (int j = 0; j < list.size(); j++) {
//                String info = list.get(j).getText().replaceAll("\n", "\t") + "\n";
//                System.out.print(i + "\t" + info);
//                sb.append(i + "\t" + info);
//            }
//            System.out.println(sb.toString());
//            TxtUtil.writeTxt(pathTxt, sb.toString());
//            i += 15;
//            Thread.sleep(5000);
//            System.out.println("页>>" + i / 15 + 1);
//        }
//    }
//
//    public String getListStr(List<String> list) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < list.size(); i++) {
//            sb.append(list.get(i) + "\n");
//        }
//        return sb.toString();
//    }
    public List<String> getResultList(WebDriver driver, String url, int pageNumber) throws InterruptedException {
        System.out.println(pageNumber + ">>" + url);
        List<String> resultList = new ArrayList<>();
        driver.get(url);
        Thread.sleep(10000);
        WebElement element = driver.findElement(By.id("root"));
        List<WebElement> elementList = element.findElements(By.className("sc-bZQynM"));
        for (int j = 0; j < elementList.size(); j++) {
            String info = elementList.get(j).getText().replaceAll("\n", "\t");
            System.out.println(pageNumber + ">>" + info);
            resultList.add(pageNumber + "\t" + info);
        }
        return resultList;
    }


    @Test
    public void restart() {
        BingSearchUtil.writeTxt(okTxt, "0");
    }

    @Test
    public void saveBookUrl() throws Exception {
        int allPage = 134;
        int currentPage = 0;
        String text = java.net.URLEncoder.encode(kw, "UTF-8");
        List<String> readList = BingSearchUtil.readTxt(urlTxt);
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

    @Test
    public void getBook() throws Exception {
        List<String> readList = BingSearchUtil.readTxt(urlTxt);
        int start = new Integer(BingSearchUtil.readTxt(okTxt).get(0).trim());
        WebDriver driver = BingSearchUtil.getChrome(true);
        for (int i = start; i < readList.size(); i++) {
            String url = readList.get(i);
            int pageNumber = i + 1;
            List<String> resultList = getResultList(driver, url, pageNumber);
            BingSearchUtil.writeAppendTxt(pathTxt, resultList);
            BingSearchUtil.writeTxt(okTxt, i + "");
        }
    }

    @Test
    public void sortBook(){
        List<String> sortList = new ArrayList<>();
        for (String s : TxtUtil.readTxt(pathTxt)) {
            String[] array = s.split("\t");
            String comment = array[2];
            if (comment.startsWith("(")) {
                comment = 0 + comment;
            }
            comment = comment.replace("(", "\t");
            comment = comment.replace(")", "");
            comment = comment.replace("人评价", "");
            comment = comment.replace("目前无", "0");
            comment = comment.replace("评价人数不足", "0");
            String press = "";
            if (array.length == 4) {
                press = array[3];
            }
            /*System.out.println();*/
            sortList.add(array[0] + "\t" + array[1] + "\t" + comment + "\t" + press);
        }
        BingSearchUtil.writeTxt(sortTxt, sortList);
    }

}

