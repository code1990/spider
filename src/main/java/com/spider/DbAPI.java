package com.spider;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonArray;
import com.util.HttpClientUtil;
import com.util.TxtUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
//#老版
//http://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b
//        #新版
//        http://t.yushu.im/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b

//# 基地址
//http://t.yushu.im
//        # 关键字搜索
//        http://t.yushu.im/v2/book/search?q={}&start={}&count={}
//        # isbn搜索
//        http://t.yushu.im/v2/book/search/isbn/{isbn}
//        # 豆瓣api
//        https://api.douban.com/v2/book
//        复制代码

public class DbAPI {
    private String Main_URL = "http://t.yushu.im/v2/book/search?";
    private String apiKey = "0df993c66c0c636e29ecbb5344252a4a";
    private String userName = System.getProperty("user.name");
//    private String path = "C:\\Users\\" + userName + "\\Desktop\\ebook\\douban\\";
    private String path = "C:\\Users\\xiala\\Desktop\\ebook\\douban\\";
    private String kw = "netty";
    private String pathTxt = path + kw + ".txt";
    private String urlTxt = path + kw + "_url.txt";
    private String okTxt = path + kw + "_ok.txt";
    private String rateTxt = path + kw + "_rate.txt";
    private String catalogTxt = path + kw + "_catalog.txt";
    @Test
    public void allInfo() {
        saveBookUrl();
        saveInfo();
        dealInfo();
    }
    @Test
    public void getInfo()  {
        String url = "http://t.yushu.im/v2/book/search?apikey=0b2bdeda43b5688921839c8ecb20399b&q=hadoop&start=200&count=30000";
        String str = HttpClientUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
        JSONArray list = jsonObject.getJSONArray("books");
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = JSONObject.parseObject(list.get(i).toString());
            String author = object.get("author").toString();
            String title = object.get("title").toString();
            JSONObject rating = JSONObject.parseObject(object.get("rating").toString());
            String average = rating.get("average").toString();
            String numRaters = rating.get("numRaters").toString();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+title);
            String catalog = object.get("catalog").toString();
            String info = title + "\t" + average + "\t" + numRaters + "\t" + author;
            System.out.println(catalog);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+title);
            System.out.println(info);
        }
    }

    public void saveBookUrl() {
        List<String> urlList = new ArrayList<>();
        int total = 4000;
        for (int i = -1; i < total / 100; i++) {
            int start = 100 * i + 100;
            String url = Main_URL + "apikey=" + apiKey + "&q=" + kw + "&start=" + start + "&count=30000";
            System.out.println(url);
            urlList.add(url);
        }
        System.out.println(urlTxt);
        System.out.println(okTxt);
        BingSearchUtil.writeTxt(urlTxt, urlList);
        BingSearchUtil.writeTxt(okTxt, "0");
    }

    public void getBook() {
        List<String> readList = BingSearchUtil.readTxt(urlTxt);
        int start = new Integer(BingSearchUtil.readTxt(okTxt).get(0).trim());
        for (int i = start; i < readList.size(); i++) {

            String url = readList.get(i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String str = HttpClientUtil.get(url);
            int pageNumber = i + 1;
            if (i == 0) {
                BingSearchUtil.writeTxt(pathTxt, "");
            }
            //网络问题 导致必须一边读取一边保存
            List<String> bookInfoList = new ArrayList<>();
            bookInfoList.add(pageNumber + "\t" + str);
            BingSearchUtil.writeAppendTxt(pathTxt, bookInfoList);
            boolean flag = isEndPage(str);
            System.out.println(flag);
            if (!flag) {
                BingSearchUtil.writeTxt(okTxt, i + "");
            } else {
                BingSearchUtil.writeTxt(okTxt, "0");
                break;
            }
        }

    }

    public boolean isEndPage(String str) {
        JSONObject jsonObject = JSONObject.parseObject(str);
        Integer count = jsonObject.getInteger("count");
        Integer start = jsonObject.getInteger("start");
        Integer total = jsonObject.getInteger("total");
        System.out.println("start:"+start+"\tcount:"+count+"\t:total"+total);
        if (-1 == total || count + start == total) {
            return true;
        } else {
            return false;
        }
    }

    public void saveInfo() {
        //
        getBook();
    }



    private void dealInfo() {
        saveRate();
        saveCatalog();
    }

    private void saveRate() {
        List<String> resultList = BingSearchUtil.readTxt(pathTxt);
        List<String> rateList = new ArrayList<>();
        for (String str : resultList) {
            str = str.split("\t")[1];
            if("null".equalsIgnoreCase(str.trim())){
                continue;
            }
            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONArray list = jsonObject.getJSONArray("books");
            for (int i = 0; i < list.size(); i++) {
                JSONObject object = JSONObject.parseObject(list.get(i).toString());
                String author = "";
                String title = object.get("title").toString();
                JSONObject rating = JSONObject.parseObject(object.get("rating").toString());
                String average = rating.get("average").toString();
                String numRaters = rating.get("numRaters").toString();
                String info = title + "\t" + average + "\t" + numRaters + "\t" + author;
                rateList.add(info);
                System.out.println(info);
            }
        }
        BingSearchUtil.writeTxt(rateTxt, rateList);
    }

    private void saveCatalog() {
        List<String> resultList = BingSearchUtil.readTxt(pathTxt);
        List<String> catalogList = new ArrayList<>();
        for (String str : resultList) {
            str = str.split("\t")[1];
            if("null".equalsIgnoreCase(str.trim())){
                continue;
            }
            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONArray list = jsonObject.getJSONArray("books");
            for (int i = 0; i < list.size(); i++) {
                JSONObject object = JSONObject.parseObject(list.get(i).toString());
                String title = object.get("title").toString();
                String catalog = object.get("catalog").toString();
                JSONObject rating = JSONObject.parseObject(object.get("rating").toString());
                String average = rating.get("average").toString();
                String numRaters = rating.get("numRaters").toString();
                if(!catalog.equals("") && BingSearchUtil.isContainChinese(catalog)){
                    catalogList.add(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+title);
                    System.out.println(catalog);
                    catalogList.add(catalog);
                    TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\ebook\\douban\\"+kw+"\\"+numRaters+"_"+title+".md",catalog);
                    catalogList.add(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+title);
                }
            }
        }
        BingSearchUtil.writeTxt(catalogTxt, catalogList);
    }





}
