package ua.yandex.shad.stream;

import java.util.Iterator;
import ua.yandex.shad.collections.MyLinkedList;
import ua.yandex.shad.function.IntUnaryOperator;
import ua.yandex.shad.function.IntToIntStreamFunction;
import ua.yandex.shad.function.IntPredicate;
import ua.yandex.shad.function.IntConsumer;
import ua.yandex.shad.function.IntBinaryOperator;

public class AsIntStream implements IntStream {

    private MyLinkedList<Integer> values;
    private MyLinkedList<Object> functions;

    private AsIntStream() {
        values = new MyLinkedList<>();
        functions = new MyLinkedList<>();
    }

    public static IntStream of(int... values) {
        AsIntStream curStream = new AsIntStream();
        for (int i : values) {
            curStream.values.add(i);
        }
        return curStream;
    }

    private void doFunctions() {
        for (Object current : functions) {
            if (current instanceof IntPredicate) {
                doFilter((IntPredicate) current);
            }
            else if (current instanceof IntUnaryOperator) {
                doMap((IntUnaryOperator) current);
            }
            else {
                doFlatMap((IntToIntStreamFunction) current);
            }
        }
        functions.clear();
    }
    
    private void doFilter(IntPredicate predicate) {
        MyLinkedList<Integer> newValues = new MyLinkedList<>();
        for (Integer current : values) {
            if (predicate.test(current)) {
                newValues.add(current);
            }
        }
        values = newValues;
    }
    
    private void doMap(IntUnaryOperator unOperator) {
        MyLinkedList<Integer> newValues = new MyLinkedList<>();
        for (Integer current : values) {
            int res = unOperator.apply(current);
            newValues.add(res);
        }
        values = newValues;
    }
    
    private void doFlatMap(IntToIntStreamFunction strFunction) {
        MyLinkedList<Integer> newValues = new MyLinkedList<>();
        for (Integer current : values) {
            AsIntStream newIntStream =
                    (AsIntStream) strFunction.applyAsIntStream(current);
            for (Integer cur : newIntStream.values) {
                newValues.add(cur);
            }
        }
        values = newValues;
    }

    @Override
    public Double average() {
        doFunctions();
        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return (double) sum() / (double) values.size();
    }

    @Override
    public Integer max() {
        doFunctions();
        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int maxV = Integer.MIN_VALUE;
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            int current = iterator.next();
            if (current > maxV) {
                maxV = current;
            }
        }
        return maxV;
    }

    @Override
    public Integer min() {
        doFunctions();
        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int minV = Integer.MAX_VALUE;
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            int current = iterator.next();
            if (current < minV) {
                minV = current;
            }
        }
        return minV;
    }

    @Override
    public long count() {
        doFunctions();
        return values.size();
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        functions.add(predicate);
        return this;
    }

    @Override
    public void forEach(IntConsumer action) {
        doFunctions();
        for (int current : values) {
            action.accept(current);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        functions.add(mapper);
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        doFunctions();
        int res = identity;
        for (int current : values) {
            res = op.apply(res, current);
        }
        return res;
    }

    @Override
    public Integer sum() {
        doFunctions();
        if (values.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Integer sum = 0;
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        return sum;
    }

    @Override
    public int[] toArray() {
        doFunctions();
        Object[] integerArray = values.toArray();
        int[] res = new int[integerArray.length];
        for (int i = 0; i < integerArray.length; i++) {
            res[i] = (int) integerArray[i];
        }
        return res;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        functions.add(func);
        return this;
    }

}
