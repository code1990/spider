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
 * @Date: 2020-01-01 16:57
 * @Author: code1990
 * @Description:
 */
public class FundSpiderBaseNew {
    private int allPage = 2;
    private String url = "http://fund.eastmoney.com/";
    private String userName = System.getProperty("user.name");
    private String path = "C:\\Users\\" + userName + "\\Desktop\\ebook\\fund\\";
    private String kw = "fund";
    private String pathTxt = path + kw + ".txt";
    private String urlTxt = path + kw + "_url.txt";
    private String okTxt = path + kw + "_ok.txt";
    private String sortTxt = path + kw + "_sort.txt";

    @Test
    public void restart() {
        BingSearchUtil.writeTxt(okTxt, "0");
    }

    @Test
    public void getFundUrl(){
        List<String> list = BingSearchUtil.readTxt(pathTxt);
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            String info = url+list.get(i)+".html";
            System.out.println(info);
            urlList.add(info+"\t"+list.get(i));
        }
        BingSearchUtil.writeTxt(urlTxt,urlList);
    }

    @Test
    public void getMonthInfo() throws Exception {
        WebDriver driver = BingSearchUtil.getChrome(true);
        int start = new Integer(BingSearchUtil.readTxt(okTxt).get(0).trim());
        List<String> list = BingSearchUtil.readTxt(urlTxt);
        for (int i = start; i <list.size(); i++) {
            String url = list.get(i).split("\t")[0];
            String fundCode = list.get(i).split("\t")[1];
            if(fundCode.equals("960008")){
                continue;
            }
            if(fundCode.equals("968011")){
                continue;
            }
            if(fundCode.equals("968019")){
                continue;
            }
            if(fundCode.equals("968016")){
                continue;
            }
            if(fundCode.equals("960000")){
                continue;
            }
            if(fundCode.equals("960020")){
                continue;
            }
            if(fundCode.equals("960021")){
                continue;
            }
            if(fundCode.equals("960002")){
                continue;
            }
            if(fundCode.equals("217012")){
                continue;
            }
            if(fundCode.startsWith("960") || fundCode.startsWith("968")){
                continue;
            }

            System.out.println(">>>>>>>>>>>>>"+fundCode);
            String str = getResult(driver,url , fundCode);
            BingSearchUtil.writeAppendTxt(pathTxt, str);
            System.out.println(i);
            BingSearchUtil.writeTxt(okTxt, i + "");
        }

    }

    public String getResult(WebDriver driver, String url, String fundCode) throws InterruptedException {
        driver.get(url);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement(By.id("increaseAmount_stage"));
        element = element.findElements(By.tagName("tr")).get(1);
        String month = element.findElements(By.tagName("td")).get(2).getText();
        System.out.println(fundCode+"\t"+month);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fundCode+"\t"+month;
    }

    @Test
    public void testInfo(){
        String path ="C:\\Users\\admin\\Desktop\\111111.txt";
        List<String> list = BingSearchUtil.readTxt(path);
        double index = 6.06;
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String fundCode = str.split("\t")[0];
            String value = str.split("\t")[1].replace("%","");
            if(new Double(value)>=index){
                if(new Double(value)>=2*index){
                    System.out.println(">>>>>>>"+str);
                }else if (new Double(value)>=10.0){
//                    System.out.println("########"+str);
                }
            }
        }

    }
}
