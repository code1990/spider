package com;

//import com.FundApplication;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @program: fund
 * @Date: 2019-10-30 23:22
 * @Author: code1990
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        String driverClassPath = TxtUtil.class.getClassLoader().getResource("chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver",driverClassPath);
        WebDriver driver= new ChromeDriver();
        driver.get("https://www.baidu.cn");

        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.get("https://m.baidu.cn");
        driver.manage().window().setSize(new Dimension(480, 800));
        Thread.sleep(2000);

        driver.quit();
    }
}
