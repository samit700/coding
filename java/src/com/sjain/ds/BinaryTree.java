package com.sjain.ds;

import java.util.HashSet;
import java.util.Set;

public class BinaryTree<E> {

    public Node<E> root;

    public static class Node<E> {
        public E value;
        public Node left;
        public Node right;

        public Node(E value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(E value) {
            this.value = value;
        }
    }

    public void bfs() {
        if (root == null)
            return;

        CircularQueue<Node<E>> queue = new CircularQueue<Node<E>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.print(node.value);
            System.out.print(" ");
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        System.out.println();
    }

    public void dfs() {
        if (root == null)
            return;

        Stack<Node<E>> stack = new Stack<Node<E>>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();
            System.out.print(node.value);
            System.out.print(" ");
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        System.out.println();
    }

    public void printInOrder() {
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        Set<Node<E>> visited = new HashSet<>();
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();

            if (visited.contains(node)) {
                System.out.print(node.value + " ");
                continue;
            }

            visited.add(node);

            if (node.right != null)
                stack.push(node.right);

            stack.push(node);

            if (node.left != null)
                stack.push(node.left);
        }
    }
}
