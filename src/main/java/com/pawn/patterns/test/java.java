package com.pawn.patterns.test;

/**
 * Created by Show on 2017/6/20.
 */
public class java {
    public static void main(String[] args) {
        int a = 0;
        int b=0;
        b = a++ + ++a - a++;

        System.out.println(a+","+b);
        int c = 0;
        int d=0;
        d = c-- + --c - c--;
        System.out.println(c+","+d);
    }
}
