package com.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Excel操作工具类
 *
 * @author admin
 */
public class ExcelUtils {
    DecimalFormat df = new DecimalFormat("#.00");
    /*public static void main(String[] args) throws Exception {
        String srcPath = "C:\\Users\\admin\\Desktop\\bing\\keyword.xls";
        List<String> list = ExcelUtils.readXls(srcPath);
        List<String> list2 = new ArrayList<>();
        String srcPath2 = "C:\\Users\\admin\\Desktop\\bing\\keyword3.xls";
        for (int i = 0; i < list.size(); i++) {
            list2.add(list.get(i)+"\t"+"q");
        }
        System.out.println(list2.size());
        ExcelUtils.writeXls(srcPath2,list2);
    }*/

    /**
     * @param path 需要读取的文件路径
     * @return
     */
    public static List<String> readXls(String path) {
        File file = new File(path);
        List<String> list = new ArrayList<>();
        try {
            //1、获取文件输入流
            InputStream inputStream = new FileInputStream(file);
            //2、获取Excel工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            //3、得到Excel工作表对象
            HSSFSheet sheetAt = workbook.getSheetAt(0);
            //4、循环读取表格数据
            for (int j = 0; j <5 ; j++) {
                for (Row row : sheetAt) {
                    String info = row.getCell(j).getStringCellValue();
                    if(!"".equals(info.trim())){
                        list.add(info);
                    }
                }
            }
            //5、关闭流
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> readXls(String path,int rows,int cols) {
        File file = new File(path);
        List<String> list = new ArrayList<>();
        try {
            //1、获取文件输入流
            InputStream inputStream = new FileInputStream(file);
            //2、获取Excel工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            //3、得到Excel工作表对象
            HSSFSheet sheetAt = workbook.getSheetAt(0);
            //4、循环读取表格数据

            for (int j = 0; j <rows ; j++) {
                int index =1;
                for (Row row : sheetAt) {
                    String info = row.getCell(j).getStringCellValue();
                    if(!"".equals(info.trim())){
                        list.add(info);
                    }
                    System.out.println(index);
                    if(index==cols){
                        break;
                    }
                    index++;
                }
            }
            //5、关闭流
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeXls(String path,List<String> list) {
        File file = new File(path);
        //1.在内存中创建一个excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //2.创建工作簿
        HSSFSheet sheet = hssfWorkbook.createSheet();
       //3.创建标题行
        //4.遍历数据,创建数据行
        for (int i = 0; i < list.size() ; i++) {
            String[] array = list.get(i).split("\t");
            HSSFRow dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue(array[0]);
            dataRow.createCell(1).setCellValue(array[1]);
        }
        try {
            //1、获取文件输入流
            FileOutputStream outputStream = new FileOutputStream(file);
            //10.写出文件,关闭流
            hssfWorkbook.write(outputStream);
            hssfWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(file.getName()+">>>excel写入ok!");
    }

    @Test
    public void testWeekInfo(){
        String path = "F:\\github\\word\\week_data.xls";
        List<String> list = readXls(path);
        Map<String,Double> codeValue = new HashMap<>();
        Map<String,String> codeName = new HashMap<>();
        for (int i = 0; i <list.size() ; i++) {
            String[] info = list.get(i).split(" ");
            String fundCode = info[0];
            String fundName = info[1];
            String fundValue = info[2].replace("%","");
//            System.out.println(info[0]+"\t"+info[1]+"\t"+info[2]);
            if(null==codeName.get(fundCode)){
                codeName.put(fundCode,fundName);
            }
//            codeValue.put(fundCode,new Double(0.0));
            if(null==codeValue.get(fundCode)){
                codeValue.put(fundCode,new Double(fundValue));
            }else{
                codeValue.put(fundCode,codeValue.get(fundCode)+new Double(fundValue));
            }

        }
        List<Map.Entry<String, Double>> sortList = new ArrayList<Map.Entry<String, Double>>(codeValue.entrySet());
        //list.sort()
        sortList.sort(new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (int i = 0; i < 100; i++) {
            System.out.println(sortList.get(i).getKey() + " "+codeName.get(sortList.get(i).getKey()) + " " +
                    df.format(sortList.get(i).getValue())+"%");
        }

    }

    @Test
    public void computeValue(){
        String path = "F:\\github\\word\\week_data.xls";
        List<String> list = readXls(path,5,20);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
