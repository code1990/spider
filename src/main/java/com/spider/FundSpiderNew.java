package com.spider;

import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: spider
 * @Date: 2020-01-01 15:24
 * @Author: code1990
 * @Description:
 */
public class FundSpiderNew {
    private int allPage = 2;
    private String url = "https://cn.morningstar.com/quickrank/default.aspx";
    private String userName = System.getProperty("user.name");
    private String path = "C:\\Users\\" + userName + "\\Desktop\\ebook\\fund\\";
    private String kw = "基金";
    private String pathTxt = path + kw + ".txt";

    private String okTxt = path + kw + "_ok.txt";
    private String sortTxt = path + kw + "_sort.txt";

    @Test
    public void restart() {
        BingSearchUtil.writeTxt(okTxt, "1");
    }

    @Test
    public void testInfo() throws Exception {
        WebDriver driver = BingSearchUtil.getChrome(true);
        driver.get(url);
        driver.findElement(By.id("ctl00_cphMain_cblStarRating_0")).click();
        driver.findElement(By.id("ctl00_cphMain_btnGo")).submit();
        int start = new Integer(BingSearchUtil.readTxt(okTxt).get(0).trim());
        for (int i = start; i <= 58; i++) {
            List<String> resultList = getResultList(driver, url, i);
            BingSearchUtil.writeAppendTxt(pathTxt, resultList);
            BingSearchUtil.writeTxt(okTxt, i + "");
        }

    }

    public List<String> getResultList(WebDriver driver, String url, int i) throws InterruptedException {
        //点击翻页
        ((JavascriptExecutor) driver).executeScript("javascript:__doPostBack('ctl00$cphMain$AspNetPager1','" + i
                + "')");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list = driver.findElement(By.id("ctl00_cphMain_gridResult")).findElements(By.tagName
                ("tr"));
        List<String> resultList = new ArrayList<>();
        for (int j = 1; j < list.size(); j++) {
            String info = list.get(j).getText().replaceAll("\n", "\t");
            System.out.println(info);
            resultList.add(info);
        }
        return resultList;
    }

    @Test
    public void sortList(){
        List<String> sortList = new ArrayList<>();
        for (String s : TxtUtil.readTxt(pathTxt)) {
            String[] array = s.split("\t");
            String info = array[0] + "\t" + "#"+array[1] + "\t" + array[2] + "\t" + array[3];
            System.out.println(info);
            sortList.add(info);
        }
        BingSearchUtil.writeTxt(sortTxt, sortList);
    }

    @Test
    public void getFundInfo111(){
        String str ="C:\\Users\\admin\\Desktop\\111111.txt";
        String str2 ="C:\\Users\\admin\\Desktop\\222.txt";
        List<String> list2 = BingSearchUtil.readTxt(str2);
        List<String> list = BingSearchUtil.readTxt(str);
        List<String> list22 = new ArrayList<>();
        List<String> list33 = new ArrayList<>();
        for (int j = 0; j <list2.size() ; j++) {
            String[] array = list2.get(j).split("\t");
//            System.out.println(array[1]);
//            System.out.println(array[6]);
            list22.add(array[1]);
            list33.add(array[6]);
        }
        System.out.println("=========");
        for (int i = 0; i <list.size() ; i++) {
            int index =-1;
            for (int j = 0; j <list22.size() ; j++) {
                if(list.get(i).replaceAll("#","").equals(list22.get(j))){
//                    System.out.println(list.get(i)+"\t"+list22.get(j));
                    index=j;
                    break;
                }
            }
            String str11 ="";
            if(index!=-1){
               str11=list33.get(index);
            }
            System.out.println(list.get(i)+"\t"+str11);
        }

    }
}