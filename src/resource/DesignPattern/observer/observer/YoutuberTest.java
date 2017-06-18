package shixinzhang.dp.observer;

import org.junit.Test;

/**
 * Created by albert on 2017/6/18.
 */
public class YoutuberTest {
    @Test
    public void testSubscribe() {
        Youtuber amoGood = new Youtuber("谷阿莫");
        Audience albert = new Audience("路人甲");
        System.out.println("路人甲尚未訂閱時，阿莫發佈影片：");
        amoGood.notifyObserver(new Film("5分鐘看完2017屍體害你的電影《驗屍官 The Autopsy of Jane Doe 》","AmoGood"));
        System.out.println("路人甲訂閱後，阿莫發佈影片：");
        amoGood.registerObserver(albert);
        amoGood.notifyObserver(new Film("5分鐘看完2017兒子不是老婆生的電影《玩命關頭8 The Fate of the Furious》","AmoGood"));
    }
}
