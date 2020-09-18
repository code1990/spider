package com;

import com.util.MapUtil;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 911
 * @date 2020-07-04 19:21
 */
public class FundInfoTest {

    @Test
    public void getInfo() {
        String path = "C:\\Users\\xiala\\Desktop\\fund123\\QDII.txt";
        String path2 = "C:\\Users\\xiala\\Desktop\\fund123\\MM.txt";
        String path3 = "C:\\Users\\xiala\\Desktop\\fund123\\rs_QDII.txt";
        List<String> list = TxtUtil.readTxt(path);
        List<String> list2 = TxtUtil.readTxt(path2);
        List<String> list3 = TxtUtil.readTxt(path3);
//        Map<String,String> map = new HashMap<>();
//        for (int i = 1; i < list.size(); i++) {
//            String[] info = list.get(i).split("\t");
//            map.put(info[0],info[1]);
//        }
        Map<String, String> map2 = new HashMap<>();
        for (int i = 1; i < list2.size(); i++) {
            String[] info = list2.get(i).split(",");
            map2.put(info[1], list2.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
//            String info = "";
//            System.out.println(list.get(i));
            String key = list.get(i).split("\t")[1];
            String info = map2.get(key) + "\t" + list3.get(i);
            System.out.println(info.replaceAll(",", "\t"));
//            System.out.println(map2.get(key));
        }
    }

    @Test
    public void getInfo01() {
        String path = "C:\\Users\\xiala\\Desktop\\fund123\\111.txt";
        List<String> list = TxtUtil.readTxt(path);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < list.size(); i++) {
            String info = list.get(i);
//            System.out.println(info);
            String[] array = info.split(",");
            int length = array.length;
            if (length == 8 && !array[7].contains("-") && !info.contains("其它")
                    && !info.contains("货币市场基金") && !info.contains("债")) {
                sb.append(info.replaceAll(",", "\t") + "\n");
//                System.out.println(length);
//                System.out.println(info);
            }


        }
        TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\fund123\\222.txt", sb.toString());
    }

    @Test
    public void ccmx() {
        String path = "C:\\Users\\xiala\\Desktop\\fund123\\fund_pool.txt";
        List<String> list = TxtUtil.readTxt(path);
        for (int i = 0; i < list.size(); i++) {
            String info = list.get(i).split("\t")[1];
            System.out.println("http://quotes.money.163.com/fund/cgmx_210008.html".replace("210008", info));
        }
    }

    @Test
    public void getInfo111() {
        String path = "C:\\Users\\xiala\\Desktop\\fund123\\current.txt";
        List<String> list = TxtUtil.readTxt(path);
        for (int i = 0; i < list.size(); i++) {
            String info = list.get(i);
//            .split("\t")[1];
//            System.out.println("http://quotes.money.163.com/fund/cgmx_210008.html".replace("210008",info));
            if (info.contains("债")) {
                continue;
            }
            if (info.split("\t")[7].contains("-")) {
                continue;
            }
            if (new Double(info.split("\t")[7]) < 10.0) {
                continue;
            }
//            System.out.println("http://fundf10.eastmoney.com/ccmx_210008.html".replace("210008",info.split("\t")[1]));
            System.out.println("http://quotes.money.163.com/fund/cgmx_210008.html".replace("210008", info.split("\t")[1]));
//            System.out.println(info.split("\t").length);
//            System.out.println(info);
        }
    }

    @Test
    public void getInfo1118() {
        String path = "C:\\Users\\xiala\\Desktop\\fund123\\ccmx_ok_163.txt";
        TxtUtil.splitTxt(path,600);
        path ="C:\\Users\\xiala\\Desktop\\fund123\\gn_url.txt";
        TxtUtil.splitTxt(path,50);
//        List<String> list = TxtUtil.readTxt(path);
////        double sum =0.0;
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < list.size(); i++) {
//            String info = list.get(i);
//            if(i<list.size()-1){
//                if((i+1)%600==0){
//                    sb.append(info+"\n");
//                    TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\fund123\\ccmx_ok_es_"+(i+1)/600+".txt",sb.toString());
//                    sb.setLength(0);
//                }else{
//                    sb.append(info+"\n");
//                }
//            }else{
//                TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\fund123\\ccmx_ok_es_"+((i+1)/600+1)+".txt",sb.toString());
//            }
//
//
//        }
    }


    @Test
    public void ccmx1() {
        String path = "C:\\Users\\xiala\\Desktop\\fund123\\222";
        List<String> list = new ArrayList<>();
        for(File file:new File(path).listFiles()){
            List<String> list1 = TxtUtil.readTxt(file.getAbsolutePath());
            list.addAll(list1);
        }
        TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\fund123\\gnmx_123.txt",list);

    }

