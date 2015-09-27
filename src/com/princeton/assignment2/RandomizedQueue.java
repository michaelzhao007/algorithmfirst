package com.princeton.assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;

    private int N;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        if (N == arr.length)
            resize(2 * (arr.length));
        arr[N++] = item;
    }

    public Item dequeue() {
        if (size() == 0)
            throw new NoSuchElementException();
        int i = StdRandom.uniform(N);
        Item item = arr[i];
        arr[i] = arr[N - 1];
        arr[N - 1] = null;
        N--;
        if (N > 0 && N == (arr.length / 4))
            resize(arr.length / 2);
        return item;
    }

    public Item sample() {
        if (size() == 0)
            throw new NoSuchElementException();
        int i = StdRandom.uniform(N);
        return arr[i];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] copy = (Item[]) new Object[size()];

        int sizeCopy = size();

        private RandomizedQueueIterator() {
            getCopyArr();
        }

        private void getCopyArr() {
            for (int i = 0; i < size(); i++) {
                copy[i] = arr[i];
            }
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return sizeCopy != 0;
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            if (sizeCopy == 0)
                throw new NoSuchElementException();
            int i = StdRandom.uniform(N);
            Item itemRet = copy[i];
            while (itemRet == null) {
                i = StdRandom.uniform(N);
                itemRet = copy[i];
            }
            copy[i] = null;
            sizeCopy--;
            return itemRet;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        // unit testing
        RandomizedQueue<Integer> qu = new RandomizedQueue<Integer>();
        qu.enqueue(1);
        qu.enqueue(2);
        qu.enqueue(3);
        System.out.println(qu.size());
        System.out.println(qu.dequeue());
        Iterator<Integer> it = qu.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
