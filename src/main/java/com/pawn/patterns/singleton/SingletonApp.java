package com.pawn.patterns.singleton;

/**
 * Created by Show on 2017/6/19.
 */
public class SingletonApp {
    public static void main(String[] args) {
        StaticVesion instance1 = StaticVesion.getInstance();
        StaticVesion instance2 = StaticVesion.getInstance();
        System.out.println(instance1==instance2);
    }
}
