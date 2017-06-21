package com.pawn.patterns.adapter;

/**
 * Adapter 類別，透過內部將Adaptee物件進行包裝。把原始介面轉換成目標介面。
 */
//要實踐目標介面
public class LightningAdapter implements Lightning {
    private TRSPlug TRSPlug;

    public LightningAdapter(TRSPlug TRSPlug) {
        this.TRSPlug = TRSPlug;
    }
    //實踐目標介面所需的方法
    public String insert() {
        return TRSPlug.insert()+" convert to  Lightning插頭.";
    }
}
