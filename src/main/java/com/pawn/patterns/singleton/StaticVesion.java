package com.pawn.patterns.singleton;

/**
 * 靜態內部類別僅適用於java語言。
 */
public class StaticVesion {
    //私有建構子，避免其他類別創建。
    private StaticVesion() {
    }
    //靜態內部類別中實例化該單例。由於靜態內部類別特性，使用時才載入，可以達到延遲載入。
    private static class singletonHolder{
        private static final StaticVesion instance = new StaticVesion();
    }
    //外部透過此方法取得實例。
    public static StaticVesion getInstance() {
        return singletonHolder.instance;
    }
}
