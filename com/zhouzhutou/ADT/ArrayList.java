package com.zhouzhutou.ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zhouzhutou on 2017/2/22.
 * 自定义的ArrayList类
 */
public class ArrayList<T> implements Iterable<T>{

    /**
     * 默认的list的大小
     */
    private static final int DEFAULT_CAPACITY=10;

    /**
     * 计数器
     */
    private int size;

    /**
     * 存储元素的数组
     */
    private T[] items;


    /**
     * 构造函数
     */
    public ArrayList(){
        doClear();
    }

    /**
     * 清空list
     */
    public void clear(){
        doClear();
    }

    public void doClear(){
        size=0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * 获取list的大小
     * @return int
     */
    public int size()
    {
        return size;
    }

    /**
     * 判断list是否为空
     * @return boolean
     */
    public boolean isEmpty()
    {
        return size()==0;
    }

    /**
     * 将list的容量设置为list存储的元素数目的大小，以节省内存空间
     */
    public void trimToSize()
    {
        ensureCapacity(size());
    }

    /**
     * 返回list中指定位置的元素
     * @param index
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    public T get(int index)
    {
        if(index<0 || index>=size())
            throw new ArrayIndexOutOfBoundsException();
        return items[index];
    }

    /**
     * 设置list中指定位置上的元素值
     * @param index
     * @param value
     * @return T
     * @throws ArrayIndexOutOfBoundsException
     */
    public T set(int index, T value)
    {
        if(index<0 || index>=size())
            throw new ArrayIndexOutOfBoundsException();
        T oldValue=items[index];
        items[index]=value;
        return oldValue;
    }

    /**
     * 根据输入的参数newCapacity的大小，重新设置list的容量
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity)
    {
        if(newCapacity<size())
            return;
        T[] oldItems=items;
        items=(T[])new Object[newCapacity];
        for(int i=0;i<size();i++){
            items[i]=oldItems[i];
        }
    }

    /**
     * 从list末尾添加元素
     * @param value
     * @return boolean
     */
    public boolean add(T value)
    {
        add(size(), value);
        return true;
    }

    /**
     * 从list指定位置位置添加元素
     * @param index
     * @param value
     */
    public void add(int index, T value)
    {
        if(items.length==size())
            ensureCapacity(2*size()+1);
        for(int i=size;i>index;i--)
            items[i]=items[i-1];
        items[index]=value;
        size++;
    }

    /**
     * 从list指定位置位置删除元素
     * @param index
     * @return T
     */
    public T remove(int index)
    {
        if(index<0 || index>=size())
            throw new ArrayIndexOutOfBoundsException();
        T removedItem=items[index];
        int i=index;
        for(;i<size()-1;i++)
            items[i]=items[i+1];
        items[i]=null;
        --size;
        return removedItem;
    }

    /**
     * 返回一个自定义迭代器
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    /**
     * 定义内部类ArrayListIterator作为自定义迭代器
     */
    private class ArrayListIterator implements Iterator<T>
    {

        /**
         * 当前迭代器指向元素的当前位置
         */
        private int current=0;

        /**
         * 删除迭代器指向的当前位置的元素
         */
        @Override
        public void remove() {
            ArrayList.this.remove(--current);
        }

        /**
         * 当前迭代器指向位置的后面是否还存在元素
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return current<size;
        }

        /**
         * 返回迭代器指向位置的元素
         * @return T
         */
        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return items[current++];
        }
    }
}
