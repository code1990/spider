package com.util;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Web自动化测试工具类 可以使用java来模拟浏览器行为 实现对于动态页面内容的爬取
 *
 * @author issuser
 */
public class SeleniumUtil {

    /**
     * 获取一个默认的浏览器创建实例
     *
     * @return
     */
    public static WebDriver getInstance() {
        //chrome浏览器安装路径 32位
        String chromebin = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
        //chromedriver浏览器驱动文件路径 32位
        String chromedriver = "D:\\sdk\\driver\\chromedriver.exe";

        /*  把chrome浏览器的位置设置为JVM全局的系统变量 */
        System.setProperty("webdriver.chrome.bin", chromebin);
        /* 把chrome浏览器的驱动陈旭设置JVM全局的系统变量*/
        System.setProperty("webdriver.chrome.driver", chromedriver);
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    /**
     * 打开浏览器
     */
    @Test
    public void testOpenBrowser() {
        WebDriver driver = SeleniumUtil.getInstance();
        driver.get("http://www.baidu.com");
        try {
            Thread.sleep(6000);
            //获取网页的title属性
            System.out.println(driver.getTitle());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            driver.close();//关闭浏览器
            driver.quit();//退出浏览器
        }
    }

    /**
     * 测试定位元素 获取页面给定的元素
     * <p>
     * Selenium提供了8种定位方式。
     * <p>
     * id
     * name
     * class name
     * tag name
     * link text
     * partial link text
     * xpath
     * css selector
     * 这8种定位方式在Java selenium中所对应的方法为：
     * <p>
     * findElement(By.id())
     * findElement(By.name())
     * findElement(By.className())
     * findElement(By.tagName())
     * findElement(By.linkText())
     * findElement(By.partialLinkText())
     * findElement(By.xpath())
     * findElement(By.cssSelector())
     */
    @Test
    public void testFindElement() {
        WebDriver driver = SeleniumUtil.getInstance();
        driver.get("http://www.baidu.com");
        try {
            //通过id定位:
            System.out.println(driver.findElement(By.id("kw")));

            //通过name定位:
            System.out.println(driver.findElement(By.name("wd")));

            //通过class name定位:
            System.out.println(driver.findElement(By.className("s_ipt")));

            //通过tag name定位:
            System.out.println(driver.findElement(By.tagName("input")));

            //通过xpath定位，xpath定位有N种写法，这里列几个常用写法:
//            System.out.println(driver.findElement(By.xpath("//*[@id='kw']")));
//            System.out.println(driver.findElement(By.xpath("//*[@name='wd']")));
//            System.out.println(driver.findElement(By.xpath("//input[@class='s_ipt']")));
//            System.out.println(driver.findElement(By.xpath("/html/body/form/span/input")));
//            System.out.println(driver.findElement(By.xpath("//span[@class='soutu-btn']/input")));
//            System.out.println(driver.findElement(By.xpath("//form[@id='form']/span/input")));
//            System.out.println(driver.findElement(By.xpath("//input[@id='kw' and @name='wd']")));

            //通过css定位，css定位有N种写法，这里列几个常用写法:
//            System.out.println(driver.findElement(By.cssSelector("#kw")));
//            System.out.println(driver.findElement(By.cssSelector("[name=wd]")));
//            System.out.println(driver.findElement(By.cssSelector(".s_ipt")));
//            System.out.println(driver.findElement(By.cssSelector("html > body > form > span > input")));
//            System.out.println(driver.findElement(By.cssSelector("span.soutu-btn> input#kw")));
//            System.out.println(driver.findElement(By.cssSelector("form#form > span > input")));

            //接下来，我们的页面上有一组文本链接。
            //通过link text定位:
            System.out.println(driver.findElement(By.linkText("新闻")));
            System.out.println(driver.findElement(By.linkText("hao123")));

            //通过link text定位:
            System.out.println(driver.findElement(By.partialLinkText("新")));
            System.out.println(driver.findElement(By.partialLinkText("hao")));
            System.out.println(driver.findElement(By.partialLinkText("123")));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            driver.close();//关闭浏览器
            driver.quit();//退出浏览器
        }
    }

    /**
     * 控制浏览器大小
     * WebDriver 提供了 manage().window().setSize()方法来设置浏览器的大小。
     *back()和forward()方法来模拟后退和前进按钮
     * refresh() 刷新页面（F5）
     */

    @Test
    public void testControlBrower(){
        WebDriver driver = SeleniumUtil.getInstance();
        driver.get("https://www.baidu.cn");
        driver.manage().window().maximize();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("https://m.baidu.cn");
        driver.manage().window().setSize(new Dimension(480, 800));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //get 到百度首页
        driver.get("https://www.baidu.com/");
        System.out.printf("now accesss %s \n", driver.getCurrentUrl());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //点击“新闻” 链接
        driver.findElement(By.linkText("新闻")).click();
        System.out.printf("now accesss %s \n", driver.getCurrentUrl());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //执行浏览器后退
        driver.navigate().back();
        System.out.printf("back to %s \n", driver.getCurrentUrl());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //执行浏览器前面
        driver.navigate().forward();
        System.out.printf("forward to %s \n", driver.getCurrentUrl());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    /*
    * clear() 清除文本。
sendKeys(*value) 模拟按键输入。
click() 单击元素
submit()方法用于提交表单
getSize() 返回元素的尺寸。

getText() 获取元素的文本。

getAttribute(name) 获得属性值。

isDisplayed() 设置该元素是否用户可见。
    * */
    @Test
    public void testTextBrower(){
        WebDriver driver = SeleniumUtil.getInstance();
        driver.get("https://www.baidu.com/");

        WebElement search_text = driver.findElement(By.id("kw"));
        WebElement search_button = driver.findElement(By.id("su"));

        search_text.sendKeys("Java");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        search_text.clear();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        search_text.sendKeys("Selenium");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        search_button.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("https://www.baidu.com/");
        //获得百度输入框的尺寸
        WebElement size = driver.findElement(By.id("kw"));
        System.out.println(size.getSize());

        //返回百度页面底部备案信息
        WebElement text = driver.findElement(By.id("cp"));
        System.out.println(text.getText());

        //返回元素的属性值， 可以是 id、 name、 type 或元素拥有的其它任意属性
        WebElement ty = driver.findElement(By.id("kw"));
        System.out.println(ty.getAttribute("type"));

        //返回元素的结果是否可见， 返回结果为 True 或 False
        WebElement display = driver.findElement(By.id("kw"));
        System.out.println(display.isDisplayed());
        driver.quit();
    }

    /*
    * Actions 类提供了鼠标操作的常用方法：
contextClick() 右击
clickAndHold() 鼠标点击并控制
doubleClick() 双击
dragAndDrop() 拖动
release() 释放鼠标
perform() 执行所有Actions中存储的行为
import org.openqa.selenium.interactions.Actions;
导入提供鼠标操作的 ActionChains 类

Actions(driver) 调用Actions()类，将浏览器驱动driver作为参数传入。

clickAndHold() 方法用于模拟鼠标悬停操作， 在调用时需要指定元素定位。

perform() 执行所有ActionChains中存储的行为， 可以理解成是对整个操作的提交动作。
    * */
    @Test
    public void testMouseEvent(){
        WebDriver driver = SeleniumUtil.getInstance();
        driver.get("https://www.baidu.com/");

        WebElement search_setting = driver.findElement(By.linkText("设置"));
        Actions action = new Actions(driver);
        action.clickAndHold(search_setting).perform();
        action.release().perform();
//        Actions action = new Actions(driver);

// 鼠标右键点击指定的元素
        action.contextClick(driver.findElement(By.id("kw"))).perform();

// 鼠标右键点击指定的元素
        action.doubleClick(driver.findElement(By.id("su"))).perform();

// 鼠标拖拽动作， 将 source 元素拖放到 target 元素的位置。
        WebElement source = driver.findElement(By.name("tj_trnews"));
        WebElement target = driver.findElement(By.name("wd"));
        action.dragAndDrop(source,target).perform();

// 释放鼠标
        action.release().perform();
        driver.quit();
    }
/*
*
* Keys()类提供了键盘上几乎所有按键的方法。 前面了解到， sendKeys()方法可以用来模拟键盘输入，
* 除此之 外， 我们还可以用它来输入键盘上的按键， 甚至是组合键， 如 Ctrl+A、 Ctrl+C 等。
* 以下为常用的键盘操作：

sendKeys(Keys.BACK_SPACE) 回格键（BackSpace）

sendKeys(Keys.SPACE) 空格键(Space)

sendKeys(Keys.TAB) 制表键(Tab)

sendKeys(Keys.ESCAPE) 回退键（Esc）

sendKeys(Keys.ENTER) 回车键（Enter）

sendKeys(Keys.CONTROL,‘a’) 全选（Ctrl+A）

sendKeys(Keys.CONTROL,‘c’) 复制（Ctrl+C）

sendKeys(Keys.CONTROL,‘x’) 剪切（Ctrl+X）

sendKeys(Keys.CONTROL,‘v’) 粘贴（Ctrl+V）

sendKeys(Keys.F1) 键盘 F1

……

sendKeys(Keys.F12) 键盘 F12
* */
    @Test
    public void keyEvent() throws Exception{
        WebDriver driver = SeleniumUtil.getInstance();
        driver.get("https://www.baidu.com");

        WebElement input = driver.findElement(By.id("kw"));

        //输入框输入内容
        input.sendKeys("seleniumm");
        Thread.sleep(2000);

        //删除多输入的一个 m
        input.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(2000);

        //输入空格键+“教程”
        input.sendKeys(Keys.SPACE);
        input.sendKeys("教程");
        Thread.sleep(2000);

        //ctrl+a 全选输入框内容
        input.sendKeys(Keys.CONTROL,"a");
        Thread.sleep(2000);

        //ctrl+x 剪切输入框内容
        input.sendKeys(Keys.CONTROL,"x");
        Thread.sleep(2000);

        //ctrl+v 粘贴内容到输入框
        input.sendKeys(Keys.CONTROL,"v");
        Thread.sleep(2000);

        //通过回车键盘来代替点击操作
        input.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        driver.quit();
    }
    /**
     * //移动到窗口绝对位置坐标，如下移动到纵坐标1600像素位置
     * ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1600)");
     * Thread.sleep(3000);
     * //结合上面的scrollTo语句，仍然移动到纵坐标1200像素位置
     * ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1200)");
     *
     *
     */
}
