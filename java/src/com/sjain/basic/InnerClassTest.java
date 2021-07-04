package com.sjain.basic;

public class InnerClassTest {

    public static void f() {
        class a {
            int a = 10;
        }

        System.out.println((new a()).a);
    }

    public static void main() {
        f();
    }
}
