package com.zhouzhutou.test;

import com.zhouzhutou.hash.QuadraticProbingHashTable;
import com.zhouzhutou.hash.SeparateChainHashTable;

import java.util.List;

/**
 * Created by zhouzhutou on 2017/3/7.
 */
public class HashTest {
    public static void main(String[] args)
    {
        /*HashTable测试*/
        System.out.println("SeparateChainHashTable测试");
        SeparateChainHashTable<Integer> separateChainHashTable=new SeparateChainHashTable<>(15);
        for(int i=0;i<18;i++){
            separateChainHashTable.insert(i);
        }
        List<Integer>[] lists=separateChainHashTable.getLists();
        for(int i=0;i<lists.length;i++)
        {
            System.out.print("第"+i+"个链表:");
            for(Integer x:lists[i]){
                System.out.print(x+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("QuadraticProbingHashTable");
        QuadraticProbingHashTable<Integer> quadraticProbingHashTable=new QuadraticProbingHashTable<>();
        for(int i=0;i<6;i++)
            quadraticProbingHashTable.insert(i);
        System.out.println(quadraticProbingHashTable.contains(3));
        System.out.println("currentSize: "+quadraticProbingHashTable.getSize());
        quadraticProbingHashTable.remove(3);
        System.out.println(quadraticProbingHashTable.contains(3));
        System.out.println("currentSize: "+quadraticProbingHashTable.getSize());


    }
}
