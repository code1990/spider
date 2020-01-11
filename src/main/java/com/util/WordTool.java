package com.util;

import com.alibaba.fastjson.JSON;
import com.spider.BingSearchUtil;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.dictionary.DictionaryFactory;
import org.apdplat.word.segmentation.Word;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WordTool implements Serializable {

    public static void automaticSelection(String title) {
        //移除停用词进行分词
        List<Word> list = WordSegmenter.seg(title);

        System.out.println(JSON.toJSONString(list));

        //保留停用词
        List<Word> lists = WordSegmenter.segWithStopWords(title);
        System.out.println(JSON.toJSONString(lists));
        //移除停用词：
        List<Word> words = WordSegmenter.seg("杨尚川是APDPlat应用级产品开发平台的作者");
        //保留停用词：
        List<Word> wordss = WordSegmenter.segWithStopWords("杨尚川是APDPlat应用级产品开发平台的作者");
        System.out.println(words);
        System.out.println(wordss);

    }

    public static void main(String[] args) {
        WordTool.automaticSelection("我叫李太白，我是一个诗人，我生活在唐朝");
    }

    @Test
    public void getInfo(){
        String url = "https://www.baidu.com/";
        WebDriver driver = BingSearchUtil.getChrome(true);
        driver.get(url);
        System.out.println(driver.getTitle());
        System.out.println();
    }

}
