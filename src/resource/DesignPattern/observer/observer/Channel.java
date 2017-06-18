package shixinzhang.dp.observer;

/**
 * 頻道介面：Observable 被觀察者(Subject)
 */
public interface Channel {
    String getChannelName();

    //提供觀眾註冊訂閱。
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    //通知觀眾的方法
    void notifyObserver(Film film);

}
