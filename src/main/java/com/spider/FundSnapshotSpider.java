package com.spider;

import com.SpiderApplication;
import com.entity.FundSnapshot;
import com.util.SpiderUtil;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/31
 */
public class FundSnapshotSpider {
    public static final String BASE_URL = "http://cn.morningstar.com/quickrank/default.aspx";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = LoggerFactory.getLogger(FundSnapshotSpider.class);
    static {
        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverClassPath);
    }
    public static List<FundSnapshot> getPageList(int pageNum){
        List<FundSnapshot> list = new ArrayList<>();
        WebDriver driver = new ChromeDriver();
        //设置浏览器尺寸
//        Dimension dimension = new Dimension(20, 20);
//        driver.manage().window().setSize(dimension);
        driver.get(BASE_URL);
        //点击翻页
        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1','"+pageNum+"')");
//        if(pageNum!=1){
//            int clickNum = pageNum-1;
//            int tmp =2;
//            while (clickNum!=0){
//                WebElement pagerId = driver.findElement(By.id("ctl00_cphMain_AspNetPager1"));
//                List<WebElement> pagerSize = pagerId.findElements(By.tagName("a"));
//                if(pagerSize.size()==14){
//                    pagerSize.get(12).click();
//                } else if(pagerSize.size()==15){
//                    pagerSize.get(13).click();
//                    //已经查询到了最后一页
//                } else if(pagerSize.size()==5){
//                    pagerSize.get(3).click();
//                }
////                String href = pagerSize.get(12).getAttribute("href");
////                String currentPage = href.split(",")[1].replaceAll("'","").replace(")","");
////                System.out.println(currentPage);
//                System.out.println("currentPage"+tmp++);
//                clickNum--;
//            }
//
//
//        }
        //翻页加载需要时间 先等待一下
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
                            if(!info.equals("-")){
                                snapshot.setValueDate(sdf.parse(info));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 8:
                        if(!info.equals("-")){
                            snapshot.setUnitValue(info);
                        }
                        break;
                    case 9:
                        if(!info.equals("-")){
                            snapshot.setValueDayChange(info);
                        }
                        break;
                    case 10:
                        if(!info.equals("-")){
                            snapshot.setYearPayPercent(info);
                        }
                        break;
                    default:
                        break;
                }
                sb.append(td.get(j).getText().trim() + "\t");
            }
            snapshot.setCreateTime(new Date());
            snapshot.setLever3("0");
            snapshot.setLever5("0");
            snapshot.setPageSize(pageNum);
            logger.error(sb.toString());
            list.add(snapshot);
        }
        //截图
        SpiderUtil.cutScreen(driver,pageNum);
        driver.quit();
        logger.error("第"+pageNum+"页爬取>>>>>>>>>>>end\t,\t数量"+list.size());
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Arrays.toString(getPageList(1).toArray()));
    }
}

