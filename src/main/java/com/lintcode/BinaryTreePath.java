package com.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，找出所有路径中各节点相加总和等于给定 目标值 的路径。
 * 一个有效的路径，指的是从根节点到叶节点的路径。
 *
 * @author maaowen
 * @date 2017/6/7
 */
public class BinaryTreePath {

    /**
     * 初始化二叉树
     * @return
     */
    public static TreeNode init(){
        TreeNode head = new TreeNode(1,new TreeNode(2),new TreeNode(4));
        TreeNode level1 =head.left;
        level1.left = new TreeNode(2);
        level1.right = new TreeNode(3);
        return head;
    }

    public  static List<List<Integer>> getPath(TreeNode root,int target) {
        List<List<Integer>> paths = new ArrayList<List<Integer>>();
        packagePath(root,new ArrayList<Integer>(),paths,target);
        return paths;
    }

    private static void packagePath(TreeNode root, List<Integer> integers, List<List<Integer>> paths,int target) {
        integers.add(root.val);
        target -= root.val;
        if (root.right!=null && target>0){
            List<Integer> tmp = new ArrayList();
            tmp.addAll(integers);
            packagePath(root.right,tmp,paths,target);
        }
        if (root.left!=null && target>0 ){
            List<Integer> tmp = new ArrayList();
            tmp.addAll(integers);
            packagePath(root.left,tmp,paths,target);
        }
        if (root.left == null && root.right == null && target>=0)
            paths.add(integers);
    }

    public static void main(String[] args){
        System.out.println(getPath(init(),5));
    }

    /**
     * 树形结构
     */
    static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

//    给定一个二叉树，和 目标值 = 5:
//              1
//             / \
//            2   4
//           / \
//          2   3
//    返回：
//
//            [
//            [1, 2, 2],
//            [1, 4]
//            ]
}
