package com.spider;

import com.SpiderApplication;
import com.entity.FundOperation;
import com.entity.FundPerformance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author wei
 * @description
 * @date 2019/10/31
 */
public class FundOperationSpider {
    public static final String BASE_URL = "http://cn.morningstar.com/quickrank/default.aspx";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = LoggerFactory.getLogger(FundOperationSpider.class);

    static {
        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverClassPath);
    }

    public static List<FundOperation> getPageList(int pageNum) {
        List<FundOperation> list = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        driver.get(BASE_URL);
        //点击翻页
        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1','" + pageNum + "')");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击购买信息
        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$lbOperations','')");

        //翻页加载需要时间 先等待一下
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement qrGrid = driver.findElement(By.id("qr_grid"));
        List<WebElement> tr = qrGrid.findElements(By.tagName("tr"));
        logger.error("第" + pageNum + "页爬取>>>>>>>>>>>begin");
        for (int i = 1; i < tr.size(); i++) {
            WebElement elementTr = tr.get(i);
            List<WebElement> td = elementTr.findElements(By.tagName("td"));
            StringBuilder sb = new StringBuilder();
            FundOperation snapshot = new FundOperation();
            for (int j = 0; j < td.size(); j++) {
                String info = td.get(j).getText();
                if ("-".equals(info.trim())) {
                    info = null;
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
                        try {
                            if(info==null){
                                snapshot.setStartDate(null);
                            }else{
                                snapshot.setStartDate(sdf.parse(info));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        snapshot.setApplyStatus(info);
                        break;
                    case 6:
                        snapshot.setRansomStatus(info);
                        break;
                    case 7:
                        snapshot.setInvestMin(info);
                        break;
                    case 8:
                        snapshot.setPreCost(info);
                        break;
                    case 9:
                        snapshot.setBackCost(info);
                        break;
                    case 10:
                        snapshot.setRansomCost(info);
                        break;
                    case 11:
                        snapshot.setManageCost(info);
                        break;
                    case 12:
                        snapshot.setHostCost(info);
                        break;
                    case 13:
                        snapshot.setSaleCost(info);
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
        driver.quit();
        logger.error("第" + pageNum + "页爬取>>>>>>>>>>>end\t,\t数量");
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 1; i++) {
            getPageList(i);
        }

    }
}

