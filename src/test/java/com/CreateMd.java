package com;

//import io.TxtUtil;

import com.spider.BingSearchUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateMd {
    private  String txtPath = "C:\\Users\\admin\\Desktop\\ebook\\book123.txt";
    private  String path = "F:\\github\\word\\stock\\";
    private  String bookName = "MBA数学";
    private  String type = ".md";
    private  String bookPath = path+bookName+type;

    @Test
    public void getBookMd(){
        List<String> list = BingSearchUtil.readTxt(txtPath);
        List<String> resultList = new ArrayList<>();
        resultList.add("# "+bookName);
        for(String str:list){
            String head = "## ";
            if (str.contains("章") || isStartWithNumber(str)){
                head="### ";
            }
            if (!str.contains("节")){
                resultList.add(head+str);
            }

            System.out.println(head+str);
        }
        BingSearchUtil.writeTxt(bookPath,resultList);
    }

    public static boolean isStartWithNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0)+"");
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
