package com.sjain.ds;

import java.util.*;

public class Graph<E> {

    private Map<E, List<E>> adjList = new HashMap<>();

    public void addVertex(E v) {
        if (!adjList.containsKey(v))
            adjList.put(v, new LinkedList<E>());
    }

    public void addEdge(E from, E to) {
        if (!adjList.containsKey(from))
            addVertex(from);
        if (!adjList.containsKey(to))
            addVertex(to);

        adjList.get(from).add(to);
    }

    public Map<E, List<E>> getAdjList() {
        return Collections.unmodifiableMap(adjList);
    }

    public void dfs(E start) {
        if (!adjList.containsKey(start))
            throw new IllegalArgumentException("start node not found");

        StringBuilder buf = new StringBuilder();
        Stack<E> stack = new Stack<>();
        stack.push(start);
        Set<E> visited = new HashSet<>();

        while (!stack.isEmpty()) {
            E v = stack.pop();
            visited.add(v);
            buf.append(v).append(' ');

            List<E> l = adjList.get(v);
            for (E e : l) {
                if (!visited.contains(e))
                    stack.push(e);
            }
        }

        System.out.println(buf.toString());
    }

    public String toString() {
        return adjList.toString();
    }

    public boolean isCyclic() {
        if (adjList.isEmpty())
            return false;

        Stack<E> stack = new Stack<>();
        stack.push(adjList.keySet().iterator().next());
        Set<E> visited = new HashSet<>();

        while (!stack.isEmpty()) {
            E v = stack.pop();
            visited.add(v);

            List<E> l = adjList.get(v);
            for (E e : l) {
                if (visited.contains(e))
                    return true;
                stack.push(e);
            }
        }
        return false;
    }

}
