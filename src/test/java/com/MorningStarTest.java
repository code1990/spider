package com;

import com.entity.FundSnapshot;
import com.util.SpiderUtil;
import com.util.TxtUtil;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: spider
 * @Date: 2019-11-24 15:08
 * @Author: code1990
 * @Description:
 */
public class MorningStarTest {
    private static final String url = "http://cn.morningstar.com/quickrank/default.aspx";
    Map<Integer, String> map = new HashMap<>();
    String path = "C:\\Users\\admin\\Desktop\\fund\\0\\";
//    String[] strArray = "快照,业绩和风险,投资组合,购买信息".split(",");
    String username = System.getProperty("os.name");
    @Test
    public void getOne() throws Exception{

//        for (int i = 0; i <1 ; i++) {
//            cutScreen(i);
            System.out.println(getInfo(0));
//            TxtUtil.writeTxt(path+i+"_"+map.get(i)+".txt",getInfo(i));
//        }
    }
    public String getInfo(int index) throws Exception {
        WebDriver driver = com.spider.SpiderUtil.getChromeDriver(url,true);
        driver.findElement(By.id("ctl00_cphMain_cblCategory_" + index)).click();
        driver.findElement(By.id("ctl00_cphMain_btnGo")).click();
        String pageNum = driver.findElement(By.id("ctl00_cphMain_TotalResultLabel")).getText();
        System.out.println(pageNum);
        int pageSize = new Integer(pageNum) / 25 + 1;
        System.out.println(pageSize);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= pageSize; i++) {
            ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1'," +
                    "'" + i + "')");
            WebElement qrGrid = driver.findElement(By.id("qr_grid"));
            List<WebElement> tr = qrGrid.findElements(By.tagName("tr"));
            for (int j = 1; j < tr.size(); j++) {
                List<WebElement> td = tr.get(j).findElements(By.tagName("td"));
                String number = td.get(0).getText();
                String fundCode = "#"+td.get(2).getText();
                String fundName = td.get(3).getText();
                String fundUrl = td.get(3).findElement(By.tagName("a")).getAttribute
                        ("href");
                String fundType = td.get(4).getText();
                String fundValue = td.get(8).getText().equals("-")?"0":td.get(8).getText();
                String fundPay = td.get(10).getText().equals("-")?"0":td.get(10).getText();
                String text = number+"\t"+fundCode+"\t"+fundName+"\t"+
                        fundType+"\t\t\t"+fundValue+"\t"+fundPay+"\t"+fundUrl;
                sb.append(text + "\n");
            }
            System.out.println(i+"\t"+sb.toString());
            TxtUtil.writeTxt(path+i+"_"+map.get(i)+".txt",sb.toString());
        }
        return sb.toString();
    }

    /*public  void cutScreen(int index) throws Exception{
        WebDriver driver = com.spider.SpiderUtil.getChromeDriver(url);
        driver.findElement(By.id("ctl00_cphMain_cblCategory_" + index)).click();
        driver.findElement(By.id("ctl00_cphMain_btnGo")).click();
        String pageNum = driver.findElement(By.id("ctl00_cphMain_TotalResultLabel")).getText();
        int pageSize = new Integer(pageNum) / 25 + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= pageSize; i++) {
            ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1'," +
                    "'" + i + "')");
            Thread.sleep(5000);
            //截图
            SpiderUtil.cutScreen(driver,i);
//            driver.quit();
        }
    }*/

//    @Test
//    public void testInfo123() throws Exception{
//        double avg = 71.8;
//        for (int i = 0; i < 1; i++) {
//            System.out.println(getAvgInfo(i));
//        }
//    }

    @Before
    public void initMap() {
        map.put(0, "股票型基金");
        map.put(1, "行业股票医药");
        map.put(2, "行业股票科技、传媒及通讯");
        map.put(3, "沪港深股票型基金");
        map.put(4, "香港股票型基金");
        map.put(5, "激进配置型基金");
        map.put(6, "灵活配置型基金");
        map.put(7, "标准混合型基金");
        map.put(8, "保守混合型基金");
        map.put(9, "沪港深混合型基金");
        map.put(10, "可转债型基金");
        map.put(11, "激进债券型基金");
        map.put(12, "普通债券型基金");
        map.put(13, "纯债基金");
        map.put(14, "短债基金");
        map.put(15, "货币基金");
        map.put(16, "市场中性策略");
        map.put(17, "商品");
        map.put(18, "保本基金");
        map.put(19, "其它");
    }

    /*public String getAvgInfo(int index) throws Exception {
        WebDriver driver = com.spider.SpiderUtil.getChromeDriver(url,true);
        driver.findElements(By.className("right")).get(0).findElement(By.tagName("a")).click();
        driver.findElement(By.id("ctl00_cphMain_ddlEffectiveDate")).findElements(By.tagName("option")).get(1).click();
        //2019-01-01 开始上市交易的 2019-01-01以来的平均收益 股票基金
        driver.findElement(By.id("ctl00_cphMain_txtEffectiveDate")).sendKeys("2018-12-26");
        driver.findElement(By.id("ctl00_cphMain_ddlFundStatus")).findElements(By.tagName("option")).get(1).click();
        driver.findElement(By.id("ctl00_cphMain_cblCategory_" + index)).click();
        driver.findElement(By.id("ctl00_cphMain_btnGo")).click();
        String pageNum = driver.findElement(By.id("ctl00_cphMain_TotalResultLabel")).getText();
        int pageSize = new Integer(pageNum) / 25 + 1;
        System.out.println(pageSize);
        double sum =0.0;
        for (int i = 1; i <= pageSize; i++) {
            ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1'," +
                    "'" + i + "')");
            WebElement qrGrid = driver.findElement(By.id("qr_grid"));
            List<WebElement> tr = qrGrid.findElements(By.tagName("tr"));
            for (int j = 1; j < tr.size(); j++) {
                String text = tr.get(j).findElements(By.className("msDataNumeric")).get(3).getText();
                if(text.contains("-")){
                    System.out.println(i);
                    if(!text.equals("-")){
                        sum = sum -new Double(text.replace("-",""));
                    }

                }else{
                    sum =sum+new Double(text);
                }
                System.out.println(text);
                System.out.println(sum);
            }
        }
        System.out.println(sum);
        System.out.println(pageSize);
        return sum/pageSize+"";
    }*/
}
