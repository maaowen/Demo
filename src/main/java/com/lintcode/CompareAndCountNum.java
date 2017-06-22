package com.lintcode;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 给定一个整数数组 （下标由 0 到 n-1，其中 n 表示数组的规模，数值范围由 0 到 10000），以及一个查询列表。
 * 对于每一个查询，将会给你一个整数，请你返回该数组中小于给定整数的元素的数量。
 * 对于数组 [1,2,7,8,5] ，查询 [1,8,5]，返回 [0,4,2]
 *
 * @author maaowen
 * @date 2017/6/11
 */
public class CompareAndCountNum {

    @Test
    public void demo() {
        int[] numbers = {1, 2, 7, 8, 5};
        int[] queries = {1, 8, 5};
        System.out.println(countOfSmallerNumber1(numbers, queries));
    }

    /**
     * 1.直接循环排序 n*d
     */
    public ArrayList<Integer> countOfSmallerNumber1(int[] A, int[] queries) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int ele : queries) {
            int location = 0;
            for (int i : A) {
                if (ele > i)
                    location++;
            }
            result.add(location);
        }
        return result;
    }

    @Test
    public void demo2(){
        int[] numbers = {1, 2, 7, 8, 5};
        int[] queries = {1, 8, 5};
        System.out.println(countOfSmallerNumber2(numbers, queries));
    }

    /**
     * 2.时间复杂度最小的排序算法，然后获取数字所在位置
     */
    public ArrayList<Integer> countOfSmallerNumber2(int[] array, int[] queries) {
        ArrayList<Integer> result = new ArrayList<>();
        array = getMinOrder(array, "冒泡");
        for(int ele :queries){
            for(int j = 0 ; j<array.length ; j++){
                if(ele == array[j])
                    result.add(j);
            }
        }
        return result;
    }

    /**
     * 排序列-排序算法
     */
    private int[] getMinOrder(int[] array, String type) {
        switch (type) {
            case "冒泡":
                for (int i = 0; i < array.length-1; i++) {
                    for (int j = i+1; j < array.length; j++) {
                        if (array[i] > array[j]) {
                            int tmp = array[i];
                            array[i] = array[j];
                            array[j] = tmp;
                        }
                    }
                }
                break;
        }
        return array;
    }
}
