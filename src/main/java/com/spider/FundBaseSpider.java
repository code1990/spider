package com.spider;

import com.SpiderApplication;
import com.entity.FundSnapshot;
import com.util.SpiderUtil;
import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wei
 * @description
 * @date 2019/10/31
 */
public class FundBaseSpider {

    String PATH=SpiderApplication.class.getClassLoader().getResource("fund.txt").getPath();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = LoggerFactory.getLogger(FundBaseSpider.class);
    static {
        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverClassPath);
    }

    public static String getYestDay(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,-24);
        return dateFormat.format(calendar.getTime());
    }

    public static void getPageList(String url){
        WebDriver driver = new ChromeDriver();
        //设置浏览器尺寸
        driver.get(url);
        //点击翻页
//        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1','"+pageNum+"')");
        //翻页加载需要时间 先等待一下
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element =null;
        element = driver.findElement(By.id("qt_fund"));
        String fundCode = element.getText().split(" ")[0];
        String fundName = element.getText().split(" ")[1];
        //=====================================================
        element = driver.findElement(By.id("qt_base"));
        String[] array = element.getText().split("\n");
        String unitValue = array[1].replace("￥","").replace("$","");
        String valueDate=getYestDay();
        String valueDayChange=array[4].split(" | ")[0];
        String valueDayPercent=array[4].split(" | ")[1];
        String fundType=array[6];
        String startDate=array[8];
        String openDate=array[10];
        String saleDate=array[12];
        String applyStatus=array[14];
        String ransomStatus=array[16];
        String investStyle=array[18];
        System.out.println(investStyle);
        String allValue=array[20];
        String investMin=array[22];
        String salePlace=array[24];
        String preCost=array[26];
        String backCost=array[28];
        //=====================================================

//        System.out.println(qrGrid.getText());
//        String str = qrGrid.getText();
//        System.out.println(str.split("\n").length);
        System.out.println(driver.findElement(By.id("qt_quarter")).getText());
        System.out.println(driver.findElement(By.id("qt_dividend")).getText());
        driver.findElement(By.id("qt_splittab")).click();
        System.out.println(driver.findElement(By.id("qt_split")).getText());

//        List<WebElement> tr = qrGrid.findElements(By.tagName("tr"));
////        logger.error("第"+pageNum+"页爬取>>>>>>>>>>>begin");
//        for (int i = 1; i < tr.size(); i++) {
//            WebElement elementTr = tr.get(i);
//            List<WebElement> td = elementTr.findElements(By.tagName("td"));
//            StringBuilder sb = new StringBuilder();
//            FundSnapshot snapshot = new FundSnapshot();
//            for (int j = 0; j < td.size(); j++) {
//                String info = td.get(j).getText();
//                switch (j) {
//                    case 0:
//                        snapshot.setNum(info);
//                        break;
//                    case 2:
//                        snapshot.setFundCode(info);
////                        String url = td.get(j).findElements(By.tagName("a")).get(0).getAttribute("href");
//                        snapshot.setFundUrl(url);
//                        break;
//                    case 3:
//                        snapshot.setFundName(info);
//                        break;
//                    case 4:
//                        snapshot.setFundType(info);
//                        break;
//                    case 7:
//                        try {
//                            if(!info.equals("-")){
//                                snapshot.setValueDate(sdf.parse(info));
//                            }
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case 8:
//                        if(!info.equals("-")){
//                            snapshot.setUnitValue(info);
//                        }
//                        break;
//                    case 9:
//                        if(!info.equals("-")){
//                            snapshot.setValueDayChange(info);
//                        }
//                        break;
//                    case 10:
//                        if(!info.equals("-")){
//                            snapshot.setYeaPayPercent(info);
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                sb.append(td.get(j).getText().trim() + "\t");
//            }
//            snapshot.setCreateTime(new Date());
//            snapshot.setLever3("0");
//            snapshot.setLever5("0");
////            snapshot.setPageSize(pageNum);
//            logger.error(sb.toString());
////            list.add(snapshot);
//        }
        //截图
//        SpiderUtil.cutScreen(driver,pageNum);
//        driver.quit();
//        logger.error("第"+pageNum+"页爬取>>>>>>>>>>>end\t,\t数量"+list.size());
//        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        String str = "http://cn.morningstar.com/quicktake/0P0001606X";
        getPageList(str);
//        System.out.println(Arrays.toString(.toArray()));
    }

    @Test
    public void testInfo(){
//        System.out.println(PATH);
        List<String> list = TxtUtil.readTxt(PATH);
        System.out.println(list.size());

        System.out.println("1\n2\n".split("\r\n").length);
    }
}

