package com.zhouzhutou.ADT;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zhouzhutou on 2017/2/23.
 * 自定义LinkedList类
 */
public class LinkedList<T> implements Iterable<T>{
    /**
     * 定义嵌套内部类节点Node
     * @param <T>
     */
    private static class Node<T>{
        Node<T> prev;
        Node<T> next;
        T data;
        Node(T d, Node<T> p, Node<T> n){
                data=d;
                prev=p;
                next=n;
        }
    }

    /**
     * 定义头节点
     */
    private Node<T> head;

    /**
     * 定义尾节点
     */
    private Node<T> tail;

    /**
     * 链表存储元素的数目
     */
    private int size;

    /**
     * 自从构造以来对链表的所做改变的次数
     */
    private int modCount;

    /**
     * 构造函数
     */
    public LinkedList()
    {
        doClear();
    }

    /**
     * 清空链表
     */
    public void clear()
    {
        doClear();
    }

    /**
     * 具体的链表清空操作的实现
     */
    public void doClear()
    {
        head=new Node<>(null,null,null);
        tail=new Node<>(null,head,null);
        head.next=tail;
        size=0;
        modCount++;
    }

    /**
     * 获取链表存储元素的数目
     * @return int
     */
    public int size()
    {
        return size;
    }

    /**
     * 判断链表是否为空
     * @return boolean
     */
    public boolean isEmpty()
    {
        return size()==0;
    }

    /**
     * 从链表末尾添加元素
     * @param value
     * @return boolean
     */
    public boolean add(T value)
    {
        add(size(),value);
        return true;
    }

    /**
     * 从链表指定位置添加元素
     * @param index
     * @param value
     */
    public void add(int index, T value)
    {
        addBefore(getNode(size(),0,size()),value);
    }

    /**
     * 在指定节点处插入值为value的新节点
     * @param p
     * @param value
     */
    private void addBefore(Node<T> p, T value)
    {
        Node<T> newNode=new Node<>(value,p.prev,p);
        p.prev.next=newNode;
        p.prev=newNode;
        size++;
        modCount++;
    }

    /**
     * 从链表指点位置获取节点的值
     * @param index
     * @return T
     */
    public T get(int index)
    {
        return getNode(index).data;
    }

    /**
     * 删除链表指定位置上的节点
     * @param index
     * @return T
     */
    public T remove(int index)
    {
       return remove(getNode(index));
    }

    /**
     * 删除链表上指定的节点
     * @param p
     * @return T
     */
    private T remove(Node<T> p)
    {
        T value=p.data;
        p.prev.next=p.next;
        p.next.prev=p.prev;
        p=null;
        size--;
        modCount++;
        return value;
    }

    /**
     * 为指定位置的节点设置新值
     * @param index
     * @param value
     * @return
     */
    public T set(int index, T value)
    {
        Node<T> p=getNode(index);
        T oldValue=p.data;
        p.data=value;
        return oldValue;
    }

    /**
     * 获取指定位置的节点
     * @param index
     * @return Node<T>
     */
    private Node<T> getNode(int index)
    {
        return getNode(index,0,size()-1);
    }

    /**
     * 获取指定位置节点操作的具体实现
     * @param index
     * @param lower
     * @param upper
     * @return Node<T>
     */
    private Node<T> getNode(int index, int lower, int upper)
    {
        if(index<lower || index>upper)
            throw new IndexOutOfBoundsException();
        Node<T> p;
        if(index<size()/2){
            p=head.next;
            for(int i=0;i<index;i++)
                p=p.next;
        }else{
            p=tail;
            for(int i=size;i>index;i--)
                p=p.prev;
        }
        return p;
    }

    /**
     * 返回一个迭代器
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * 定义自定义迭代器LinkedListIterator
     */
    private class LinkedListIterator implements Iterator<T>
    {
        int expectMod= LinkedList.this.modCount;

        Node<T> current=head.next;

        boolean okToRemove=false;

        /**
         * 删除迭代器当前位置的元素
         */
        @Override
        public void remove() {
            if(expectMod!=modCount)
                throw new ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();
            LinkedList.this.remove(current.prev);
            expectMod++;
            okToRemove=false;
        }

        /**
         * 当前迭代器指向位置的后面是否还存在元素
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return current!=tail;
        }

        /**
         * 返回迭代器指向位置的元素
         * @return
         */
        @Override
        public T next() {
            if(expectMod!=modCount)
                throw new ConcurrentModificationException();
            if(!hasNext())
                throw new NoSuchElementException();
            T data=current.data;
            current=current.next;
            okToRemove=true;
            return data;
        }
    }
}
