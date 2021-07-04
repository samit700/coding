package com.sjain.basic;

public class BindingTest {

    public static class A {
        int a = 5;
        public void f() {
            System.out.println("A: " + a);
        }

        public static void g() {
            System.out.println("A.g()");

        }
    }


    public static class B extends A {
        int a = 10;

        public static void g() {
            System.out.println("B.g()");

        }
    }


    public static void main(String[] args) {
        A a = new B();
        a.f();

        A b = new B();
        a.g();
        b.g();
    }


}
