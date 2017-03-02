package com.zhouzhutou.test;

import com.zhouzhutou.sort.Sort;

/**
 * Created by zhouzhutou on 2017/3/1.
 */
public class SortTest {
    public static void main(String[] args)
    {
        Sort<Integer> sort=new Sort<>();
        /*插入排序测试*/
        System.out.println("插入排序测试");
        Integer arr[]=new Integer[]{2,4,1,78,3,5,65,85};
        sort.insertSort(arr);
        for(Integer i:arr)
            System.out.print(i+" ");
        System.out.println();
         /*希尔排序测试*/
        System.out.println("希尔排序测试");
        arr=new Integer[]{2,4,1,78,3,5,65,85};
        sort.shellSort(arr);
        for(Integer i:arr)
            System.out.print(i+" ");
        System.out.println();
        /*堆排序测试*/
        System.out.println("堆排序排序测试");
        arr=new Integer[]{2,4,1,78,3,5,65,85};
        sort.heapSort(arr);
        for(Integer i:arr)
            System.out.print(i+" ");
        System.out.println();
         /*快速排序测试*/
        System.out.println("快速排序测试");
        arr=new Integer[]{7,3,2,5,7,3,10};
        sort.quickSort(arr);
        for(Integer i:arr)
            System.out.print(i+" ");
        System.out.println();
    }
}