    @Test
    public void getInfo1111(){
        String path = "C:\\\\Users\\\\xiala\\\\Desktop\\\\fund123\\\\gnmx_123.txt";
        String app = "C:\\Users\\xiala\\Desktop\\fund123\\hy_mx.txt";
        List<String> list = TxtUtil.readTxt(path);
        List<String> list2 = TxtUtil.readTxt(app);
        for (int j = 0; j < list2.size(); j++) {
            String str = list2.get(j);
            String code = str.split("\t")[1];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                String info = list.get(i);
                if(info.contains(code)){
                    sb.append(info.split("\t")[3]+",");
                }
            }
            System.out.println(str+"\t"+sb.toString());
//            System.out.println("update table fund_com_hy set gn_name='"+sb.toString()+"' where com_code='"+code+"';");
        }

    }

    @Test
    public void getInfo99(){
        String path ="C:\\Users\\xiala\\Desktop\\123.txt";
        List<String> list = TxtUtil.readTxt(path);
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String info = list.get(i).split(",")[0];
            if(map.get(info)==null){
                map.put(info,1);
            }else{
                map.put(info,map.get(info)+1);
            }
        }
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"\t"+ entry.getValue());
        }
    }

    @Test
    public void getInfo909() {
        String path = "C:\\Users\\xiala\\Desktop\\tmp.txt";
        List<String> list = TxtUtil.readTxt(path);
        for (int i = 0; i <list.size() ; i++) {
            String[] array = list.get(i).split("\t");
            System.out.println("insert into fund_gn (gn_name,gn_url) values('"+array[1]+"','"+array[2]+"');");
        }
    }

    @Test
    public void getInfo56(){
        String path ="C:\\Users\\xiala\\Desktop\\fund123\\ccmx_123.txt";
        List<String> list = TxtUtil.readTxt(path);
        Map<String,String> map = new HashMap<>();
        Map<String,String> map2 = new HashMap<>();
        Map<String,String> map3 = getcodehy();
        Map<String,String> map4 = getcodegn();
        for (int i = 0; i <list.size() ; i++) {
            String info =list.get(i);
//            System.out.println(info);
            if(info.startsWith(",")){
                continue;
            }
            String[] array = info.split(",");
            String name = array[0].split(" ")[0];
            String code = array[0].split("\\(")[1].replace(")","");
            String code2 = array[2];
//            System.out.println();
            String key = name+"\t"+code;
            if(map.get(key)==null){
                map.put(key,map3.get(code2));
                map2.put(key,map4.get(code2));
            }else{
                map.put(key,map3.get(code2)+","+map.get(key));
                map2.put(key,map4.get(code2)+","+map2.get(key));
            }
//            System.out.println(name);
//            System.out.println(code);
//            System.out.println(array[0]);

        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String> m:map.entrySet()){
            if(m.getValue()==null){
                continue;
            }
            String[] array = m.getValue().split(",");
            Map<String,Integer> map5 = new HashMap<>();
            for (int i = 0; i < array.length; i++) {
                String key = array[i];
                if(map5.get(key)==null){
                    map5.put(key,1);
                }else{
                    map5.put(key,1+map5.get(key));
                }
            }
            sb.append(m.getKey()+"\t"+map5.toString()+"\n");
//            System.out.println();
        }
        TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\fund123\\hy123",sb.toString());
        sb.setLength(0);
        for(Map.Entry<String,String> m:map2.entrySet()){
            if(m.getValue()==null){
                continue;
            }
            String[] array = m.getValue().split(",");
            Map<String,Integer> map5 = new HashMap<>();
            for (int i = 0; i < array.length; i++) {
                String key = array[i];
                if(key.equals("")){
                    continue;
                }
                if(map5.get(key)==null){
                    map5.put(key,1);
                }else{
                    map5.put(key,1+map5.get(key));
                }
            }
            sb.append(m.getKey()+"\t"+map5.toString()+"\n");
//            System.out.println(m.getKey()+"\t"+map5.toString());
        }
        TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\fund123\\gn123",sb.toString());
    }

    public Map<String,String> getcodehy(){
        String path ="C:\\Users\\xiala\\Desktop\\fund123\\mx123.txt";
        List<String> list = TxtUtil.readTxt(path);
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i <list.size() ; i++) {
            String info =list.get(i);
//            System.out.println(info);
            String[] array = info.split("\t");
            map.put(array[1],array[3]);
        }
//        for(Map.Entry<String,String> m:map.entrySet()){
//            System.out.println(m.getKey()+"\t"+m.getValue());
//        }
        return map;
    }

    public Map<String,String> getcodegn(){
        String path ="C:\\Users\\xiala\\Desktop\\fund123\\mx123.txt";
        List<String> list = TxtUtil.readTxt(path);
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i <list.size() ; i++) {
            String info =list.get(i);
            String[] array = info.split("\t");
//            System.out.println(array.length);
            String value ="";
            if(array.length==5){
                value=array[4];
            }
            map.put(array[1],value);
        }
//        for(Map.Entry<String,String> m:map.entrySet()){
//            System.out.println(m.getKey()+"\t"+m.getValue());
//        }
        return map;
    }

    @Test
    public void getInfo111111(){
        String path ="C:\\Users\\xiala\\Desktop\\fund123\\hy123.txt";
        List<String> list = TxtUtil.readTxt(path);
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i <list.size() ; i++) {
            String str = list.get(i);
            if(!str.contains("医")&& !str.contains("药")){
                continue;
            }
            if(str.split(",").length>5||str.contains("医药制造=1")||str.contains("医疗行业=1")){
                continue;
            }
            System.out.println(str);
        }
    }

    @Test
    public void get1Info(){
        String path ="C:\\Users\\xiala\\Desktop\\yi123.txt";
        List<String> list = TxtUtil.readTxt(path);
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i <list.size() ; i++) {
            String str = list.get(i);
//            str = str.replaceAll(",","\t");
//            str = str.replace("(","\t");
//            str = str.replace("%","\t");
            String[] array = str.split("\t");
//            if(array[3].equals("")){
//
//            }
            String info = array[4].substring(0,6);
//            System.out.println(list.get(i));
            System.out.println(array[0]+"\t#"+array[1]+"\t"+array[2]+"\t"+info);
        }
    }

}
