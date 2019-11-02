package com.spider;

import com.SpiderApplication;
import com.entity.FundSnapshot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/11/2
 */
public class FundSnapshotSpiderLess {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static final String BASE_URL = "http://cn.morningstar.com/quickrank/default.aspx";


    static {
        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverClassPath);
    }

    public static List<FundSnapshot> getLessList(List<String> fundCodeList) {
        List<FundSnapshot> list = new ArrayList<>();
        for (String fundCode : fundCodeList) {
            WebDriver driver = new ChromeDriver();
            driver.get(BASE_URL);
            WebElement searchBox = driver.findElement(By.id("qr_search")).findElements(By.className("right")).get(0).findElements(By.tagName("a")).get(0);
            searchBox.click();
            WebElement select = driver.findElement(By.id("fs_moreoptions"));
            select.click();
            WebElement input = driver.findElement(By.id("ctl00_cphMain_txtFund"));
            input.sendKeys(fundCode);
            WebElement searchButton = driver.findElement(By.id("ctl00_cphMain_btnGo"));
            searchButton.click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement table = driver.findElement(By.id("ctl00_cphMain_gridResult"));
            WebElement elementTr = table.findElements(By.tagName("tr")).get(1);
            List<WebElement> td = elementTr.findElements(By.tagName("td"));
            StringBuilder sb = new StringBuilder();
            FundSnapshot snapshot = new FundSnapshot();
            System.out.println(td.size());
            System.out.println(elementTr.getText());
            for (int j = 1; j < td.size(); j++) {
                String info = td.get(j).getText();
                switch (j) {
                    case 1:
                        snapshot.setFundCode(info);
                        String url = td.get(j).findElements(By.tagName("a")).get(0).getAttribute("href");
                        snapshot.setFundUrl(url);
                        break;
                    case 2:
                        snapshot.setFundName(info);
                        break;
                    case 3:
                        snapshot.setFundType(info);
                        break;
                    case 6:
                        try {
                            if (!info.equals("-")) {
                                snapshot.setValueDate(sdf.parse(info));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 7:
                        if (!info.equals("-")) {
                            snapshot.setUnitValue(info);
                        }
                        break;
                    case 8:
                        if (!info.equals("-")) {
                            snapshot.setValueDayChange(info);
                        }
                        break;
                    case 9:
                        if (!info.equals("-")) {
                            snapshot.setYeaPayPercent(info);
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
            snapshot.setPageSize(361);
            list.add(snapshot);
            System.out.println(sb.toString());
            System.out.println(snapshot.toString());
            driver.quit();
        }
        return list;
    }
}

