package com.sjain.basic;

import java.util.*;
import java.util.function.*;

public class LambdaTest {

    @FunctionalInterface
    interface TernaryPredicate<T, U, V> {
        boolean test(T var1, U var2, V var3);

        default TernaryPredicate<T, U, V> or(TernaryPredicate<? super T, ? super U, ? super V> p2) {
            Objects.requireNonNull(p2);
            return (var1, var2, var3) -> {
                return this.test(var1, var2, var3) || p2.test(var1, var2, var3);
            };
        }
    }

    public static void lambdaCompare(Integer[] a) {
        //comparator functional interface
        Comparator<Integer> c = (Integer a1, Integer a2) -> (Integer.compare(a1, a2));
        Arrays.sort(a, c);
    }

    public static Thread lambdaRunnable() {
        Thread t = new Thread(
                () -> { System.out.println(Thread.currentThread().getId() + " running"); }
            );
        t.start();
        return t;
    }

    public static void testCustomPredicate() {
        TernaryPredicate<Integer, Integer, Integer> p1 = (a, b, c) -> (a + b <= c);
        TernaryPredicate<Integer, Integer, Integer> p2 = (a, b, c) -> (a + c <= b);
        TernaryPredicate<Integer, Integer, Integer> p = p1.or(p2);
        assert p.test(1, 2, 3) == true;
        assert p.test(1, 3, 3) == false;
    }

    public static String testCompose() {
        UnaryOperator<String> f1 = (s) -> (s.toUpperCase());
        UnaryOperator<String> f2 = (s) -> ("'" + s + "'");
        Function<String, String> f = f2.compose(f1);
        return f.apply("hello world");
    }

    public static void testLambdaPrimitives() {
        IntFunction<String> f1 = (i) -> (String.valueOf(i));
        DoubleToIntFunction f2 = (i) -> ((int)Math.ceil(i));
        ToDoubleBiFunction<String, Long> f3 = (i, j) -> ((Double.valueOf(i) + j)/100);

        assert f1.apply(100) == "100";
        assert f2.applyAsInt(5.6) == 6;
        assert f3.applyAsDouble("100.5",100L) == 2.005;
    }

    private static Supplier<Integer> getSupplier() {
        final int[] val = {0};
        return () -> (val[0]+=1);
    }

    public static void testSupplier() {
        //final int[] val = {0};
        //Supplier<Integer> f = () -> (val[0]+=1);
        Supplier<Integer> f = getSupplier();
        assert f.get() == 1;
        assert f.get() == 2;
        assert f.get() == 3;
    }

    public static void testConsumer() {
        Consumer<String> f = (s) -> { System.out.print(s); };
        f.accept("hello world");
    }

    public static void testMethodReference() {
        Function<String, Integer> f1 = Integer::valueOf;
        assert f1.apply("54") == 54;

        Function<String, Integer> f2 = String::length;
        assert f2.apply("abc") == 3;

        Supplier<Stack<Integer>> f3 = Stack<Integer>::new;
        Stack<Integer> st = f3.get();
        st.push(1);
        assert st.pop() == 1;
        assert st.isEmpty();

        Function<Integer, List[]> f4 = List[]::new;
        List[] l = f4.apply(10);
        assert l.length == 10;
        assert l[0] == null;



    }

}
