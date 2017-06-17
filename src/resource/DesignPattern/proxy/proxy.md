
#proxy

##靜態代理:

![MacDown logo](https://gitcdn.link/repo/show1po/DesignPattern/master/src/resource/DesignPattern/proxy/proxy_agent.png)

~~~java
/**
 *藝人介面，定義藝人的基本行為。
 */
public interface IArtist {
	/**上脫口秀節目
	 * @param paid 通告費
	 * @param showName 節目名稱
	 */
    void talkShow(int paid, String showName);
    /**上實境秀節目
	 * @param paid 通告費
	 * @param showName 節目名稱
	 */
    void liveShow(int paid, String showName);
}
~~~
IdolArtist 偶像類型藝人，為藝人類別實作。

~~~java
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
~~~
定義經紀人：代理類別 Agent 

~~~java
public class Agent implements IArtist {
	//代理藝人行為
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
~~~
###進行單元測試：

~~~java
//聯繫經紀人，敲通告找藝人來上節目。
@Test 
public void callArtist() {
    IArtist MCHotDog = new IdolArtist("MCHotDog");
    Agent agent = new Agent(MCHotDog);
    agent.talkShow(1000,"康熙來了");
    agent.liveShow(20000,"飢餓遊戲");
}
~~~
###執行結果

![MacDown logo](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/proxy/Artist%20console.png)

###缺點
* 代理類別 Agent 和被代理類別 Star 需實作相同介面。若介面有變動，則代理類別與被代理類別均須調整。
* 執行前須先呼叫代理類別，若有多項代理則須一一調用。


##動態代理：


#參考：
* [动态代理：1 个经纪人如何代理 N 个明星][proxy]
[proxy]:http://blog.csdn.net/u011240877/article/details/52334547
