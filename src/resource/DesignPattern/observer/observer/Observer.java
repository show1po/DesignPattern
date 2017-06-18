package shixinzhang.dp.observer;

/**
 * 觀察者介面：當被觀察者發佈影片時，Youtuber.notifyObserver()呼叫Audience.update()來通知觀眾。
 */
public interface Observer {
    void update(Channel observable, Film film);
}
