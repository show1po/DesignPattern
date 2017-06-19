package com.pawn.patterns.adapter;

/**
 * Created by Show on 2017/6/19.
 */
public class IPhone7 {
    public void playMusic(Object plug) {
        if (plug instanceof Lightning ) {
            Lightning lightningSlot = (Lightning) plug;
            System.out.println(lightningSlot.insert()+" 連接成功，播放音樂中。");
        }else{
            System.out.println("連接失敗，請使用 Lightning 接口的插頭.");
        }
    }
}
