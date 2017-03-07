package com.zhouzhutou.hash;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhouzhutou on 2017/3/7.
 * 自定义HashTable类（分离链接法）
 */
public class HashTable<T> {

    /**
     * 定义散列表
     */
    private List<T> lists[];

    /**
     * 当前的散列表大小
     */
    private int currentSize;

    /**
     * 默认散列表大小
     */
    //private static final int DEFAULT_TABLE_SIZE=101;
    private static final int DEFAULT_TABLE_SIZE=7;

    /**
     * 返回指定整数后面的第一个素数
     * @param n
     * @return int
     */
    private int nextPrime(int n)
    {
        if(n%2==0)
            n++;
        boolean notPrime=false;
        for(;;n+=2)
        {
            notPrime=false;
            for(int i=3;i*i<=n;i+=2)
            {
                if(n%i==0){
                    notPrime=true;
                    break;
                }
            }
            if(!notPrime)
                return n;
        }
    }

    /**
     * 构造函数
     */
    public HashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * 构造函数
     * @param size
     */
    public HashTable(int size){
        lists=new LinkedList[nextPrime(size)];
        for(int i=0;i<lists.length;i++){
            lists[i]=new LinkedList<>();
        }
    }

    /**
     * 自定义hash函数
     * @param x
     * @return int
     */
    private int hash(T x)
    {
        int hashVal=x.hashCode();
        hashVal%=lists.length;
        if(hashVal<0)
            hashVal+=lists.length;
        return hashVal;
    }

    /**
     *  判断哈希表中是否包含指定的值
     * @param x
     * @return boolean
     */
    public boolean contains(T x)
    {
        int idx=hash(x);
        List<T> list=lists[idx];
        if(list.contains(x))
            return true;
        else
            return false;
    }

    /**
     * 像哈希表中插入元素
     * @param x
     */
    public void insert(T x)
    {
        int idx=hash(x);
        List<T> list=lists[idx];
        if(!list.contains(x))
        {
            list.add(x);
            if(++currentSize>lists.length)
                reHash();
        }
    }

    /**
     * 对哈希表进行再散列
     */
    private void reHash()
    {
        List<T>[] oldLists=lists;
        lists=new LinkedList[nextPrime(2*lists.length)];
        for(int i=0;i<lists.length;i++)
            lists[i]=new LinkedList<>();
        currentSize=0;
        for(int i=0;i<oldLists.length;i++){
            List<T> list=oldLists[i];
            for(T x:list)
                insert(x);
        }
    }

    /**
     * 从哈希表中删除指定元素
     * @param x
     */
    public void remove(T x)
    {
        int idx=hash(x);
        List<T> list=lists[idx];
        if(list.contains(x))
        {
            list.remove(x);
            currentSize--;
        }
    }

    /**
     * 清空哈希表
     */
    public void makeEmpty()
    {
        for(int i=0;i<lists.length;i++)
            lists[i]=null;
        currentSize=0;
    }

    /**
     * 返回lists
     * @return List<T>[]
     */
    public List<T>[] getLists()
    {
        return lists;
    }
}
