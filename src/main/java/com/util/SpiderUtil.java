package com.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author wei
 * @description
 * @date 2019/11/1
 */
public class SpiderUtil {

    public static void cutScreen(WebDriver driver,int pageNum){
        /**
         * 截屏操作
         * 图片已当前时间命名
         */
        driver.manage().window().maximize();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500)");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //转换时间格式
        String time = dateFormat.format(Calendar.getInstance().getTime()); //获取当前时间
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); //执行屏幕截取
        try {
            FileUtils.copyFile(srcFile, new File("屏幕截图", pageNum + ".png")); //利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件;"屏幕截图"即时保存截图的文件夹
            System.out.println("==============>ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

