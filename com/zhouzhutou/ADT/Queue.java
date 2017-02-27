package com.zhouzhutou.ADT;

/**
 * Created by zhouzhutou on 2017/2/27.
 * 自定义Queue类
 */
public class Queue<T> {
    /**
     * 定义一个链表
     */
    private LinkedList<T> list=new LinkedList<>();

    /**
     * 入队操作
     * @param value
     * @return boolean
     */
    public boolean enqueue(T value)
    {
       return list.add(value);
    }

    /**
     * 出队操作
     * @return T
     */
    public T dequeue()
    {
        return list.remove(list.size()-1);
    }

    /**
     * 获取队头元素
     * @return T
     */
    public T front()
    {
        return list.get(0);
    }

    /**
     * 获取队尾元素
     * @return T
     */
    public T rear()
    {
        return list.get(list.size()-1);
    }

    /**
     *
     * @return int
     */
    public int size()
    {
        return list.size();
    }
}
