package com.spider;

import com.SpiderApplication;
import com.entity.FundBase;
import com.util.TxtUtil;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public static void getPageList(String num,String fundUrl){
        WebDriver driver = new ChromeDriver();
        //设置浏览器尺寸
        driver.get(fundUrl);
        //翻页加载需要时间 先等待一下
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element =null;
        String[] array = null;
        element = driver.findElement(By.id("qt_fund"));
        String fundCode = element.getText().split(" ")[0];
        String fundName = element.getText().split(" ")[1];
        //=====================================================
        element = driver.findElement(By.id("qt_base"));
        array = element.getText().split("\n");
        String unitValue = array[1];
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
        String allValue=array[20];
        String investMin=array[22];
        String salePlace=array[24];
        String preCost=array[26];
        String backCost=array[28];
        //=====================================================
        element = driver.findElement(By.id("qt_per"));
        array = element.getText().split("\n");
        String yearPayPercent = array[9];
        String basePayPercent= array[18];
        String commonPayPercent= array[27];
        //=====================================================
        element = driver.findElement(By.id("qt_return1"));
        array = element.getText().split("\n");
        String pay1month = array[5];
        String pay3month = array[10];
        String pay6month = array[15];
        String pay1year = array[20];
        String pay2year = array[25];
        String pay3year = array[30];
        String pay5year = array[35];
        String pay10year = array[40];
        //==================================================
        String lever3="0";
        String lever5="0";
        //=====================================================
        element = driver.findElement(By.id("qt_risk"));
        array = element.getText().split("\n");
        String avg3=array[7];
        String std3=array[14];
        String risk3=array[21];
        String sharp=array[28];
        //====================================================
        element = driver.findElement(By.id("qt_riskstats"));
        array = element.getText().split("\n");
        String baseA1=array[3];
        String commonA1=array[4];
        String baseB1=array[6];
        String commonB1=array[7];
        String baseSqrt=array[9];
        String commonSqrt=array[10];
        //======================================================
        element = driver.findElement(By.id("qt_stylel"));
        array = element.getText().split("\n");
        String fundStyle = array[0].split("：")[1];
        String fundScope = array[1].split("：")[1];
        //===============================================
        element = driver.findElement(By.id("qt_asset"));
        array = element.getText().split("\n");
        String moneyPercent = array[3];
        String stockPercent = array[6];
        String bondPercent = array[9];
        String otherPercent = array[12];
        //======================================================
        String allPercent = "";
        element = driver.findElement(By.id("qt_sector"));
        array=element.getText().split("\n");
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            if(str.contains(".") && !str.contains("-")){
                allPercent = array[i-1]+"\t"+array[i]+"\t"+array[i+1];
                break;
            }
        }
        //==============================================================
        String allStock = "";
        element = driver.findElement(By.id("qt_stock"));
        array=element.getText().split("\n");
        allStock = array[5]+array[7]+"\t"+array[9]+array[11]+"\t"+array[13]+array[15]+"\t"+array[17]+array[19]+"\t"+
                array[21]+array[23]+"\t"+array[25]+array[27]+"\t"+array[29]+array[31]+"\t"+array[33]+array[35]+"\t"
                +array[37]+array[39]+"\t"+array[41]+array[43];
        //==============================================
        String divInfo = "";
        driver.findElement(By.id("qt_dividendtab")).click();
        element =driver.findElement(By.id("qt_dividend")).findElement(By.className("content"));
        List<WebElement> lists = element.findElements(By.tagName("li"));
        StringBuilder sb1 = new StringBuilder();
        if(lists.size()!=0){
            for(WebElement element1:lists){
                sb1.append(element1.getText()+"\t");
            }
        }
        divInfo=sb1.toString();
        //=======================
        String splitInfo = "";
        driver.findElement(By.id("qt_splittab")).click();
        element =driver.findElement(By.id("qt_split")).findElement(By.className("content"));
        lists = element.findElements(By.tagName("li"));
        StringBuilder sb2 = new StringBuilder();
        if(lists.size()!=0){
            for(WebElement element1:lists){
                sb2.append(element1.getText()+"\t");
            }
        }
        splitInfo=  sb2.toString();
        //=============================================end
        FundBase fundBase = new FundBase();
        fundBase.setNum( num);
        fundBase.setFundCode( fundCode);
        fundBase.setFundName( fundName);
        fundBase.setUnitValue( unitValue);
        fundBase.setValueDate( valueDate);
        fundBase.setValueDayChange( valueDayChange);
        fundBase.setValueDayPercent( valueDayPercent);
        fundBase.setFundType( fundType);
        fundBase.setStartDate( startDate);
        fundBase.setOpenDate( openDate);
        fundBase.setSaleDate( saleDate);
        fundBase.setApplyStatus( applyStatus);
        fundBase.setRansomStatus( ransomStatus);
        fundBase.setInvestStyle( investStyle);
        fundBase.setAllValue( allValue);
        fundBase.setInvestMin( investMin);
        fundBase.setSalePlace( salePlace);
        fundBase.setPreCost( preCost);
        fundBase.setBackCost( backCost);
        fundBase.setYearPayPercent( yearPayPercent);
        fundBase.setBasePayPercent( basePayPercent);
        fundBase.setCommonPayPercent( commonPayPercent);
        fundBase.setPay1month( pay1month);
        fundBase.setPay3month( pay3month);
        fundBase.setPay6month( pay6month);
        fundBase.setPay1year( pay1year);
        fundBase.setPay2year( pay2year);
        fundBase.setPay3year( pay3year);
        fundBase.setPay5year( pay5year);
        fundBase.setPay10year( pay10year);
        fundBase.setLever3( lever3);
        fundBase.setLever5( lever5);
        fundBase.setAvg3( avg3);
        fundBase.setStd3( std3);
        fundBase.setRisk3( risk3);
        fundBase.setSharp( sharp);
        fundBase.setBaseA1( baseA1);
        fundBase.setBaseB1( baseB1);
        fundBase.setBaseSqrt( baseSqrt);
        fundBase.setCommonA1( commonA1);
        fundBase.setCommonB1( commonB1);
        fundBase.setCommonSqrt( commonSqrt);
        fundBase.setFundStyle( fundStyle);
        fundBase.setFundScope( fundScope);
        fundBase.setMoneyPercent( moneyPercent);
        fundBase.setStockPercent( stockPercent);
        fundBase.setBondPercent( bondPercent);
        fundBase.setOtherPercent( otherPercent);
        fundBase.setAllPercent( allPercent);
        fundBase.setAllStock( allStock);
        fundBase.setDivInfo( divInfo);
        fundBase.setSplitInfo( splitInfo);
        fundBase.setCreateTime(new Date());
        fundBase.setFundUrl( fundUrl);
        System.out.println(fundBase.toString());
        driver.quit();
//        logger.error("第"+pageNum+"页爬取>>>>>>>>>>>end\t,\t数量"+list.size());
//        return list;
    }

    public static void main(String[] args) throws InterruptedException {
        String fundUrl = "http://cn.morningstar.com/quicktake/0P0001606X";
        getPageList("1",fundUrl);
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

