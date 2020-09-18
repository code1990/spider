package com;

import org.junit.Test;

import java.io.File;
import java.util.List;

public class RcpTest {

    @Test
    public void getInfo(){
        String path ="D:\\workspaceE1\\client-new\\com.csair.soc.disp.monitor.ui\\src\\com\\csair\\soc\\disp\\monitor\\newui";
        for(File file:new File(path).listFiles()){
//            System.out.println(file.getName());
            for(File file2:file.listFiles()){
                System.out.println(file.getName()+"\t"+file2.getName());
            }
        }
    }

    @Test
    public void getInfo1(){
        String path ="D:\\workspaceE1\\client-new\\com.csair.soc.disp.monitor.ui\\src\\com\\csair\\soc\\disp\\monitor\\ui";
        for(File file:new File(path).listFiles()){
//            System.out.println(file.getName());
            for(File file2:file.listFiles()){
                System.out.println(file.getName()+"\t"+file2.getName());
            }
        }
    }

    @Test
    public void createDir(){
        String path ="D:\\github\\xmind\\code\\xmind\\src\\main\\java\\";
        String kw="model";
        int sum = 29;
        for (int i = 1; i <=sum ; i++) {
            String chapter = "chapter"+i;
            if(i<10){
                chapter="chapter0"+i;
            }
            String dir =path+kw+"\\"+chapter;
            new File(dir).mkdirs();
            System.out.println(dir);
        }

    }

    @Test
    public void getInfo121(){
        String path = "C:\\Users\\xiala\\Desktop\\test.md";
        List<String> list = TxtUtil.readTxt(path);
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
//            System.out.println(str);
            if(str.startsWith("### ")){
//                System.out.println(str);
                for (int j = 1; j <=12 ; j++) {
                    if(str.startsWith("### "+j+".")){
                        for (int k = 1; k <=10 ; k++) {
                            System.out.println("#### "+j+"."+k+".1界面布局\n");
                            System.out.println("#### "+j+"."+k+".2界面元素\n");
                            System.out.println("#### "+j+"."+k+".3界面事件\n");
                        }

                        break;
                    }
                }
            }
//            if(i+1<list.size()){
//                String tmp = list.get(i+1);
//                if(str.startsWith("## ")&&tmp.startsWith(" ")){
//                    System.out.println(str);
//                    System.out.println("#### ");
//                }else{
//                    System.out.println(str);
//                }
//            }

        }
    }

    @Test
    public void getInfo123(){
        String path ="C:\\Users\\xiala\\Desktop\\new 11.txt";
        List<String> list = TxtUtil.readTxt(path);
        for (int i = 0; i <list.size() ; ) {
            String str = list.get(i);
            System.out.println("VolcanoData data"+i/4+" = new VolcanoData();");
            System.out.println("data"+i/4+".setVaac(\""+list.get(i)+"\");");
            System.out.println("data"+i/4+".setUpdTime(\""+list.get(i+1)+"\");");
            System.out.println("data"+i/4+".setVolcano(\""+list.get(i+2)+"\");");
            System.out.println("data"+i/4+".setFvContent(\""+list.get(i+3)+"\");");
            System.out.println("volcanoData.add(data"+i/4+");");
            i+=4;
        }

    }
}
