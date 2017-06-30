package com.pawn.patterns.javalang;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by albert on 2017/6/29.
 */
public class AbstractConstructorImplTest {
    @Test
    public void test() {
        AbstractConstructorImpl abstractConstructor = new AbstractConstructorImpl("hi","halo");
        System.out.println(abstractConstructor);
    }

}