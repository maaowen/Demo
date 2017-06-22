package com.lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数组重新排序以构造最小值
 * 给定 [3, 32, 321]，通过将数组重新排序，可构造 6 个可能性数字
 * 最小值为 321323，所以，将数组重新排序后，该数组变为 [321, 32, 3]
 *
 * @author maaowen
 * @date 2017/6/12
 */
public class RecombinArray {

    private static List<List<Integer>> paths = new ArrayList<>();
    /**
     * 拼接数字组合: 可以有负数？有且只能有一个？
     */
    @Test
    public void demo1(){
        Integer result = null;
        List<Integer> data =init(321,32,1);
        for(int i = 0 ; i<data.size() ; i++){
            List<Integer> path = getCopy(new ArrayList<>(),true,data.get(i));
            List<Integer> remain = getCopy(data,false,i);
            getPath(paths,path,remain);
        }
        for(List<Integer> ele:paths){
            StringBuilder sb = new StringBuilder();
            for(Integer i : ele){
                sb.append(i);
            }
            try {
                int tmp = Integer.parseInt(sb.toString());
                result = (result==null||tmp<result) ? tmp :result;
            }catch (Exception e){
                continue;
            }
        }
        System.out.println(result);
    }

    /**
     * 穷举一个数组
     * 缺点:占用内存对象复制
     */
    @Test
    public void demo() {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> data = init(1,2,3,4);
        for(int i = 0 ; i<data.size() ; i++){
            List<Integer> path = getCopy(new ArrayList<>(),true,data.get(i));
            List<Integer> remain = getCopy(data,false,i);
            getPath(paths,path,remain);
        }
        for(List<Integer> ele:paths){
            System.out.println(ele);
        }
    }

    private void getPath(List<List<Integer>> array,List<Integer> path, List<Integer> remain) {
        if(remain.isEmpty())
            array.add(path);
        for(int i = 0 ; i<remain.size() ; i++){
            List<Integer> pathCopy = getCopy(path,true,remain.get(i));
            List<Integer> remainCopy = getCopy(remain,false,i);
            getPath(array,pathCopy,remainCopy);
        }
    }

    /**
     * 复制对象
     * @param input 需要复制的对象
     * @param flag true 添加元素/false 删除元素
     * @param i   元素 or 数组下标
     * @return
     */
    private List<Integer> getCopy(List<Integer> input,boolean flag,int i){
        List list = new ArrayList();
        list.addAll(input);
        if (flag){
            list.add(i);
        }else{
            list.remove(i);
        }
        return list;
    }

    /**
     * 初始化
     */
    private List<Integer> init(int... params) {
        List<Integer> data = new ArrayList<>();
        for(int ele : params){
            data.add(ele);
        }
        return data;
    }
}
