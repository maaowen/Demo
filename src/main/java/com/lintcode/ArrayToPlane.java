package com.lintcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个列表，该列表中的每个要素要么是个列表，要么是整数。将其变成一个只包含整数的简单列表。
 * 给定 [1,2,[1,2]]，返回 [1,2,1,2]。
 * 给定 [4,[3,[2,[1]]]]，返回 [4,3,2,1]。
 *
 * @author maaowen
 * @date 2017/6/12
 */
public class ArrayToPlane {

    /**
     * 结构体
     */
    class NestedInteger {

        private Integer i ;
        private List<NestedInteger> list;

        public NestedInteger(Integer i) {
            this.i = i;
        }

        public NestedInteger(List<NestedInteger> list) {
            this.list = list;
        }

        public boolean isInteger(){
            return i != null ? true : false;
        }

        public Integer getInteger(){
            return i;
        }

        public List<NestedInteger> getList(){
            return list;
        }
    }

    @Test
    public void demo(){
        List<NestedInteger> list = init();
        System.out.println(flatten(list));
    }

    public List<NestedInteger> init(){
        List<NestedInteger> list = new ArrayList<>();
        NestedInteger level11 = new NestedInteger(1);
        NestedInteger level12 = new NestedInteger(2);
        list.add(level11);
        list.add(level12);
        List level2 = new ArrayList();
        level2.addAll(list);
        NestedInteger level21 = new NestedInteger(level2);
        list.add(level21);
        return list;
    }

    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> list = new ArrayList<>();
        for(NestedInteger element:nestedList){
            if(element.isInteger()){
                list.add(element.getInteger());
            }else{
                list.addAll(flatten(element.getList()));
            }
        }
        return list;
    }
}
