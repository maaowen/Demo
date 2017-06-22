package com.lintcode.test;

import org.junit.Test;

/**
 * RecombinArray 测试不用工具类
 *
 * @author maaowen
 * @date 2017/6/15
 */
public class RecombinArrayTest {

    @Test
    public void demo(){
       int[] data = {321,32,1};
       addPath(data,data);
    }

    private void addPath(int[] data,int[] remain){
        int[] dataCopy = data.clone();
        int[] remainCopy = remain.clone();
        for(int i = 0 ; i<remain.length ;i++){

        }
    }

    /**
     * flag true 新增，false 删除
     */
    private int[] getCopy(int[]data,boolean flag,int i){
        int[] dataCopy = null;
        if (flag){
            dataCopy = new int[data.length+1];
            for(int k = 0 ; k<data.length ; k++){
                dataCopy[k] = data[k];
            }
            dataCopy[data.length] = i;
        }else{
            dataCopy = new int[data.length-1];
            for(int k = 0 ; k<data.length ; ){
                if( k!=i){
                    dataCopy[k] = data[k];
                }
                k++;
            }
        }
        return dataCopy;
    }
}
