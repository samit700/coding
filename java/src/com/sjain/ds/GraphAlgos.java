package com.sjain.ds;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphAlgos {

    public static int distance(Graph<Integer> g, int from, int to) {
        Map<Integer, List<Integer>> adjList = g.getAdjList();

        CircularQueue<Integer> q = new CircularQueue<>();
        q.add(from);

        Set<Integer> visited = new HashSet<>();

        int distance = 0;

        while (!q.isEmpty()) {
            int v = q.poll();
            visited.add(v);
            if (v == to)
                return distance;

            List<Integer> lst = adjList.get(v);
            for (Integer e : lst) {
                if (visited.contains(e))
                    continue;
                q.add(e);
            }
            ++distance;
        }

        return -1;
    }

}
