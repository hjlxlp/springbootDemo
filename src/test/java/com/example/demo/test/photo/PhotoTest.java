package com.example.demo.test.photo;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author huangjiale
 * @date 2022/3/25 15:40
 */
public class PhotoTest {

    public static Integer num = 1;

    @Test
    public void test01() throws Exception {
        File file = new File("D:\\BaiduNetdiskDownload\\file");
        //removeFile(file);
    }

    public static void removeFile(File file) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                removeFile(f);
            }
        } else {
            File newDir = new File("D:\\BaiduNetdiskDownload\\all" + "/" + file.getName());
            if (newDir.exists()) {
                newDir = new File("D:\\BaiduNetdiskDownload\\all" + "/" + file.getName() + num);
                num++;
            }
            Files.copy(file.toPath(), newDir.toPath());
        }
    }

    @Test
    public void test02() throws Exception {
        File file = new File("D:\\BaiduNetdiskDownload\\all");
        File[] files = file.listFiles();
        Map<String, Long> map = new HashMap<>();
        for (int i = 0; i < files.length; i++) {
            map.put(files[i].getName(), files[i].length());
        }

        File file2 = new File("D:\\Photo\\blhx");
        File[] files2 = file2.listFiles();
        Map<String, Long> map2 = new HashMap<>();
        for (int i = 0; i < files2.length; i++) {
            map2.put(files2[i].getName(), files2[i].length());
        }

        // 2是不是都在1中，在的话，大小是不是一样
        List<String> noList = new ArrayList<>();
        List<String> noSizeList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : map2.entrySet()) {
            Long size2 = map.get(entry.getKey());
            if (size2 == null) {
                //System.out.println(entry.getKey());
                noList.add(entry.getKey());
            } else if (size2 != entry.getValue()) {
                noSizeList.add(entry.getKey());
            }
        }

        System.out.println(1);

    }

    @Test
    public void test03() throws Exception {
        File file = new File("D:\\BaiduNetdiskDownload\\all");
        File[] files = file.listFiles();

        File file2 = new File("D:\\Photo\\blhx");
        File[] files2 = file2.listFiles();
        Map<String, Long> map2 = new HashMap<>();
        for (int i = 0; i < files2.length; i++) {
            map2.put(files2[i].getName(), files2[i].length());
        }

        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            // 排除hx
            if (f.getName().contains("hx.")) {
                continue;
            }
            Long size2 = map2.get(f.getName());
            // 名称不相同则继续判断
            if (size2 == null) {
                // 排查相同大小
                Boolean flag = false;
                for (int j = 0; j < files2.length; j++) {
                    File f2 = files2[j];
                    if (f2.length() == f.length()) {
                        flag = true;
                    }
                }
                if (flag) {
                    continue;
                }
                // 复制到all2
                File newDir = new File("D:\\BaiduNetdiskDownload\\all2" + "/" + f.getName());
                Files.copy(f.toPath(), newDir.toPath());
            }
            /*else if (size2 < f.length()) {
                File newDir = new File("D:\\BaiduNetdiskDownload\\all3" + "/" + f.getName());
                Files.copy(f.toPath(), newDir.toPath());
            } else if (size2 > f.length()) {
                File newDir = new File("D:\\BaiduNetdiskDownload\\all4" + "/" + f.getName());
                Files.copy(f.toPath(), newDir.toPath());
            }*/
        }

    }

    @Test
    public void test04() throws Exception {
        File file = new File("D:\\BaiduNetdiskDownload\\all2");
        File[] files = file.listFiles();

        File file2 = new File("D:\\Photo\\blhx");
        File[] files2 = file2.listFiles();

        /*Map<Long, List<File>> map2 = Arrays.stream(files2).collect(Collectors.groupingBy(a -> a.length()));
        for (Map.Entry<Long, List<File>> longListEntry : map2.entrySet()) {
            if(longListEntry.getValue().size()>1){
                System.out.println();
            }
        }*/

        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.getName().contains("hx.")) {
                f.delete();
                continue;
            }
            Boolean flag = false;
            for (int j = 0; j < files2.length; j++) {
                File f2 = files2[j];
                if (f2.length() == f.length()) {
                    /*File newDir = new File("D:\\BaiduNetdiskDownload\\all3" + "/" + f2.getName());
                    if (newDir.exists()) {
                        newDir = new File("D:\\BaiduNetdiskDownload\\all3" + "/" + num + f2.getName());
                        num++;
                    }
                    Files.copy(f2.toPath(), newDir.toPath());*/
                    flag = true;
                }
            }
            if (flag) {
                f.delete();
                /*File newDir = new File("D:\\BaiduNetdiskDownload\\all3" + "/" + f.getName());
                if (newDir.exists()) {
                    newDir = new File("D:\\BaiduNetdiskDownload\\all3" + "/" + num + f.getName());
                    num++;
                }
                Files.copy(f.toPath(), newDir.toPath());*/
            }
        }
    }


}
