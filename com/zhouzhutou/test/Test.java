package com.zhouzhutou.test;

import com.zhouzhutou.ADT.ArrayList;

import java.util.Iterator;

/**
 * Created by zhouzhutou on 2017/2/22.
 */
public class Test {
    public static void main(String[] args)
    {
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<10;i++)
            list.add(new Integer(i));
        for(int i=0;i<list.size();i++)
            System.out.print(list.get(i)+" ");
        System.out.println();
        list.remove(3);
        for(int i=0;i<list.size();i++)
            System.out.print(list.get(i)+" ");
        System.out.println();
        Iterator<Integer> iterator=list.iterator();
        while (iterator.hasNext())
        {
            Integer item=iterator.next();
            System.out.print(item+" ");
            if(item%2==0)
                iterator.remove();
        }
        System.out.println();
        for(int i=0;i<list.size();i++)
            System.out.print(list.get(i)+" ");
    }
}
