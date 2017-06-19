package com.pawn.patterns.adapter;

/**
 *  Apple所出品的連接器，此連接器針腳為8 pin，正反面皆可插，尺寸與Micro USB相近。
 */
public class LightningPlug implements Lightning{

    public String insert() {
        return "Lightning插頭";
    }
}
