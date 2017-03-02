package com.zhouzhutou.sort;

/**
 * Created by zhouzhutou on 2017/3/1.
 * 自定义Sort类
 */
public class Sort<T extends Comparable<? super T>> {
    /**
     * 插入排序
     * @param nums
     */
    public void insertSort(T nums[])
    {
        if(nums==null || nums.length==0 || nums.length==1)
            return;
        int j;
        for(int i=1;i<nums.length;i++)
        {
            T tmp=nums[i];
            for(j=i;j>0 && tmp.compareTo(nums[j-1])<0;j--)
                nums[j]=nums[j-1];
            nums[j]=tmp;
        }
    }

    /**
     * 希尔排序
     * @param nums
     */
    public void shellSort(T nums[])
    {
        if(nums==null || nums.length==0 || nums.length==1)
            return ;
        int j;
        for(int gap=nums.length/2;gap>0;gap/=2)
        {
            for(int i=gap;i<nums.length;i+=gap)
            {
                T tmp=nums[i];
                for(j=i;j>=gap && tmp.compareTo(nums[j-gap])<0;j-=gap)
                    nums[j]=nums[j-gap];
                nums[j]=tmp;
            }
        }
    }

    /**
     * 进行堆排序
     * @param nums
     */
    public void heapSort(T nums[])
    {
        if(nums==null || nums.length==0 || nums.length==1)
            return ;
        buildHeap(nums);
        int i=nums.length;
        while (i>1){
            deleteMax(nums,i);
            i--;
        }
    }

    /**
     * 构建堆
     * @param nums
     */
    public void buildHeap(T nums[])
    {
        for(int i=0;i<nums.length;i++)
        {
            insertHeap(nums[i],nums,i);
        }
    }

    /**
     * 向堆中插入元素的具体实现
     * @param value
     * @param nums
     * @param holeIndex
     */
    private void insertHeap(T value,T[] nums,int holeIndex)
    {
        int parent=(holeIndex-1)/2;
        while (holeIndex>0 && nums[parent].compareTo(value)<0)
        {
            nums[holeIndex]=nums[parent];
            holeIndex=parent;
            parent=(holeIndex-1)/2;
        }
        nums[holeIndex]=value;
    }

    /**
     * 从堆顶删除元素的具体实现
     * @param nums
     * @param length
     */
    private void deleteMax(T[] nums,int length)
    {
        T tmp=nums[length-1];
        nums[length-1]=nums[0];
        int holeIndex=0;
        int secondChild=2*holeIndex+2;
        while (secondChild<length-1){
            if(nums[secondChild].compareTo(nums[secondChild-1])<0)
                secondChild--;
            nums[holeIndex]=nums[secondChild];
            holeIndex=secondChild;
            secondChild=2*holeIndex+2;
        }
        if(secondChild==length-1){
            nums[holeIndex]=nums[secondChild-1];
            holeIndex=secondChild-1;
        }
        insertHeap(tmp,nums,holeIndex);
    }

    /**
     * 交换数组中指定索引的两个元素
     * @param nums
     * @param left
     * @param right
     */
    private void swap(T nums[],int left, int right)
    {
        T temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }

    /**
     * 返回三个数中的最小值
     * @param a
     * @param b
     * @param c
     * @return T
     */
    public T median3(T a, T b, T c)
    {
        if(a.compareTo(b)<0) {
            if (b.compareTo(c) < 0)
                return b;
            else if (a.compareTo(c) < 0)
                return c;
            else
                return a;
        }else{
            if(b.compareTo(c)>0)
                return b;
            else if(a.compareTo(c)>0)
                return c;
            else
                return a;
        }
    }

    /**
     * 快速排序入口
     * @param nums
     */
    public void quickSort(T nums[])
    {
        quickSort(nums,0,nums.length-1);
    }

    /**
     * 递归完成快速排序
     * @param nums
     * @param left
     * @param right
     */
    public void quickSort(T nums[],int left,int right)
    {
        if(left<right) {
            T privot = median3(nums[left], nums[right], nums[(left + right) / 2]);
            int middle = partition(nums, left, right, privot);
            quickSort(nums, left, middle - 1);
            quickSort(nums, middle + 1, right);
        }
    }

    /**
     * 分割
     * @param nums
     * @param left
     * @param right
     * @param privot
     * @return int
     */
    public int partition(T nums[], int left, int right,T privot)
    {
        while (true){
            while (nums[left].compareTo(privot)<0) ++left;
            while (nums[right].compareTo(privot)>0) --right;
            if(!(left<right))
                return left;
            swap(nums,left,right);
            left++;
        }
    }
}
