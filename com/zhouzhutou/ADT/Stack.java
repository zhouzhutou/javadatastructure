package com.zhouzhutou.ADT;

/**
 * Created by zhouzhutou on 2017/2/24.
 * 自定义Stack类
 */
public class Stack<T> {
    /**
     * 定义一个线性表
     */
    private ArrayList<T> stack;

    /**
     * Stack构造函数
     */
    public Stack()
    {
        stack=new ArrayList<>();
    }

    /**
     * 向栈顶添加元素
     */
    public void push(T value)
    {
        stack.add(value);
    }

    /**
     * 弹出栈顶元素
     * @return
     */
    public T pop()
    {
        return stack.remove(stack.size()-1);
    }

    /**
     * 返回栈顶元素
     * @return
     */
    public T top()
    {
        return stack.get(stack.size()-1);
    }

    /**
     * 获取栈存储元素的大小
     * @return int
     */
    public int size()
    {
        return stack.size();
    }
}
