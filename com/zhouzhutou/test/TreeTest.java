package com.zhouzhutou.test;

import com.zhouzhutou.Tree.BinarySearchTree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhouzhutou on 2017/2/27.
 */
public class TreeTest {
    public static void main(String[] args) {
        /*BinarySearchTree测试*/
        System.out.println("BinarySearchTree测试");
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.insert(9);
        binarySearchTree.insert(10);
        binarySearchTree.insert(2);
        binarySearchTree.insert(6);
        binarySearchTree.insert(8);
        binarySearchTree.insert(7);
        binarySearchTree.insert(5);
        System.out.println(binarySearchTree.contains(11));
        binarySearchTree.preOrder(binarySearchTree.getRoot());
        List<Integer> preOrderList=new LinkedList<>();
        preOrderList=binarySearchTree.getTraverse();
        System.out.println(preOrderList.size());
        for(Integer i:preOrderList)
            System.out.print(i+" ");
        System.out.println();
        System.out.println("min element: "+binarySearchTree.findMin());
        System.out.println("max element: "+binarySearchTree.findMax());
        binarySearchTree.clearTraverse();
        binarySearchTree.remove(6);
        binarySearchTree.preOrder(binarySearchTree.getRoot());
        preOrderList=binarySearchTree.getTraverse();
        System.out.println(preOrderList.size());
        for(Integer i:preOrderList)
            System.out.print(i+" ");
        System.out.println();
        binarySearchTree.clearTraverse();
        binarySearchTree.remove(5);
        binarySearchTree.preOrder(binarySearchTree.getRoot());
        preOrderList=binarySearchTree.getTraverse();
        System.out.println(preOrderList.size());
        for(Integer i:preOrderList)
            System.out.print(i+" ");
        System.out.println();

    }
}
