package ua.yandex.shad.stream;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

public class AsIntStreamTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmptyArray() {
        AsIntStream.of().average();
    }
    
    @Test
    public void testAverageArrayWithElements() {
        double EPS = 0.000000000001;
        IntStream test = AsIntStream.of(1, 2, 5, 9);
        double res = test.average();
        double expectRes = 4.25;
        assertEquals(res, expectRes, EPS);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmptyArray() {
        AsIntStream.of().max();
    }
    
    @Test
    public void testMaxArrayWithElements() {
        IntStream test = AsIntStream.of(142, 2, 52, 91);
        int res = test.max();
        int expectRes = 142;
        assertEquals(res, expectRes);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMinEmptyArray() {
        AsIntStream.of().min();
    }
    
    @Test
    public void testMinArrayWithElements() {
        IntStream test = AsIntStream.of(142, 2, 52, 91);
        int res = test.min();
        int expectRes = 2;
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testCountEmptyArray() {
        IntStream test = AsIntStream.of();
        long res = test.count();
        long expectRes = 0;
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testCountArrayWithSeveralElements() {
        IntStream test = AsIntStream.of(142, 2, 52, 91);
        long res = test.count();
        long expectRes = 4;
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testFilterEmptyArray() {
        IntStream test = AsIntStream.of();
        test.filter(x -> (x - 1) == 0);
        int[] res = test.toArray();
        int[] expectRes = {};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testFilterArrayWithElementsBadResult() {
        IntStream test = AsIntStream.of(5, 6, 8, 5);
        test.filter(x -> (x - 1) == 0);
        int[] res = test.toArray();
        int[] expectRes = {};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testFilterArrayWithElementsGodResult() {
        IntStream test = AsIntStream.of(5, 1, 2, 5, 9, 1);
        test.filter(x -> (x - 1) == 0);
        int[] res = test.toArray();
        int[] expectRes = {1, 1};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testFilterArrayWithElementsAllElements() {
        IntStream test = AsIntStream.of(5, 1, 2, 5, 9, 1);
        test.filter(x -> x >= 1);
        int[] res = test.toArray();
        int[] expectRes = {5, 1, 2, 5, 9, 1};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testForEachEmptyArray() {
        IntStream test = AsIntStream.of();
        Temp temp = new Temp();
        test.forEach(temp :: add);
        int res = temp.sum;
        int expectRes = 0;
        
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testForEachArrayWithElements() {
        IntStream test = AsIntStream.of(5, 6, 8, 5);
        Temp temp = new Temp();
        test.forEach(temp :: add);
        int res = temp.sum;
        int expectRes = 24;
        
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testForEachArrayWithElementsMoreElements() {
        IntStream test = AsIntStream.of(5, 6, 8, 5, -3, -9);
        Temp temp = new Temp();
        test.forEach(temp :: add);
        int res = temp.sum;
        int expectRes = 12;
        
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testMapEmptyArray() {
        IntStream test = AsIntStream.of();
        test.map(x -> x - 1);
        int[] res = test.toArray();
        int[] expectRes = {};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testMapArrayWithElements() {
        IntStream test = AsIntStream.of(5, 6, 8, 5);
        test.map(x -> x + 1);
        int[] res = test.toArray();
        int[] expectRes = {6, 7, 9, 6};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testMapArrayWithElementsMoreElements() {
        IntStream test = AsIntStream.of(5, 1, 2, 5, 9, 1);
        test.map(x -> x * 5);
        int[] res = test.toArray();
        int[] expectRes = {25, 5, 10, 25, 45, 5};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testReduceEmptyArray() {
        IntStream test = AsIntStream.of();
        int res = test.reduce(0, (sum, x) -> sum += x);
        int expectRes = 0;
        
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testReduceArrayWithElements() {
        IntStream test = AsIntStream.of(5, 6, 8, 5);
        int res = test.reduce(0, (sum, x) -> sum += x);
        int expectRes = 24;
        
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testReduceArrayWithElementsMoreElements() {
        IntStream test = AsIntStream.of(5, 1, 2, 5, 9, 1);
        int res = test.reduce(0, (sum, x) -> sum += x);
        int expectRes = 23;
        
        assertEquals(res, expectRes);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSumEmptyArray() {
        AsIntStream.of().sum();
    }
    
    @Test
    public void testSumArrayWithElements() {
        IntStream test = AsIntStream.of(142, 2, 52, 91);
        int res = test.sum();
        int expectRes = 287;
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testSumArrayWithElementsZeroResult() {
        IntStream test = AsIntStream.of(142, -7, 2, 52, 91, -280);
        int res = test.sum();
        int expectRes = 0;
        assertEquals(res, expectRes);
    }
    
    @Test
    public void testToArrayEmptyArray() {
        IntStream test = AsIntStream.of();
        int[] res = test.toArray();
        int[] expectRes = {};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testToArrayArrayWithElements() {
        IntStream test = AsIntStream.of(5, 6, 8, 5);
        int[] res = test.toArray();
        int[] expectRes = {5, 6, 8, 5};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testFlatMapEmptyArray() {
        IntStream test = AsIntStream.of();
        test.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
        int[] res = test.toArray();
        int[] expectRes = {};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testFlatMapArrayWithElements() {
        IntStream test = AsIntStream.of(5, 8, 10);
        test.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
        int[] res = test.toArray();
        int[] expectRes = {4, 5, 6, 7, 8, 9, 9, 10, 11};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testFlatMapArrayWithElementsAnotherOperations() {
        IntStream test = AsIntStream.of(5, 8, -3);
        test.flatMap(x -> AsIntStream.of(x / 2, x, x * 2));
        int[] res = test.toArray();
        int[] expectRes = {2, 5, 10, 4, 8, 16, -1, -3, -6};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    @Test
    public void testDifferentOperations() {
        IntStream test = AsIntStream.of(5, 8, -3);
        test.flatMap(x -> AsIntStream.of(x / 2, x, x * 2))
                .filter(x -> x > 0)
                .map(x -> x * 2);
        int[] res = test.toArray();
        int[] expectRes = {4, 10, 20, 8, 16, 32};
        
        Assert.assertArrayEquals(res, expectRes);
    }
    
    class Temp {
        int sum;
        
        public void add(int val) {
            sum += val;
        }
    }
}
