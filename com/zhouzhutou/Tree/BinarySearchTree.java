package com.zhouzhutou.Tree;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouzhutou on 2017/2/27.
 * 自定义BinarySearchTree(二叉查找树)
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    /**
     * 定义二叉查找树的节点
     * @param <T>
     */
    private static class BinaryNode<T>{
        /**
         * 定义左孩子
         */
        BinaryNode<T> left;
        /**
         * 定义右孩子
         */
        BinaryNode<T> right;
        /**
         * 定义节点存储的数据
         */
        T data;

        /**
         * BinaryNode构造函数
         * @param d
         * @param l
         * @param r
         */
        BinaryNode(T d, BinaryNode<T> l, BinaryNode<T> r)
        {
            data=d;
            left=l;
            right=r;
        }

        /**
         * BinaryNode构造函数
         * @param data
         */
        BinaryNode(T data)
        {
            this(data,null,null);
        }
    }

    /**
     * 定义根节点
     */
    private BinaryNode<T> root;

    /**
     * 定义存储遍历二叉查找树结果的列表
     */
    private List<T> traverse;

    /**
     * BinarySearchTree构造函数
     */
    public BinarySearchTree()
    {
        root=null;
        traverse=new ArrayList<>();
    }

    /**
     * 清空二叉查找树
     */
    public void clear()
    {
        root=null;
    }

    /**
     * 判断二叉查找树是否为空
     * @return boolean
     */
    public boolean isEmpty()
    {
        return root==null;
    }

    /**
     * 返回根节点
     * @return BinaryNode<T>
     */
    public BinaryNode<T> getRoot()
    {
        return root;
    }

    /**
     * 返回定义存储遍历二叉查找树结果的列表
     * @return
     */
    public List<T> getTraverse()
    {
        return traverse;
    }

    /**
     * 插入节点
     * @param value
     */
    public void insert(T value)
    {
        root=insert(value,root);
    }

    /**
     * 插入节点的递归实现
     * @param value
     * @param t
     * @return BinaryNode<T>
     */
    private BinaryNode<T> insert(T value,BinaryNode<T> t)
    {
        if(t==null)
            return new BinaryNode<T>(value);
        int com=value.compareTo(t.data);
        if(com<0)
            t.left=insert(value,t.left);
        else if(com>0)
            t.right=insert(value,t.right);
        else
            throw new RuntimeException("insert the same element");
        return t;
    }

    /**
     * 检验二叉查找树中是否包含某个元素
     * @param x
     * @return boolean
     */
    public boolean contains(T x)
    {
        return contains(x,root);
    }

    /**
     * 检验二叉查找树中是否包含某个元素的递归实现
     * @param x
     * @param t
     * @return boolean
     */
    private boolean contains(T x, BinaryNode<T> t)
    {
        if(t==null) return false;
        int com=x.compareTo(t.data);
        if(com==0)
            return true;
        if(com<0){
            return contains(x,t.left);
        }else{
            return contains(x,t.right);
        }
    }

    /**
     * 返回二叉查找树中的最小元素
     * @return T
     */
    public T findMin()
    {
        if(isEmpty())
            throw new RuntimeException("the tree is empty");
        return findMin(getRoot()).data;
    }

    /**
     * 查找二叉树中具有最小元素节点的实现
     * @param t
     * @return BinaryNode<T>
     */
    private BinaryNode<T> findMin(BinaryNode<T> t)
    {
        if(t==null)
            return null;
        else if(t.left!=null)
            return findMin(t.left);
        else
            return t;
    }

    /**
     * 返回二叉查找树中的最大元素
     * @return T
     */
    public T findMax(){
        if(isEmpty())
            throw new RuntimeException("the tree is empty");
        return findMax(getRoot()).data;
    }

    /**
     * 查找二叉树中具有最大元素节点的实现
     * @param t
     * @return BinaryNode<T>
     */
    private BinaryNode<T> findMax(BinaryNode<T> t)
    {
        if(t==null)
            return null;
        else if(t.right!=null)
            return findMax(t.right);
        else
            return t;
    }

    /**
     * 先序遍历二叉查找树
     * @param
     */
    public void preOrder(BinaryNode<T> t)
    {
        if(t==null) return;
        traverse.add(t.data);
        if(t.left!=null) preOrder(t.left);
        if(t.right!=null) preOrder(t.right);
    }
}
