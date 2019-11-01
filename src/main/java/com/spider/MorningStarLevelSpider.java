package com.spider;

import com.SpiderApplication;
import com.entity.FundSnapshot;
//import org.apache.tomcat.util.http.fileupload.FileUtils;
import com.util.SpiderUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.internal.WrapsDriver;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/31
 */
public class MorningStarLevelSpider {
    public static final String BASE_URL = "http://cn.morningstar.com/quickrank/default.aspx";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = LoggerFactory.getLogger(MorningStarLevelSpider.class);
    static {
        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverClassPath);
    }
    public static void getPageList(int pageNum){
        WebDriver driver = new ChromeDriver();
        driver.get(BASE_URL);
        //点击翻页
        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1','"+pageNum+"')");
//        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$lbPerformance','')");

//        if(pageNum!=1){
//            int clickNum = pageNum-1;
//            int tmp =2;
//            while (clickNum!=0){
//                WebElement pagerId = driver.findElement(By.id("ctl00_cphMain_AspNetPager1"));
//                List<WebElement> pagerSize = pagerId.findElements(By.tagName("a"));
//                String href = "";
//                if(pagerSize.size()==14){
//                    pagerSize.get(12).click();
////                    System.out.println(pagerSize.get(12));
////                    href = pagerSize.get(12).getAttribute("href");
//                } else if(pagerSize.size()==15){
//                    pagerSize.get(13).click();
////                    href = pagerSize.get(13).getAttribute("href");
//                    //已经查询到了最后一页
//                } else if(pagerSize.size()==5){
//                    pagerSize.get(3).click();
//                    href = pagerSize.get(3).getAttribute("href");
//                }
////                String currentPage = href.split(",")[1].replaceAll("'","").replace(")","");
//                System.out.println("currentPage"+tmp++);
//                clickNum--;
//            }
//
//            //翻页加载需要时间 先等待一下
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }
        WebElement qrGrid = driver.findElement(By.id("qr_grid"));
        List<WebElement> tr = qrGrid.findElements(By.tagName("tr"));
        logger.error("第"+pageNum+"页爬取>>>>>>>>>>>begin");
        for (int i = 1; i < tr.size(); i++) {
            WebElement elementTr = tr.get(i);
            List<WebElement> td = elementTr.findElements(By.tagName("td"));
            StringBuilder sb = new StringBuilder();
            FundSnapshot snapshot = new FundSnapshot();
            for (int j = 0; j < td.size(); j++) {
                String info = td.get(j).getText();
                switch (j) {
                    case 0:
                        snapshot.setNum(info);
                        break;
                    case 2:
                        snapshot.setFundCode(info);
                        String url = td.get(j).findElements(By.tagName("a")).get(0).getAttribute("href");
                        snapshot.setFundUrl(url);
                        break;
                    case 3:
                        snapshot.setFundName(info);
                        break;
                    case 4:
                        snapshot.setFundType(info);
                        break;
                    case 7:
                        try {
                            snapshot.setValueDate(sdf.parse(info));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 8:
                        snapshot.setUnitValue(info);
                        break;
                    case 9:
                        snapshot.setValueDayChange(info);
                        break;
                    case 10:
                        snapshot.setYeaPayPercent(info);
                        break;
                    default:
                        break;
                }
                sb.append(td.get(j).getText().trim() + "\t");
            }
            snapshot.setCreateTime(new Date());
            snapshot.setPageSize(pageNum);
            logger.error(sb.toString());
        }
        SpiderUtil.cutScreen(driver,pageNum);
//        Thread.sleep(2000);
//        driver.quit();
//        driver.quit();
        driver.quit();
        logger.error("第"+pageNum+"页爬取>>>>>>>>>>>end\t,\t数量");
//        return list;
    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(Arrays.toString(.toArray()));
        for(int i =1;i<=33;i++){
            getPageList(i);
        }

    }
}

