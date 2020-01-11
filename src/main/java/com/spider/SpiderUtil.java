package com.spider;

import com.SpiderApplication;
import org.junit.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.springframework.boot.logging.LogLevel;

import java.io.IOException;
import java.util.logging.Level;

/**
 * @author wei
 * @description
 * @date 2019/11/19
 */
public class SpiderUtil {

    static {
//        String driverClassPath = SpiderApplication.class.getClassLoader().getResource("chromedriver.exe").getPath();
//        System.setProperty("webdriver.chrome.driver", driverClassPath);
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver78.exe");
        //控制台关闭日志
        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
    }

    public static WebDriver getChromeDriver(String url) {
        //设置浏览器可选项
        ChromeOptions options = new ChromeOptions();
        //隐藏浏览器
        options.addArguments("--headless");
        //单进程运行Google Chrome
        options.addArguments("–single-process");
        //禁止加载图片
        options.addArguments("–disable-images");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }

    //
//    public ChromeOptions hideBrowser(){
//        //设置浏览器可选项
//        ChromeOptions options = getDefaultBrowser();
//        //隐藏浏览器
//        options.addArguments("--headless");
//        return options;
//    }
//
    public static ChromeOptions getDefaultBrowser() {
        //设置浏览器可选项
        ChromeOptions options = new ChromeOptions();
        //单进程运行Google Chrome
        options.addArguments("–single-process");
        //禁止加载图片
        options.addArguments("–disable-images");
        return options;
    }

    public static WebDriver getChromeDriver(String url, boolean show) {
        //设置浏览器可选项
        ChromeOptions options = new ChromeOptions();
        if (!show) {
            //隐藏浏览器
            options.addArguments("--headless");
        }
        //单进程运行Google Chrome
        options.addArguments("–single-process");
        //禁止加载图片
        options.addArguments("–disable-images");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }

    public static void chromeQuit(WebDriver driver) {
        driver.quit();
        try {
            Runtime.getRuntime().exec("taskkill /F /im " + "chromedriver.exe");
            Runtime.getRuntime().exec("taskkill /F /im " + "chrome.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异常退出杀死chromedriver和chrome浏览器！
    }

    @Test
    public void testInfo123() {
        String url = "https://www.baidu.com/";
        WebDriver driver = getChromeDriver(url, false);
        chromeQuit(driver);
    }
}

