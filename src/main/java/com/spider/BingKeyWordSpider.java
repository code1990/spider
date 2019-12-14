package com.spider;

import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

/**
 * @author wei
 * @description
 * @date 2019/12/4
 */
public class BingKeyWordSpider {
    private String userName = System.getProperty("user.name");
    private String str = "C:\\Users\\" + userName + "\\Desktop\\ebook\\123456789.txt";
    private String Main_url = "https://cn.bing.com/";
    List<String> list = TxtUtil.readTxt(str);
    int pageSize = 30;

    @Test
    public void testInfo() {
        Map<String, String> map = getMap();
        List<String> list = getList();
        for (int i = 0; i < list.size(); i++) {
            String url = list.get(i);
            System.out.println(url);
            String[] values = map.get(url).split("_");
            String number = values[0];
            String keyword = values[1];
            String size = values[2];
            String info = getUrlList(keyword,url);
            System.out.println("第"+number+"个关键字"+keyword+",第"+size+"页,爬取内容如下：");
            System.out.println(info);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public String dealChar(String keyword) {
        /*keyword = keyword.replaceAll(" ", "+");*/
        keyword = keyword.replaceAll("\"", "%22");
        keyword = keyword.replaceAll("#", "%23");
        keyword = keyword.replaceAll("%", "%25");
        keyword = keyword.replaceAll("&", "%26");
        keyword = keyword.replaceAll("\\(", "%28");
        keyword = keyword.replaceAll("\\)", "%29");
        keyword = keyword.replaceAll("\\+", "%2B");
        keyword = keyword.replaceAll(",", "%2C");
        keyword = keyword.replaceAll("/", "%2F");
        keyword = keyword.replaceAll(":", "%3A");
        keyword = keyword.replaceAll(";", "%3B");
        keyword = keyword.replaceAll("<", "%3C");
        keyword = keyword.replaceAll("=", "%3D");
        keyword = keyword.replaceAll(">", "%3E");
        keyword = keyword.replaceAll("\\?", "%3F");
        keyword = keyword.replaceAll("@", "%40");
        keyword = keyword.replaceAll("\\|", "%7C");
        return keyword;
    }

    public String getUrlList(String keyword, String url) {
        String split = "";
        if (keyword.contains("%3A.")) {
            split = keyword.split("%3A")[1];
        }
        /*System.out.println(split);*/
        WebDriver driver = SpiderUtil.getChromeDriver(url,false);
        /*WebElement element = null;
        element = driver.findElement(By.id("est_en"));
        element.click();
        element = driver.findElement(By.id("sb_form_q"));
        element.sendKeys(keyword);
        element = driver.findElement(By.id("sb_form_go"));
        element.click();*/
        StringBuilder sb = new StringBuilder();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list = driver.findElement(By.id("b_results")).findElements(By.className("b_algo"));
        for (int i = 0; i < list.size(); i++) {
            WebElement webElement = list.get(i);
            String title = webElement.findElement(By.tagName("h2")).getText();
            String href = webElement.findElement(By.tagName("a")).getAttribute("href");
            if (!"".equals(href)) {
                href = href.split(split)[0] + split;
            }
            sb.append(title + "\t" + href+"\n");
        }
        SpiderUtil.chromeQuit(driver);
        return sb.toString();
    }

    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String keyword = list.get(i).trim();
            keyword = dealChar(keyword);
            int searchNum = 1;
            for (int j = 1; j <= pageSize; j++) {
                if (j != 1) {
                    searchNum = (j - 1) * 14;
                }
                String from = "PERE";
                if (j != 1 && j != 2) {
                    from = from + (j - 2);
                }
                String url = Main_url + "?q=" + keyword + "&go=%e6%8f%90%e4%ba%a4&qs=ds&ensearch=1&first=" + searchNum + "&FORM=" + from;
                map.put(url, (i+1) + "_" + keyword + "_" + j);
            }
        }
        return map;
    }

    public List<String> getList() {
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String keyword = list.get(i).trim();
            keyword = dealChar(keyword);
            int searchNum = 1;
            for (int j = 1; j <= pageSize; j++) {
                if (j != 1) {
                    searchNum = (j - 1) * 14;
                }
                String from = "PERE";
                if (j != 1 && j != 2) {
                    from = from + (j - 2);
                }
                String url = Main_url + "?q=" + keyword + "&go=%e6%8f%90%e4%ba%a4&qs=ds&ensearch=1&first=" + searchNum + "&FORM=" + from;
                urlList.add(url);
            }
        }
        return urlList;
    }
}

