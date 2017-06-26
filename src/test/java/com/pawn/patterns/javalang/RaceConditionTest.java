package com.pawn.patterns.javalang;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Show on 2017/6/26.
 */
public class RaceConditionTest {

    @Test
    public void testRaceCondition() {
        RaceCondition raceCondition=new RaceCondition();
        new Thread(()->{
            while (true) {
                System.out.println("Thread A:"+raceCondition.getPlusOneResult());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                System.out.println("Thread B:"+raceCondition.getPlusOneResult());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        sleep(5);
    }
    @Test
    public void testImproveRaceCondition() {
        RaceCondition raceCondition=new RaceCondition();
        new Thread(()->{
            while (true) {
                System.out.println("Thread A:"+raceCondition.getSyncPlusOneResult());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                System.out.println("Thread B:"+raceCondition.getSyncPlusOneResult());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        sleep(5);
    }
    public void sleep(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}