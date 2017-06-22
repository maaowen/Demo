package com.lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数数组，在该数组中，寻找三个数，分别代表三角形三条边的长度，问，可以寻找到多少组这样的三个数来组成三角形？
 *
 * @author maaowen
 * @date 2017/6/10
 */
public class CountTriangle {

    @Test
    public void demo(){
        int[] array = {3,4,6,7};
        System.out.println(new Demo1().triangleCount(array));
    }

    @Test
    public void demo2(){
        int[] array = {3,4,6,7};
        System.out.println(new Demo2().triangleCount(array));
    }

    static class Demo2{
        public int triangleCount(int input[]){
            int count = 0;
            for(int i = 0 ; i<input.length-2 ; i++){
                for(int j = i+1; j<input.length-1 ;j++){
                    for(int k = j+1; k<input.length ; k++){
                        if(input[i]+input[j]>input[k] && Math.abs(input[i]-input[j])<input[k])
                            count++;
                    }
                }
            }
            return count;
        }
    }


    static class Demo1{
        public int triangleCount(int input[]) {
            int count = 0;
            List<int[]> array = countAllGroup(input);
            for(int[] ele:array){
                if(check(ele))
                    count++;
            }
            return count;
        }

        /**
         * 判断一个组合是否符合三角形
         * @param ele
         * @return
         */
        private boolean check(int[] ele) {
            if(ele.length!=3)
                return false;
            if (ele[0]+ele[1]>ele[2] && Math.abs(ele[0]-ele[1])<ele[2]){
                return true;
            }
            return false;
        }

        /**
         * 获取全部三角形三边组合
         * @param input
         * @return
         */
        private List<int[]> countAllGroup(int[] input) {
            List<int[]> groups = new ArrayList();
            for(int i = 0 ; i<input.length-2 ; i++){
                for(int j = i+1; j<input.length-1 ;j++){
                    for(int k = j+1; k<input.length ; k++){
                        int[] ele = {input[i],input[j],input[k]};
                        groups.add(ele);
                    }
                }
            }
            return groups;
        }
    }

}
