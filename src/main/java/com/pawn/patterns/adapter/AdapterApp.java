package com.pawn.patterns.adapter;

/**
 * Created by Show on 2017/6/19.
 */
public class AdapterApp {
    public static void main(String[] args) {
        IPhone7 iPhone7=new IPhone7();
        //使用傳統3.5mm插頭
        iPhone7.playMusic(new TRSPlug());
        //使用Apple限定的Lightning插頭。
        iPhone7.playMusic(new LightningPlug());

        //透過轉接器將傳統3.5mm插頭，轉接成Lightning插頭。
        iPhone7.playMusic(new LightningAdapter(new TRSPlug()));

    }
}