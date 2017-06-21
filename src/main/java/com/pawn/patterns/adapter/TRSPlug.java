package com.pawn.patterns.adapter;

/**
 * Adaptee 需要被轉換類別。
 * TRS connector 是一種常用於連接音響設備，用以傳遞音頻訊號的連接器。
 */
public class TRSPlug {
    public String insert() {
        return "TRS 3.5mm 插頭";
    }
}
