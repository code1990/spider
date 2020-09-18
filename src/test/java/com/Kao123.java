package com;

import org.junit.Test;

import java.util.List;

/**
 * @author 911
 * @date 2020-07-18 11:23
 */
public class Kao123 {

    @Test
    public void getMd(){
        String path ="C:\\Users\\xiala\\Desktop\\111.txt";
        List<String> list = TxtUtil.readTxt(path);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i <list.size() ; i++) {
            String str = list.get(i);
            String[] array = str.split(" ");
            if(array.length==3){
                str = array[0]+array[1];
            }
            str = str.replaceAll(" ","");
            if(str.contains("微模考")|| str.contains("思维导图")||str.contains("答案详解")||str.contains("引言")){
                continue;
            }
            if(str.contains("章")){
                sb2.append(""+str+"\n");
                str = "### "+str;
            }else {
                if(str.contains("一")){
                    str = str.replace("一","1");
                }else if(str.contains("二")){
                    str = str.replace("二","2");
                }if(str.contains("三")){
                    str = str.replace("三","3");
                }if(str.contains("四")){
                    str = str.replace("四","4");
                }if(str.contains("五")){
                    str = str.replace("五","5");
                }if(str.contains("六")){
                    str = str.replace("六","6");
                }if(str.contains("七")){
                    str = str.replace("七","7");
                }if(str.contains("八")){
                    str = str.replace("八","8");
                }if(str.contains("九")){
                    str = str.replace("九","9");
                }if(str.contains("十")){
                    str = str.replace("十","10");
                }
                str = "#### "+str+"\n\n\n";
            }
            sb.append(str+"\n");
            System.out.println(str);
        }
        TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\123.md",sb.toString());
        System.out.println(sb2.toString());
    }

    @Test
    public void getInfo11(){
        String path ="下部 提高篇三部分 形式逻辑常见题型四章 复言命题的常见题型题型1 充分与必要题型2 并且、或者、要么题型3 箭头 德摩根题型4 “”与“”的互换题型5 箭头的串联题型6 “有的AB”及其串联题型7 假言命题的负命题题型8 二难推理题型9 复言命题的真假话问题微模考4 复言命题微模考4 参考答案五章 简单命题的常见题型题型1 对当关系题型2 替换法解简单命题的负命题题型3 隐三段论题型4 真假话问题微模考5 简单命题及概念微模考5 参考答案四部分 论证逻辑常见题型六章 削弱题题型1 论证型削弱题题型2 因果型削弱题题型3 求因果五法型削弱题 题型4 数字陷阱之百分数对比型削弱题题型5 数字陷阱之比率型削弱题题型6 数字陷阱之平均值型削弱题题型7 措施目的型削弱题题型8 调查统计型削弱题微模考6 削弱题微模考6 参考答案七章 支持题题型1 论证型支持题题型2 因果型支持题题型3 求因果五法型支持题题型4 措施目的型支持题题型5 调查统计型支持题微模考7 支持题微模考7 参考答案八章 假设题题型1 充分型假设、必要型假设与可能型假设题型2 搭桥法题型3 因果型假设题题型4 求因果五法型假设题题型5 措施目的型假设题题型6 调查统计型假设题题型7 数字型假设题微模考8 假设题微模考8 参考答案九章 解释题题型1 解释现象或矛盾题型2 解释差异题型3 数字型解释题微模考9 解释题微模考9 参考答案十章 推论题题型1 推论题解题技巧题型2 求异法型推论题题型3 类比型推论题题型4 阅读理解题题型5 概括结论题题型6 完成段落题微模考10 推论题微模考10 参考答案十一章 评论题题型1 评论逻辑漏洞题型2 评论逻辑技法题型3 争论焦点题题型4 评价题微模考11 评论题微模考11 参考答案十二章 结构相似题题型1 形式逻辑型结构相似题题型2 论证逻辑型结构相似题微模考12 结构相似题微模考12 参考答案五部分 综合推理十三章 综合推理题型1 综合推理及其方法总结题型2 排序题题型3 方位题题型4 数字推理题题型5 题组及复杂匹配题微模考13 综合推理微模考13 参考答案";
        System.out.println(path.replaceAll("题型","\n题型").replaceAll("微模考","\n微模考"));
    }

    @Test
    public void getInfo1111(){
        String path = "C:\\Users\\xiala\\Desktop\\123.txt";
        List<String> list = TxtUtil.readTxt(path);
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            System.out.println(str.split("：")[1].replace("}",""));
        }
    }
}
