package com;

import com.util.MapUtil;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyWordTest {

    @Test
    public void getInfo() {
        String path = "C:\\Users\\xiala\\Desktop\\ebook\\douban\\并发";
        Map<String, Integer> map = new HashMap<>();
        for (File file : new File(path).listFiles()) {
            List<String> list = TxtUtil.readTxt(file.getAbsolutePath());
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                List<Word> words = WordSegmenter.seg(str);
                for (int j = 0; j < words.size(); j++) {
                    String word = words.get(j).getText();
                    if (map.get(word) == null) {
                        map.put(word, 1);
                    } else {
                        map.put(word, 1 + map.get(word));
                    }
                }
            }
        }
        Map<String, Integer> map2 = MapUtil.sortAsc(map);
        for (Map.Entry<String, Integer> m : map2.entrySet()) {
            System.out.println(m.getKey() + "\t\t" + m.getValue());
        }
    }

    @Test
    public void getInfo11111() {
        String path = "C:\\Users\\xiala\\Desktop\\C#2.txt";
        List<String> list = TxtUtil.readTxt(path);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
//            System.out.println(str.split(" ").length);
            if (str.contains(" ")) {
                String[] array = str.split(" ");
                if (array.length == 3) {
                    System.out.println(array[0] + array[1]);
                }
            } else {
                if (str.split("\\.").length > 2 || str.contains("小结" )|| str.contains("视频讲解")) {
                    continue;
                }else {
                    System.out.println(str);
                }
            }

//            System.out.println();
        }
    }

    @Test
    public void getInfo111() {
        String path = "C:\\Users\\xiala\\Desktop\\1.txt";
        for (String str : TxtUtil.readTxt(path)) {
            System.out.println(str.split("——")[1]);
        }
    }

    @Test
    public void getInfo1111() {
        String path = "C:\\Users\\xiala\\Desktop\\fund123\\FOF.txt";
        String path2 = "C:\\Users\\xiala\\Desktop\\fund123\\fund_pool.txt";
        List<String> list = TxtUtil.readTxt(path);
        List<String> list2 = TxtUtil.readTxt(path2);
        Map<String, String> map = new HashMap<>();
//        Map<String,String> map2 = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String[] array = list.get(i).split("\t");
            map.put(array[1], array[2]);
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < list2.size(); j++) {
            String[] array = list2.get(j).split("\t");
            if (map.get(array[1]) == null) {
                System.out.println(list2.get(j));
                sb.append(list2.get(j) + "\n");
            }
        }
        TxtUtil.writeTxt(path2, sb.toString());
    }

    @Test
    public void getInfo123() {
        String path = "C:\\Users\\xiala\\Desktop\\fund_pool.txt";
        List<String> list = TxtUtil.readTxt(path);
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            String[] array = list.get(i).split("\t");
            List<Word> list2 = WordSegmenter.seg(array[2]);
            StringBuilder sb = new StringBuilder(array[2] + "|");
            for (Word word : list2) {
                sb.append(word.getText() + "\t");
            }
//            System.out.println();
            map.put(array[1], sb.toString());
        }
        for (Map.Entry<String, String> m : map.entrySet()) {
            System.out.println(m.getKey() + "\t" + m.getValue());
        }
    }
}
