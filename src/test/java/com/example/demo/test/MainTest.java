package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.BeanMapperUtil;
import com.example.demo.vo.GoodsVo;
import com.example.demo.vo.SortVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author huangjiale
 * @date 2020/4/30 11:39
 **/
public class MainTest {

    public static void main(String[] args) {
        List<GoodsVo> goodsList = new ArrayList<>();
        List<SortVo> sortList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            goodsList.add(new GoodsVo(i * 10, "name" + i));
            if (i == 1) {
                sortList.add(new SortVo(i, i * 10, 0));
            } else if (i == 10) {
                sortList.add(new SortVo(i, i * 10, i - 1));
            } else {
                sortList.add(new SortVo(i, i * 10, i - 1));
            }
        }


        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("输入分页：1,10");
            String s1 = scan.nextLine();
            Integer index = Integer.parseInt(s1.split(",")[0]);
            Integer size = Integer.parseInt(s1.split(",")[1]);
            List<SortVo> list = getGoodsVo(index, size, sortList, goodsList);
            System.out.println(JSON.toJSONString(list));

            System.out.println("输入交换下标：1,2");
            String s2 = scan.nextLine();
            Integer index1 = Integer.parseInt(s2.split(",")[0]);
            Integer index2 = Integer.parseInt(s2.split(",")[1]);
            SortVo vo1 = sortList.stream().filter(a -> a.getId() == index1).findFirst().orElse(null);
            SortVo vo2 = sortList.stream().filter(a -> a.getId() == index2).findFirst().orElse(null);
            //SortVo vo1Next = sortList.stream().filter(a -> a.getLastId() == vo1.getId()).findFirst().orElse(null);
            SortVo vo2Next = sortList.stream().filter(a -> a.getLastId() == vo2.getId()).findFirst().orElse(null);
            //SortVo vo2Next = sortList.stream().filter(a -> a.getId() == vo2.getNextId()).findFirst().orElse(null);

            SortVo tempVo1 = BeanMapperUtil.objConvert(vo1, SortVo.class);
            SortVo tempVo2 = BeanMapperUtil.objConvert(vo2, SortVo.class);

            if (vo2Next != null) {
                vo2Next.setLastId(tempVo2.getLastId());
            }
            vo1.setLastId(tempVo2.getId());
            vo2.setLastId(tempVo1.getLastId());

            /*//把vo2前后关联，去掉vo2
            vo2Last.setNextId(tempVo2.getNextId());
            if (vo2Next != null) {
                vo2Next.setLastId(tempVo2.getLastId());
            }
            //把vo1前的后关联vo2
            if (vo1Last != null) {
                vo1Last.setNextId(tempVo2.getId());
            }
            //把vo1的前关联vo2
            vo1.setLastId(tempVo2.getId());
            //把vo2前后关联加上
            vo2.setLastId(tempVo1.getLastId());
            vo2.setNextId(tempVo1.getId());*/

            System.out.println(1);
        }
    }

    /**
     * 获取分页数据
     *
     * @param index
     * @param size
     * @param sortList
     * @param goodsList
     * @return
     */
    private static List<SortVo> getGoodsVo(Integer index, Integer size, List<SortVo> sortList, List<GoodsVo> goodsList) {
        List<SortVo> list = new ArrayList<>();
        Integer begin = (index - 1) * size;
        Integer end = index * size;
        SortVo firstSort = sortList.stream().filter(a -> a.getLastId() == 0).findFirst().orElse(null);
        Integer num = 0;
        Integer thisId = firstSort.getId();
        while (true) {
            Integer finalThisId = thisId;
            SortVo thisVo = sortList.stream().filter(a -> a.getId().equals(finalThisId)).findFirst().orElse(null);
            if (num >= begin && num <= end) {
                //GoodsVo vo = goodsList.stream().filter(a -> a.getId().equals(thisVo.getGoodsId())).findFirst().orElse(null);
                //thisVo.setGoodsVo(vo);
                list.add(thisVo);
            }
            num = num + 1;
            SortVo next = sortList.stream().filter(a -> a.getLastId().equals(finalThisId)).findFirst().orElse(null);
            if (next != null) {
                thisId = next.getId();
                if (num == end) {
                    break;
                }
            } else {
                break;
            }
        }
        return list;
    }


}
