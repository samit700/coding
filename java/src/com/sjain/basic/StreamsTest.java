package com.sjain.basic;

import com.sjain.algo.CharacterTrie;
import com.sjain.ds.MinHeap;

import javax.print.attribute.UnmodifiableSetException;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

public class StreamsTest {

    public static void toCollection() {
        //toList
        List<Integer> l = Arrays.asList(1, 1, 2, 2, 3, 3).stream()
                .map(i -> 2*i)
                .collect(Collectors.toList());
        assert l.size() == 6;
        assert l.get(0) == 2;
        assert l.get(5) == 6;

        //toSet
        Set<Integer> s = Arrays.asList(1, 1, 2, 2, 3, 3).stream()
                .map(i -> 2*i)
                .collect(Collectors.toSet());
        assert s.size() == 3;
        assert s.contains(2);
        assert s.contains(6);

        //primitive array to list
        Supplier<List<Integer>> supplier = ArrayList<Integer>::new;
        ObjIntConsumer<List<Integer>> accumulator = List<Integer>::add;
        BiConsumer<List<Integer>, List<Integer>> combiner = List<Integer>::addAll;
        List<Integer> l2 = Arrays.stream(new int[] {1, 1, 2, 2, 3, 3})
                .map(i -> 2*i)
                .collect(supplier, accumulator, combiner);
        assert l2.size() == 6;
        assert l2.get(0) == 2;
        assert l2.get(5) == 6;

        //custom collection
        List<Integer> l3 = Arrays.asList(1, 1, 2, 2, 3, 3).stream()
                .collect(Collectors.toCollection(LinkedList<Integer>::new));
        assert l3.size() == 6;
        assert l3.get(0) == 1;
        assert l3.get(5) == 3;
    }

    public static void toMap() {
        List<String> l = Arrays.asList("a", "b", "aa", "bb", "aaa", "bbb");
        Map<String,Integer> m = l.stream()
                .collect(Collectors.toMap(String::toUpperCase, String::length));
        assert m.size() == 6;
        assert (!m.containsKey("a"));
        assert m.containsKey("A");
        assert m.get("AA") == 2;

        List<String> l2 = Arrays.asList("a", "a", "bb", "bb", "ccc", "ccc");
        Map<String,Integer> m2 = l.stream()
                .collect(Collectors.toMap(Function.identity(), String::length, (k1, k2) -> k2));
        assert m2.size() == 3;
        assert m2.get("a") == 1;
        assert m2.get("ccc") == 1;
    }

    public static void chain() {
        Set<Integer> s = Arrays.asList(1, 1, 2, 2, 3, 3).stream()
                .map(i -> 2*i)
                .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
        assertThat(s)
                .hasSize(3)
                .contains(2)
                .contains(6)
                .doesNotContain(3);
        assertThatThrownBy(() -> s.add(6))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    public static void stats() {
        List<String> l = Arrays.asList("hello", "world");
        String fmtString = l.stream()
                .collect(Collectors.collectingAndThen(Collectors.joining(" ", "PRE-", "-POST"), String::toUpperCase));
        assertThat(fmtString)
                .startsWith("PRE")
                .endsWith("POST")
                .doesNotContain("hello world")
                .contains("HELLO WORLD");

        List<String> l2 = Arrays.asList("1.2", "3", "42.3");
        DoubleSummaryStatistics stat = l2.stream()
                .collect(Collectors.summarizingDouble(Double::parseDouble));
        assertThat(stat.getCount()).isEqualTo(3);
        assertThat(stat.getAverage()).isGreaterThan(0);
        assertThat(stat.getMax()).isEqualTo(42.3);

        List<String> l3 = Arrays.asList("aa", "bbb", "aaa", "dkjfd", "ccc", "c", "cab");
        Optional<String> maxS = l3.stream()
                .collect(Collectors.maxBy(Comparator.naturalOrder()));
        assertThat(maxS).isPresent();
        assertThat(maxS.get()).startsWith("d");
    }


    public static void grouping() {
        //group
        List<String> l = Arrays.asList("aa", "bbb", "aaa", "dkjfd", "ccc", "c", "cab");
        Map<Character, Set<String>> m = l.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.toSet()));
        assertThat(m)
                .isNotEmpty()
                .hasSize(4)
                .containsKeys('a', 'b', 'c');
        assertThat(m.get('c'))
                .hasSize(3)
                .allMatch(s -> s.charAt(0) == 'c')
                .contains("ccc");

        //partition
        Map<Boolean, List<String>> m2 = l.stream()
                .collect(Collectors.partitioningBy(s -> s.length() == 3));
        assertThat(m2)
                .isNotEmpty()
                .hasSize(2)
                .containsOnlyKeys(Boolean.TRUE, Boolean.FALSE);
        assertThat(m2.get(Boolean.TRUE))
                .hasSize(4)
                .contains("aaa", "bbb");
        assertThat(m2.get(Boolean.FALSE))
                .hasSize(3)
                .contains("aa", "c");

        //tee
        /* double range = IntStream.range(1, 10)
                .collect(Collectors.teeing(Collectors.minBy(Integer::compareTo),
                                           Collectors.maxBy(Integer::compareTo),
                                           (a, b) -> b - a))
         */
    }


    static class StdDevCollector
            implements Collector<Double, double[], Double> {

        @Override
        public Supplier<double[]> supplier() {
            return () -> new double[3];
        }

        @Override
        public BiConsumer<double[], Double> accumulator() {
            return (a, t) -> { a[0] += t*t; a[1] += t; a[2]++; };
        }

        @Override
        public BinaryOperator<double[]> combiner() {
            return (a, b) -> { a[0] += b[0]; a[1] += b[1]; a[2] += b[2]; return a; };
        }

        @Override
        public Function<double[], Double> finisher() {
            return (a) -> { double a1 = a[0]/a[2]; double a2 = a[1]/a[2]; return Math.sqrt(a1 - a2*a2); };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(
                    EnumSet.of(Characteristics.UNORDERED,
                               Characteristics.CONCURRENT));
        }

    }

    //Custom Collector
    public static void customCollector() {
        //stdev collector
        List<Double> l = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10.);
        double stdev = l.stream().collect(new StdDevCollector());
        assertThat(stdev)
                .isGreaterThan(1)
                .isLessThan(10);
    }

}
