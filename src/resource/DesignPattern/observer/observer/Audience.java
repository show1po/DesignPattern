package shixinzhang.dp.observer;

/**
 * Created by albert on 2017/6/18.
 */
public class Audience implements Observer {
    private String name;

    public Audience(String name) {
        this.name = name;
    }

    @Override
    public void update(Channel observable, Film film) {
        System.out.println("親愛的 "+name+",實況主:"+observable.getChannelName()+"，已經更新影片："+film.getName()+"。");
    }
}
