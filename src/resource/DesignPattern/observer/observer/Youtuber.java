package shixinzhang.dp.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albert on 2017/6/18.
 */
public class Youtuber implements Channel {
    private String name;

    public Youtuber(String name) {
        this.name = name;
    }

    public String getChannelName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //訂閱者清單：所有訂閱這個頻道的觀眾名單。
    List<Observer> observers = new ArrayList<>();
    /***
     * 觀眾訂閱實況主
     * @param observer
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObserver(Film film) {
        System.out.println(name+" 已經發佈影片："+film.getName()+"。");
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this,film);
        }
    }
}
