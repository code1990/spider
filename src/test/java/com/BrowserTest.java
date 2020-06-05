package com;

import com.spider.BingSearchUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowserTest {

    @Test
    public void getInfo123() throws Exception {
        long start = System.currentTimeMillis();
        WebDriver driver = BingSearchUtil.getChrome(true);
        String url = "http://www.baidu.com";
        driver.get(url);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement webElement = driver.findElement(By.id("su"));
        System.out.println(webElement.getAttribute("value"));
        System.out.println(System.currentTimeMillis() - start);
    }
    @Test
    public void testInfo(){
        System.out.println(111);
    }

    @Test
    public void getInfo1(){
        String path = "";
        System.out.println(BrowserTest.class.getName());
        System.out.println(BrowserTest.class.getClass() .getClassLoader().getResource(""));
    }

    @Test
    public void getInfo2(){
        String path ="C:\\Users\\xiala\\Desktop\\1.txt";
        List<String> list = TxtUtil.readTxt(path);
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            int sort = 100-i;
            String[] array = list.get(i).split("\t");
            for (int j = 0; j < array.length; ) {
                String key = array[j];
                String prefix="";
                switch (j){
                    case 0:
                        prefix="1w:";
                        break;
                    case 2:
                        prefix="1m:";
                        break;
                    case 4:
                        prefix="3m:";
                        break;
                    case 6:
                        prefix="6m:";
                        break;
                    case 8:
                        prefix="0.5y:";
                        break;
                }
                if(map.get(key)==null){
                    map.put(key,prefix+sort);
                }else{
                    map.put(key,map.get(key)+","+prefix+sort);
                }
//                System.out.println(key);
//                System.out.println(array[j+1]);
//                System.out.println(j);
                j=j+2;
            }
        }
        String[] arrays = {"1w","1m","3m","6m","0.5y"};
        for (Map.Entry<String,String> m:map.entrySet()) {
//            System.out.println(m.getKey()+"====>"+m.getValue());
            String[] value = m.getValue().split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <arrays.length ; i++) {
                String info = arrays[i];
                if(m.getValue().contains(info)){
                    String tmp = m.getValue().split(info+":")[1];
                    if(tmp.contains(",")){
                        tmp =tmp.split(",")[0];
                    }
                    sb.append(tmp+"\t");
                }else{
                    sb.append("\t");
                }

            }
            System.out.println(m.getKey()+"\t"+sb.toString());
        }

    }

}
