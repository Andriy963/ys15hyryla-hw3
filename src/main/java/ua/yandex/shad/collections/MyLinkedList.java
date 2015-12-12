package ua.yandex.shad.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyLinkedList<T> implements Iterable<T> {

    private int size;
    private Node start;
    private Node end;
    
    public MyLinkedList(T ... values) {
        for (T value : values) {
            add(value);
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    public void add(T value) {
        Node newValue = new Node(value);
        if (isEmpty()) {
            start = newValue;
            end = newValue;
        }
        else {
            end.next = newValue;
            end = end.next;
        }
        size++;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void clear() {
        size = 0;
        start = null;
        end = null;
    }
    
    public T[] toArray() {
        T[] result = (T[]) new Object[size];
        Iterator<T> iter = iterator();
        int count = 0;
        while (iter.hasNext()) {
            result[count] = iter.next();
            count++;
        }
        return result;
    }
    
    private static class Node<T> {
        private final T value;
        private Node next;
        
        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }
    
    private class MyLinkedListIterator<T> implements Iterator<T> {

        private Node<T> cur = start;
        
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T res = cur.value;
            cur = cur.next;
            return res;
        }
    }
}
