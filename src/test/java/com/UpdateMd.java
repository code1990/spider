package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: spider
 * @Date: 2020-01-04 11:14
 * @Author: code1990
 * @Description:
 */
public class UpdateMd {
    public static void main(String[] args) {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/mindoc_db";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from md_documents";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("姓名" + "\t" + "职称");
            System.out.println("-----------------");

            String job = null;
            String id = null;
            List<String> list = new ArrayList<>();
            while(rs.next()){
                //获取stuname这列数据
                job = rs.getString("identify");
                //获取stuid这列数据
                id = rs.getString("document_id");
//                if(job.contains("-")){
//
//                    list.add(id + "\t" + job);
//                }
                System.out.println(id + "\t" + job);
            }
            rs.close();
            for (int i = 0; i <list.size() ; i++) {
                job = list.get(i).split("\t")[1];
                id = list.get(i).split("\t")[0];
                String sql2 ="update md_documents set identify='"+job.replaceAll("-","_")+"' where " +
                        "document_id='"+id+"'";
                statement.execute(sql2);
                //输出结果
                System.out.println(id + "\t" + job);
            }
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

}
