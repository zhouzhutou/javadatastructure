package com.zhouzhutou.test;

import com.zhouzhutou.ADT.ArrayList;
import com.zhouzhutou.ADT.LinkedList;
import com.zhouzhutou.ADT.Queue;
import com.zhouzhutou.ADT.Stack;

import java.util.Iterator;

/**
 * Created by zhouzhutou on 2017/2/22.
 */
public class Test {
    public static void main(String[] args)
    {
        /*ArrayList测试*/
        System.out.println("ArrayList测试");
        ArrayList<Integer> list1=new ArrayList<>();
        for(int i=0;i<10;i++)
            list1.add(new Integer(i));
        for(int i=0;i<list1.size();i++)
            System.out.print(list1.get(i)+" ");
        System.out.println();
        list1.remove(3);
        for(int i=0;i<list1.size();i++)
            System.out.print(list1.get(i)+" ");
        System.out.println();
        Iterator<Integer> iterator1=list1.iterator();
        while (iterator1.hasNext())
        {
            Integer item=iterator1.next();
            System.out.print(item+" ");
            if(item%2==0)
                iterator1.remove();
        }
        System.out.println();
        for(int i=0;i<list1.size();i++)
            System.out.print(list1.get(i)+" ");
        System.out.println();
        list1.set(0,11);
        for(int i=0;i<list1.size();i++)
            System.out.print(list1.get(i)+" ");
        System.out.println();
        System.out.println("LinkedList测试");
        /*LinkedList测试*/
        LinkedList<Integer> list2=new LinkedList<>();
        for(int i=0;i<10;i++)
            list2.add(i);
        for(int i=0;i<list2.size();i++)
            System.out.print(list2.get(i)+" ");
        System.out.println();
        list2.remove(4);
        for(int i=0;i<list2.size();i++)
            System.out.print(list2.get(i)+" ");
        System.out.println();
        list2.set(4,100);
        for(int i=0;i<list2.size();i++)
            System.out.print(list2.get(i)+" ");
        System.out.println();
        Iterator<Integer> iterator2=list2.iterator();
        while (iterator2.hasNext()){
            int i=iterator2.next();
            System.out.print(i+" ");
            if(i%2==0)
                iterator2.remove();
        }
        System.out.println();
        for(int i=0;i<list2.size();i++)
            System.out.print(list2.get(i)+" ");
        System.out.println();
        /*stack测试*/
        System.out.println("Stack测试");
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<10;i++)
            stack.push(i);
        System.out.println(stack.top());
        int size=stack.size();
        for(int i=0;i<size/2;i++)
            System.out.print(stack.pop()+" ");
        System.out.println();
        System.out.println(stack.top());
        /*Queue测试*/
        System.out.println("Queue测试");
        Queue<Integer> queue=new Queue<>();
        for(int i=0;i<10;i++)
            queue.enqueue(i);
        System.out.println("queue front: "+queue.front());
        System.out.println("queue rear: "+queue.rear());
        System.out.println("queuq size: "+queue.size());
        for(int i=0;i<4;i++)
            queue.dequeue();
        System.out.println("queue front: "+queue.front());
        System.out.println("queue rear: "+queue.rear());
        System.out.println("queuq size: "+queue.size());
    }
}
