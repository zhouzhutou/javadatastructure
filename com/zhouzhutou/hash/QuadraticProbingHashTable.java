package com.zhouzhutou.hash;

import java.util.HashMap;

/**
 * Created by zhouzhutou on 2017/3/7.
 * 自定义QuadraticProbingHashTable类
 */
public class QuadraticProbingHashTable<T> {

    /**
     * 自定义的HashEntry
     * @param <T>
     */
    public static class HashEntry<T>{
        /**
         * HashEntry存放的元素值
         */
        T element;
        /**
         * 元素是否删除标识符
         */
        boolean isActive;

        /**
         * 构造函数
         * @param value
         */
        public HashEntry(T value){
            this(value,true);
        }

        /**
         * 构造函数
         * @param value
         * @param active
         */
        public HashEntry(T value,boolean active)
        {
            element=value;
            isActive=active;
        }
    }

    /**
     * 默认的哈希表的大小
     */
    private static final int DEFAULT_TABLE_SIZE=11;
    /**
     * 存储HashEntry的数组
     */
    private HashEntry<T>[] array;

    /**
     * 目前存储元素的大小
     */
    private int currentSize;

    /**
     * 自定义hash函数
     * @param x
     * @return int
     */
    private int hash(T x)
    {
        int hashVal=x.hashCode();
        hashVal%=array.length;
        if(hashVal<0)
            hashVal+=array.length;
        return hashVal;
    }

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
            for(int i=3;i*i<n;i+=2)
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
    public  QuadraticProbingHashTable()
    {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * 构造函数
     * @param size
     */
    public QuadraticProbingHashTable(int size)
    {
        array=new HashEntry[nextPrime(size)];
        for(int i=0;i<array.length;i++)
            array[i]=null;
    }

    /**
     * 查看哈希表中是否包含某个元素
     * @param x
     * @return boolean
     */
    public boolean contains(T x)
    {
        int pos=findPos(x);
        return isActive(pos);
    }

    /**
     * 向哈希表中插入元素
     * @param x
     */
    public void insert(T x)
    {
        int pos=findPos(x);
        if(isActive(pos))
            return;
        HashEntry<T> entry=new HashEntry<>(x);
        array[pos]=entry;
        if(++currentSize>array.length/2)
            rehash();
    }

    /**
     * 从哈希表中删除指定值的元素
     * @param x
     */
    public void remove(T x)
    {
        int pos=findPos(x);
        if(isActive(pos)) {
            array[pos].isActive = false;
            currentSize--;
        }
    }

    /**
     * 查询指定元素值的位置
     * @param x
     * @return int
     */
    private int findPos(T x){
        int idx=hash(x);
        int offset=1;
        while (array[idx]!=null && !array[idx].element.equals(x))
        {
            idx+=offset;
            offset+=2;
            if(idx>=array.length)
                idx-=array.length;

        }
        return idx;
    }

    /**
     * 判断指定位置是否存在元素和元素的删除标志是否为真
     * @param pos
     * @return boolean
     */
    public boolean isActive(int pos)
    {
        return array[pos]!=null && array[pos].isActive==true;
    }

    /**
     * 再散列
     */
    private void rehash()
    {
        HashEntry<T>[] oldArray=array;
        array=new HashEntry[nextPrime(2*array.length)];
        currentSize=0;
        for(int i=0;i<oldArray.length;i++)
        {
            if(oldArray[i]!=null && oldArray[i].isActive)
                insert(oldArray[i].element);
        }
    }

    /**
     * 获取存储元素的个数
     * @return int
     */
    public int getSize()
    {
        return currentSize;
    }

}
