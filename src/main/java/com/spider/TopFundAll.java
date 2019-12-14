package com.spider;

import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: spider
 * @Date: 2019-12-09 21:30
 * @Author: code1990
 * @Description: 爬取基金数据 按照每日增长率排名50
 */
public class TopFundAll {
    String topic = "zs";
    String qdii = "http://fund.eastmoney.com/data/fundranking.html#t"+topic+";c0;r;sdm;pn50;dasc;qsd20181207;" +
            "qed20191207;qdii;zq;gg;gzbd;gzfs;bbzt;sfbb";
    String path = "C:\\Users\\admin\\Desktop\\fund123\\";
    int allPage = 16;
    String textPath = path + topic + ".txt";
    StringBuilder sb = new StringBuilder();

    @Test
    public void getInfo() throws InterruptedException {
        WebDriver driver = SpiderUtil.getChromeDriver(qdii, true);
        WebElement table = driver.findElement(By.id("dbtable"));
        WebElement pagebar = driver.findElement(By.id("pagebar"));
        Set<String> set = new HashSet<>();
        int index = 4;
        for (int i = 1; i <= allPage; i++) {
            Thread.sleep(5000);
            List<WebElement> list = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
//            Thread.sleep(5000);
            String text123="";
            int length = pagebar.findElements(By.tagName("label")).size();
            if (length == 9) {
                if (allPage - i <= 4) {
                    text123=pagebar.findElements(By.tagName("label")).get(index-1).getText();
                    pagebar.findElements(By.tagName("label")).get(index).click();
                    index++;
                } else {
                    text123=pagebar.findElements(By.tagName("label")).get(5).getText();
                    pagebar.findElements(By.tagName("label")).get(5).click();
                }

            } else {
                text123=pagebar.findElements(By.tagName("label")).get(i).getText();
                pagebar.findElements(By.tagName("label")).get(i).click();
            }
            for (WebElement element : list) {
                String text = element.getText();
                text = text.replaceAll("\n", "\t");
                text = text.replaceAll(" ", "\t");
                String[] textArray = text.split("\t");
                String fundNumber = textArray[0];
                String fundCode = textArray[1];
                String fundName = textArray[2];
                String info = fundNumber + "\t" + fundCode + "\t" + fundName + "\n";
                System.out.println(info);
                sb.append(info);
            }
            TxtUtil.writeTxt(textPath, sb.toString());

            Thread.sleep(1000);
        }
        TxtUtil.writeTxt(textPath,sb.toString());
    }

    @Test
    public void testInfo123() {
        String[] typeArray = new String[]{"gp", "hh", "zs", "qdii", "lof"};
        String[] zsArray = new String[]{"gzbd053", "gzbd054", "gzbd01", "gzbd02,03", "gzbd001", "gzbd003"};
        String[] zbArray = new String[]{"gzfs051", "gzfs052"};
        String[] qdiiArray = new String[]{"qdii311", "qdii312", "qdii313", "qdii314", "qdii315", "qdii316", "qdii317", "qdii318", "qdii319", "qdii320",
                "qdii320", "qdii330", "qdii340"};

    }

    @Test
    public void getNumber() {
        for (int i = 11; i <= 20; i++) {
            System.out.print("\"qdii3" + i + "\",");
        }
    }

    /*@Test
    public void formatInfo(){
        String path123 ="C:\\Users\\admin\\Desktop\\fund123\\tmp.log";
        List<String> list = TxtUtil.readTxt(path123);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String fundNumber = list.get(i);
            String fundCode = list.get(i+1);
            String fundName = list.get(i+3);
            i=i+4;
            String info = fundNumber+"\t"+fundCode+"\t"+fundName;
            System.out.println(info);
        }

//        TxtUtil.writeTxt(path123,sb.toString());
    }*/
}
