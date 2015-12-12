package ua.yandex.shad.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

public class MyLinkedListTest {
    
    class Func {
        
    }
    
    @Test
    public void testAddWithInteger() {
        MyLinkedList<Integer> test = new MyLinkedList<>();
        Integer val = 5;
        test.add(val);
        int size = test.size();
        int expectSize = 1;
        assertEquals(size, expectSize);
    }
    
    @Test
    public void testAddWithIntFunc() {
        MyLinkedList<Func> test = new MyLinkedList<>();
        Func val = new Func();
        test.add(val);
        int size = test.size();
        int expectSize = 1;
        assertEquals(size, expectSize);
    }
    
    @Test
    public void testAddWithSeveralInteger() {
        MyLinkedList<Integer> test = new MyLinkedList<>();
        Integer val1 = 5;
        Integer val2 = 525;
        Integer val3 = 5564;
        Integer val4 = 528;
        test.add(val1);
        test.add(val2);
        test.add(val3);
        test.add(val4);
        int size = test.size();
        int expectSize = 4;
        assertEquals(size, expectSize);
    }
    
    @Test
    public void testIsEmptyFalseResult() {
        MyLinkedList<Integer> test = new MyLinkedList<>(1, 2, 548, 4513, 4587);
        boolean res = test.isEmpty();
        boolean expectRes = false;
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testIsEmptyTrueResult() {
        MyLinkedList<Integer> test = new MyLinkedList<>();
        boolean res = test.isEmpty();
        boolean expectRes = true;
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testClear() {
        MyLinkedList<Integer> test = new MyLinkedList<>(1, 254, 1546, 1248);
        test.clear();
        assertTrue(test.isEmpty());
    }
    
    @Test
    public void testToArrayWithIntegers() {
        MyLinkedList<Integer> test = new MyLinkedList<>(1, 254, 1546, 1248);
        Object[] res = test.toArray();
        Object[] expectRes = {1, 254, 1546, 1248};
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testIteratorEmptyList() {
        MyLinkedList<Integer> test = new MyLinkedList<>();
        Iterator<Integer> iterator = test.iterator();
        iterator.next();
    }
    
    @Test
    public void testIteratorListWithElements() {
        MyLinkedList<Integer> test = new MyLinkedList<>(1, 254, 2146, 1245);
        Iterator<Integer> iterator = test.iterator();
        Integer[] expectRes = {1, 254, 2146, 1245};
        boolean equalArrays = true;
        
        int iter = 0;
        while (iterator.hasNext()) {
            if (!Objects.equals(iterator.next(), expectRes[iter])) {
                equalArrays = false;
                break;
            }
            iter++;
        }
        assertTrue(equalArrays && iter == 4);
    }
}
