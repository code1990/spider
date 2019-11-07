package com.spider;

import com.SpiderApplication;
import com.entity.FundPerformance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.tomcat.util.http.fileupload.FileUtils;
//import java.awt.Rectangle;

/**
 * @author wei
 * @description
 * @date 2019/10/31
 */
public class FundPerformanceSpider {
    public static final String BASE_URL = "http://cn.morningstar.com/quickrank/default.aspx";
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = LoggerFactory.getLogger(FundPerformanceSpider.class);
    static {
        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverClassPath);
    }
    public static List<FundPerformance> getPageList(int pageNum){
        List<FundPerformance> list = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        driver.get(BASE_URL);
        //点击翻页
        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1','"+pageNum+"')");
        //点击业绩和风险
        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$lbPerformance','')");

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
            FundPerformance snapshot = new FundPerformance();
            for (int j = 0; j < td.size(); j++) {
                String info = td.get(j).getText();
                if("-".equals(info.trim())){
                    info=null;
                }
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
                        snapshot.setPay1day(info);
                        break;
                    case 5:
                        snapshot.setPay1week(info);
                        break;
                    case 6:
                        snapshot.setPay1month(info);
                        break;
                    case 7:
                        snapshot.setPay3month(info);
                        break;
                    case 8:
                        snapshot.setPay6month(info);
                        break;
                    case 9:
                        snapshot.setPay1year(info);
                        break;
                    case 10:
                        snapshot.setPay2year(info);
                        break;
                    case 11:
                        snapshot.setPay3year(info);
                        break;
                    case 12:
                        snapshot.setPay5year(info);
                        break;
                    case 13:
                        snapshot.setPay10year(info);
                        break;
                    case 14:
                        snapshot.setPayStart(info);
                        break;
                    case 15:
                        snapshot.setStd3(info);
                        break;
                    case 16:
                        snapshot.setRisk3(info);
                        break;
                    default:
                        break;
                }
                sb.append(td.get(j).getText().trim() + "\t");
            }
            snapshot.setCreateTime(new Date());
            snapshot.setPageSize(pageNum);
            logger.error(sb.toString());
            logger.error(snapshot.toString());
            list.add(snapshot);
        }

//        SpiderUtil.cutScreen(driver,pageNum);
//        Thread.sleep(2000);
//        driver.quit();
//        driver.quit();
        driver.quit();
        logger.error("第"+pageNum+"页爬取>>>>>>>>>>>end\t,\t数量");
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println(Arrays.toString(.toArray()));
        for(int i =1;i<=1;i++){
            getPageList(i);
        }

    }
}

