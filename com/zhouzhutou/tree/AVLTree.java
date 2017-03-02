package com.zhouzhutou.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhouzhutou on 2017/2/28.
 * 自定义AVLTree(平衡二叉树)
 */
public class AVLTree<T extends Comparable<? super T>> {

    /**
     * 定义平衡二叉树的节点
     * @param <T>
     */
    private static class AvlNode<T>{
        /**
         * 定义节点存储的数据
         */
        T data;
        /**
         * 定义左孩子
         */
        AvlNode<T> left;
        /**
         * 定义右孩子
         */
        AvlNode<T> right;
        /**
         * 定义节点的高度
         */
        int height;

        /**
         * 定义构造函数
         * @param value
         */
        AvlNode(T value)
        {
            this(value,null,null);
        }

        /**
         * 定义构造函数
         * @param d
         * @param l
         * @param r
         */
        AvlNode(T d,AvlNode<T> l,AvlNode<T> r)
        {
            data=d;
            left=l;
            right=r;
            height=0;
        }
    }

    /**
     * 定义节点左右子树最大相差高度
     */
    private static final int ALLOW_DIFF=1;
    /**
     * 定义平衡二叉树根节点
     */
    private AvlNode<T> root;

    /**
     * 定义比较器
     */
    private Comparator<? super T> comparator;

    /**
     * 定义存储遍历二叉查找树结果的列表
     */
    private List<T> traverse;

    /**
     * 定义树的高度
     */
    private int height;


    /**
     * AVLTree构造函数
     */
    public AVLTree()
    {
        this(null);
    }

    /**
     * AVLTree构造函数
     * @param com
     */
    public AVLTree(Comparator<? super T> com)
    {
        root=null;
        comparator=com;
        traverse=new ArrayList<>();
    }

    /**
     * 返回树的高度
     * @return int
     */
    public int getHeight()
    {
        return root.height;
    }

    /**
     * 返回traverse列表
     * @return List<T>
     */
    public List<T> getTraverse()
    {
        return traverse;
    }

    /**
     * 清空traverse列表
     */
    public void clearTraverse()
    {
        traverse.clear();
    }

    /**
     * 计算树的高度的递归实现
     * @param t
     * @return int
     */
    public int getHeight(AvlNode<T> t)
    {
        if(t==null)
            return -1;
        return 1+Math.max(getHeight(t.left),getHeight(t.right));
    }

    /**
     * 获取平衡二叉树中指定节点的高度
     * @param t
     * @return int
     */
    public int height(AvlNode<T> t)
    {
        return t==null ? -1 : t.height;
    }

    /**
     * 比较两个节点的元素大小
     * @param x
     * @param y
     * @return int
     */
    public int compare(T x, T y)
    {
        if(comparator==null)
            return x.compareTo(y);
        else
            return comparator.compare(x,y);
    }
    /**
     * 判断平衡二叉树是否为空
     * @return boolean
     */
    public boolean isEmpty()
    {
        return root==null;
    }

    /**
     * 清空平衡二叉树
     */
    public void clear()
    {
        root=null;
    }

    /**
     * 获取平衡二叉树的根节点
     */
    public AvlNode<T> getRoot()
    {
        return root;
    }

    /**
     * 向平衡二叉树插入新节点
     * @param value
     */
    public void insert(T value)
    {
        root=insert(value,root);
    }

    /**
     * 返回平衡二叉树中的最小元素
     * @return T
     */
    public T findMin()
    {
        return findMin(root).data;
    }

    /**
     * 返回包含最小元素的节点
     * @param t
     * @return AvlNode<T>
     */
    public AvlNode<T> findMin(AvlNode<T> t)
    {
        if(t==null)
            return t;
        while (t.left!=null)
            t=t.left;
        return t;
    }

    /**
     * 返回平衡二叉树中的最大元素
     * @return T
     */
    public T findMax()
    {
        return findMax(root).data;
    }

    /**
     * 返回包含最大元素的节点
     * @param t
     * @return AvlNode<T>
     */
    public AvlNode<T> findMax(AvlNode<T> t)
    {
        if(t==null)
            return t;
        while(t.right!=null)
            t=t.right;
        return t;
    }

