package com.spider;

import com.SpiderApplication;
import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wei
 * @description
 * @date 2019/10/31
 */
public class MeiniguiSpider {

    String BIGDATA_PATH = SpiderApplication.class.getClassLoader().getResource("bigdata.txt").getPath();

    private static Logger logger = LoggerFactory.getLogger(MeiniguiSpider.class);

    static {
        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverClassPath);
    }


    public static void getPageList(String num, String fundUrl) {
        WebDriver driver = new ChromeDriver();
        //设置浏览器尺寸
        driver.get("http://www.meinigui.com/");
        driver.manage().window().maximize();
        //翻页加载需要时间 先等待一下
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = null;
        String[] array = null;
        element = driver.findElement(By.id("menu"));
        element.findElement(By.className("new-login")).click();
        driver.findElement(By.id("username")).sendKeys("903957");
        driver.findElement(By.id("password")).sendKeys("ya83cr");
        driver.findElement(By.className("layui-layer-btn0")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("kw")).sendKeys(fundUrl);
        driver.findElement(By.className("banner_button")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String str = driver.findElement(By.className("layui-layer-content")).getText();
        System.out.println(str);
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        String fundUrl = "http://www.javaxxz.com/thread-394162-1-1.html";
        getPageList("1", fundUrl);
    }

    @Test
    public void getBigData() {
        String core = "推荐";
        for (String str : TxtUtil.readTxt(BIGDATA_PATH)) {
            if (str.toLowerCase().contains(core.toLowerCase())) {
                System.out.println(str.split("\t")[1]);
                String url = str.split("\t")[2];
                getPageList("1", url);
            }
        }
    }

    public static String getYunUrl(String url) {
        String result = "";
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            //设置浏览器尺寸
            driver.get("http://www.meinigui.com/");
            driver.manage().window().maximize();
            //翻页加载需要时间 先等待一下
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement element = null;
            element = driver.findElement(By.id("menu"));
            element.findElement(By.className("new-login")).click();
            driver.findElement(By.id("username")).sendKeys("903957");
            driver.findElement(By.id("password")).sendKeys("ya83cr");
            driver.findElement(By.className("layui-layer-btn0")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.id("kw")).sendKeys(url);
            driver.findElement(By.className("banner_button")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            result = driver.findElement(By.className("layui-layer-content")).getText();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return result;
    }

    public static String getUrl(String url) throws Exception{
        String result = "";
        WebDriver driver = null;
        String mainUrl = "http://www.meinigui.com/";
        try {
            driver = SpiderUtil.getChromeDriver(mainUrl);
            //设置浏览器尺寸
            driver.manage().window().maximize();
            //翻页加载需要时间 先等待一下
            Thread.sleep(1000);
            WebElement element = null;
            element = driver.findElement(By.id("menu"));
            element.findElement(By.className("new-login")).click();
            driver.findElement(By.id("username")).sendKeys("903957");
            driver.findElement(By.id("password")).sendKeys("ya83cr");
            driver.findElement(By.className("layui-layer-btn0")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.id("kw")).sendKeys(url);
            driver.findElement(By.className("banner_button")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            result = driver.findElement(By.className("layui-layer-content")).getText();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return result;
    }

}

