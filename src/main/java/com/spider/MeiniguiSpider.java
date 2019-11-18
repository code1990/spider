package com.spider;

import com.SpiderApplication;
import com.entity.FundBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/31
 */
public class MeiniguiSpider {

    String PATH=SpiderApplication.class.getClassLoader().getResource("fund.txt").getPath();

    private static Logger logger = LoggerFactory.getLogger(MeiniguiSpider.class);
    static {
        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverClassPath);
    }


    public static void getPageList(String num,String fundUrl){
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
        WebElement element =null;
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String str = driver.findElement(By.className("layui-layer-content")).getText();
        System.out.println(str);

    }

    public static void main(String[] args) throws InterruptedException {
        String fundUrl = "http://www.javaxxz.com/thread-394162-1-1.html";
        getPageList("1",fundUrl);
    }

}

