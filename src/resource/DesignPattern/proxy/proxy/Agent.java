package shixinzhang.dp.proxy;

/**
 * Created by albert on 2017/6/17.
 */
public class Agent implements IArtist {
    private IArtist idolArtist;

    public Agent(IArtist idolArtist) {
        this.idolArtist = idolArtist;
    }

    @Override
    public void talkShow(int paid, String showName) {
        if (paid < 8000) {
            System.out.println(paid+" 元，只出的起香蕉，就去請猴子吧！");
            return;
        }
        idolArtist.talkShow(paid,showName);
    }

    @Override
    public void liveShow(int paid, String showName) {
        if (paid < 16000) {
            System.out.println(paid+" 元，只出的起香蕉，就去請猴子吧！");
            return;
        }
        idolArtist.liveShow(paid,showName);
    }
}
