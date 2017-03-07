package com.zhouzhutou.test;

import com.zhouzhutou.hash.HashTable;

import java.util.List;

/**
 * Created by zhouzhutou on 2017/3/7.
 */
public class HashTest {
    public static void main(String[] args)
    {
        /*HashTable测试*/
        System.out.println("HashTable测试");
        HashTable<Integer> hashTable=new HashTable<>(15);
        for(int i=0;i<18;i++){
            hashTable.insert(i);
        }
        List<Integer>[] lists=hashTable.getLists();
        for(int i=0;i<lists.length;i++)
        {
            System.out.print("第"+i+"个链表:");
            for(Integer x:lists[i]){
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }
}
