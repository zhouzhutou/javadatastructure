package com.zhouzhutou.test;

import com.zhouzhutou.tree.AVLTree;
import com.zhouzhutou.tree.BinarySearchTree;

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
        /*AVLTree测试*/
        System.out.println("AVLTree测试");
        AVLTree<Integer> avlTree=new AVLTree<>();
        for(int i=1;i<6;i++)
            avlTree.insert(i);
        System.out.println("contains 11: "+avlTree.contains(11));
        System.out.println("contains 4: "+avlTree.contains(4));
        avlTree.preOrder(avlTree.getRoot());
        List<Integer> list=avlTree.getTraverse();
        for(Integer i:list)
            System.out.print(i+" ");
        avlTree.clearTraverse();
        System.out.println();
        System.out.println("min element: "+avlTree.findMin());
        System.out.println("max element: "+avlTree.findMax());
        avlTree.insert(10);
        avlTree.preOrder(avlTree.getRoot());
        list=avlTree.getTraverse();
        for(Integer i:list)
            System.out.print(i+" ");
        avlTree.clearTraverse();
        System.out.println();
        System.out.println("min element: "+avlTree.findMin());
        System.out.println("max element: "+avlTree.findMax());
        avlTree.insert(8);
        avlTree.preOrder(avlTree.getRoot());
        list=avlTree.getTraverse();
        for(Integer i:list)
            System.out.print(i+" ");
        avlTree.clearTraverse();
        System.out.println();
        System.out.println("min element: "+avlTree.findMin());
        System.out.println("max element: "+avlTree.findMax());
        avlTree.remove(2);
        avlTree.preOrder(avlTree.getRoot());
        list=avlTree.getTraverse();
        for(Integer i:list)
            System.out.print(i+" ");
        avlTree.clearTraverse();
        System.out.println();
        System.out.println("min element: "+avlTree.findMin());
        System.out.println("max element: "+avlTree.findMax());
        System.out.println("height: "+avlTree.getHeight());
    }
}
