# Observer 觀察者模式：Youtube訂閱，擅長二次創作的谷阿莫

## 前言

![MacDown logo](https://yt3.ggpht.com/T5OsnSLil-kvVsFELs2y1sYsMY19eJfrsHlJr9oXP_hm2wPjK2W_hgpnYtTtM9rjPoeBOzpF=w2120-fcrop64=1,00005a57ffffa5a8-nd-c0xffffffff-rj-k-no)


谷阿莫是台灣網紅，擅長『以XX分鐘帶你看完OO電影』，並且將影片上傳至Youtube平台供人觀賞。

- [5分鐘看完2017兒子不是老婆生的電影]("https://www.youtube.com/watch?v=TpPFuP7TioA")

- [5分鐘看完2017屍體害你的電影]("https://www.youtube.com/watch?v=xE7sJjfMYuY")

但是他的影片畫面來源，都是摘錄自盜版影片的畫面，進而引發了“二次創作”的相關爭議。

- [【谷阿莫】我被警方搜查之著作權合理使用原則在網路上的適用](【谷阿莫】我被警方搜查之著作權合理使用原則在網路上的適用 "https://www.youtube.com/watch?v=7icUXwJRaXQ")


----------


那谷阿莫與我們要談的觀察者模式有什麼關聯呢？其實沒有直接相關，哈哈！
此篇主角是Youtube平台上的***“訂閱功能”***，觀眾透過訂閱頻道，每當頻道有更新影片了，便會主動發通知給觀眾，讓觀眾知道有新影片可以觀看，趕緊上線看影片拉ＲＲＲ!

- 拆解步驟：
	1. 首先會關注頻道主人，點選訂閱按鈕(即註冊訂閱)。
	2. 頻道主人發佈新影片。
	3. 訂閱觀眾收到通知，立刻上線看片。


## 進入代碼環節：

![MacDown logo](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/observer/observer_diagram.png)


Channel ：Youtuber會將影片上傳，至開創的頻道之中供觀眾點閱觀賞。

而被觀察者通常會提供三種基本行為：

* 供觀察者註冊 registerObserver(Observer observer)
* 供觀察者移除 removeObserver(Observer observer)
* 通知觀察者 notifyObserver(Data data)

~~~java
/**
 * 頻道介面：被觀察者(Observable)或稱主題(Subject)，具備三種基本行為。
 */
public interface Channel {
    String getChannelName();

    //提供觀眾註冊訂閱。
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    //通知觀眾的方法
    void notifyObserver(Film film);

}
~~~

Observer 觀察者：就是在Youtube平台上面註冊的觀眾，可以訂閱喜歡的Youtuber頻道。

~~~java
/**
 * 觀察者介面：當被觀察者發佈影片時，Youtuber.notifyObserver()呼叫Audience.update()來通知觀眾。
 */
public interface Observer {
    void update(Channel observable, Film film);
}
~~~

Youtuber:Channel的實作者。

~~~java
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
~~~

~~~java
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
~~~

~~~java
public class Film{
    private String name;
    private String creator;

    public Film() {
    }

    public Film(String name, String creator) {
        this.name = name;
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
~~~

### 單元測試：

~~~java
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
~~~

執行結果：

![MacDown logo](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/observer/observer_console.png)

