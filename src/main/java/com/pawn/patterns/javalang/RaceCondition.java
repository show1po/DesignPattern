package com.pawn.patterns.javalang;

/**
 * Created by Show on 2017/6/26.
 */
public class RaceCondition {
    private int count;

    public int getPlusOneResult() {
        return count;
    }
    public synchronized int getSyncPlusOneResult() {
        return count++;
    }

    public void testAsync() {
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
    }

    public void testSync() {
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
    }

    public static void main(String[] args) {
        RaceCondition raceCondition = new RaceCondition();
        raceCondition.testSync();
//        raceCondition.testAsync();
    }
}
