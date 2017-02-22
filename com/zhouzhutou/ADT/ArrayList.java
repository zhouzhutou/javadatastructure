package com.zhouzhutou.ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by zhouzhutou on 2017/2/22.
 */
public class ArrayList<T> implements Iterable<T>{

    private static final int DEFAULT_CAPACITY=10;

    private int size;

    private T[] items;


    public ArrayList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    public void doClear(){
        size=0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size()==0;
    }

    public void trimToSize()
    {
        ensureCapacity(size());
    }

    public T get(int index)
    {
        if(index<0 || index>=size())
            throw new ArrayIndexOutOfBoundsException();
        return items[index];
    }

    public T set(int index, T value)
    {
        if(index<0 || index>=size())
            throw new ArrayIndexOutOfBoundsException();
        T oldValue=items[index];
        items[index]=value;
        return oldValue;
    }

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

    public boolean add(T value)
    {
        add(size(), value);
        return true;
    }

    public void add(int index, T value)
    {
        if(items.length==size())
            ensureCapacity(2*size()+1);
        for(int i=size;i>index;i--)
            items[i]=items[i-1];
        items[index]=value;
        size++;
    }

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

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T>
    {

        private int current=0;

        @Override
        public void remove() {
            ArrayList.this.remove(--current);
        }

        @Override
        public boolean hasNext() {
            return current<size;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return items[current++];
        }
    }
}
