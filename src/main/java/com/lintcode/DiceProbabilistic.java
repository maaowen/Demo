package com.lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 扔 n 个骰子，向上面的数字之和为 S。给定 Given n，请列出所有可能的 S 值及其相应的概率。
 * 给定 n = 1，返回 [ [1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]]。
 *
 * @author maaowen
 * @date 2017/6/12
 */
public class DiceProbabilistic {

    //色子一个面向上的概率
    private static double pre = 0.17;

    @Test
    public void demo(){
        System.out.println(dicesSum(1));
    }

    public List<Map<Integer, Double>> dicesSum(int n) {
        List<Map<Integer,Double>> list = new ArrayList<>();

        return list;
    }
}
