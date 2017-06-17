package shixinzhang.dp.proxy;

/**
 * Created by albert on 2017/6/17.
 */
public class IdolArtist implements IArtist {
    private String name;

    public IdolArtist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void talkShow(int paid, String showName) {
        System.out.println(name+" 參與脫口秀節目: "+showName+",片酬："+paid);

    }

    @Override
    public void liveShow(int paid, String showName) {
        System.out.println(name+" 參與實境秀節目: "+showName+",片酬："+paid);

    }
}