    /**
     * 向平衡二叉树插入新节点
     * @param value
     * @param t
     * @return AvlNode<T>
     */
    public AvlNode<T> insert(T value,AvlNode<T> t)
    {
        if(t==null)
            return new AvlNode<T>(value);
        int com=compare(value,t.data);
        if(com<0)
            t.left=insert(value,t.left);
        else if(com>0)
            t.right=insert(value,t.right);
        else
            throw new RuntimeException("insert the same element");
        return balance(t);
    }

    /**
     * 插入节点导致二叉树不平衡，调节使它平衡
     * @param t
     * @return AvlNode<T>
     */
    private AvlNode<T> balance(AvlNode<T> t)
    {
        if(t==null)
            return t;
        if(height(t.left)-height(t.right)>ALLOW_DIFF)
        {
            if(height(t.left.left)>=height(t.left.right))
                t=rotateWithLeftChild(t);
            else
                t=doubleRotateWithLeftChild(t);
        }else if(height(t.right)-height(t.left)>ALLOW_DIFF)
        {
            if(height(t.right.right)>=height(t.right.left))
                t=rotateWithRightChild(t);
            else
                t=doubleRotateWithRightChild(t);
        }
        t.height=1+Math.max(height(t.left),height(t.right));//边平衡节点边计算节点的高度
        return t;
    }

    /**
     * 左儿子单旋转
     * @param t
     * @return AvlNode<T>
     */
    public AvlNode<T> rotateWithLeftChild(AvlNode<T> t)
    {
        AvlNode<T> p=t.left;
        t.left=p.right;
        p.right=t;
        t.height=Math.max(height(t.left),height(t.right))+1;
        p.height=Math.max(height(p.left),t.height)+1;
        return p;
    }

    /**
     * 右儿子单旋转
     * @param t
     * @return AvlNode<T>
     */
    public AvlNode<T> rotateWithRightChild(AvlNode<T> t)
    {
        AvlNode<T> p=t.right;
        t.right=p.left;
        p.left=t;
        t.height=Math.max(height(t.left),height(t.right))+1;
        p.height=Math.max(t.height,height(p.right))+1;
        return p;
    }

    /**
     * 左二子双旋转
     * @param t
     * @return AvlNode<T>
     */
    public AvlNode<T> doubleRotateWithLeftChild(AvlNode<T> t)
    {
        AvlNode<T> l=t.left;
        t.left=rotateWithRightChild(l);
        return rotateWithLeftChild(t);
    }

    /**
     * 右二子双旋转
     * @param t
     * @return AvlNode<T>
     */
    public AvlNode<T> doubleRotateWithRightChild(AvlNode<T> t)
    {
        AvlNode<T> r=t.right;
        t.right=rotateWithLeftChild(r);
        return rotateWithRightChild(t);
    }

    /**
     * 删除指定元素的节点
     * @param value
     */
    public void remove(T value)
    {
        root=remove(value,root);
    }

    /**
     * 删除包含指定元素的节点
     * @param value
     * @param t
     * @return AvlNode<T>
     */
    public AvlNode<T> remove(T value,AvlNode<T> t)
    {
        if(t==null)
            return t;
        int com=compare(value,t.data);
        if(com<0)
            t.left=remove(value,t.left);
        else if(com>0)
            t.right=remove(value,t.right);
        else if(t.left!=null && t.right!=null)
        {
            t.data=findMin(t.right).data;
            t.right=remove(t.data,t.right);
        }else{
            t=t.left!=null ? t.left:t.right;
        }
        return balance(t);
    }

    /**
     * 查看是否包含某个元素的节点
     * @param value
     * @return boolean
     */
    public boolean contains(T value)
    {
        return contains(value,root);
    }

    /**
     * 查看是否包含某个元素的节点的递归实现
     * @param x
     * @param t
     * @return boolean
     */
    public boolean contains(T x, AvlNode<T> t)
    {
        if(t==null)
            return false;
        int com=compare(x,t.data);
        if(com<0)
            return contains(x,t.left);
        else if(com>0)
            return contains(x,t.right);
        else
            return true;
    }

    /**
     * 先序遍历平衡二叉树
     * @param t
     */
    public void preOrder(AvlNode<T> t)
    {
        if(t==null)
            return;
        traverse.add(t.data);
        if(t.left!=null)
            preOrder(t.left);
        if(t.right!=null)
            preOrder(t.right);
    }
}
