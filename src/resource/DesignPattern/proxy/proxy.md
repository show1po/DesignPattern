
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
***動態代理*** 運用反射機制，攔截欲代理的操作，並且動態建立***代理類別*** Agent，省去編程代理類別。
JDK中提供Proxy類別來實現動態代理，主要方法：Proxy.newProxyInstance()。

~~~java
public static Object newProxyInstance(
            ClassLoader loader, 
            Class<?>[] interfaces,
            InvocationHandler invocationHandler)
            throws IllegalArgumentException {
            //...方法内容省略
            }
~~~
###範例

![MacDown logo](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/proxy/Dynamic.png)
在此自定義一個Handler，來實現上述靜態代理範例，經紀人對於通告費的接洽控制。

~~~java
/**
 * 動態代理：透過反射機制動態建立而成，省去編成 代理類別 Agent
 * InvocationHandler invoke()，是代理類別進行攔截操作的入口，且Proxy.newProxyInstance()#arg[2] 需要自定義一個Handler來實現。
 */
public class ProxyHandler implements InvocationHandler {
    //被代理類別 IdolArtist
    private Object mTarget;

    public ProxyHandler(Object mTarget) {
        this.mTarget = mTarget;
    }

    /**
     * 方法攔截，可額外進行操作。
     * @param proxy
     * @param method 攔截的方法
     * @param args 方法對應的參數
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.equals("talkShow")) {
            if (args[0] instanceof Integer && (int) args[0] < 8000) {
                System.out.println(((int) args[0]) + " 元，" + ((String) args[1]) + "只出的起香蕉，就去請猴子吧！");
                return null;
            } else {
                System.out.println("Dynamic Proxy");
                Object result = method.invoke(mTarget, args);
                return result;
            }
        }
        if (methodName.equals("liveShow")) {
            if (args[0] instanceof Integer && (int) args[0] < 16000) {
                System.out.println(((int) args[0]) + " 元，" + ((String) args[1]) + "只出的起香蕉，就去請猴子吧！");
                return null;

            } else {
                System.out.println("Dynamic Proxy");
                Object result = method.invoke(mTarget, args);
                return result;
            }
        }
        return null;
    }

    /***
     * 用此方法呼叫JDK Proxy.newProxyInstance()方法生成代理。
     * @ ClassLoader loader 被代理類別的類別載入器，用來創建代理類別 Agent。
     * @ Class<?>[] interfaces 被代理類別實現介面 IArtist。
     * @ InvocationHandler
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(mTarget.getClass().getClassLoader(), mTarget.getClass().getInterfaces(), this);
    }
}
~~~
* 利用getProxy()的呼叫Proxy.newProxyInstance()生成代理類別 Agent。
* 在invoke()，依據方法名稱，創建類別等資訊進行對應攔截處理。

###單元測試

~~~java
    @Test
    public void testInvoke() throws Exception {
        IdolArtist williamShen = new IdolArtist("williamShen");
        ProxyHandler proxyHandler = new ProxyHandler(williamShen);
        IArtist agent = (IArtist) proxyHandler.getProxy();
        agent.liveShow(1000,"飢餓遊戲");
        agent.talkShow(36000,"康納秀");
    }
~~~
- 若被代理類別兼具多重實作，agent可透過強制轉型，實現不同介面。
如：沈玉琳是偶像藝人，也是知名製作人。

~~~java
	IProducer agent = (IProducer) proxyHandler.getProxy();
    agent.createShow(100000,"台灣紅不讓");
~~~

###執行

![MacDown logo](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/proxy/dynamic%20console.png)
使用時只需要傳入藝人，就會動態產生經紀人。
而經紀人不需要去實作欲代理的操作，也不遭IArtist介面修改影響。
##結論
- 動態代理實現Aop思想。
- JDK僅實現對介面實現類別代理， CGLIB (Code Generation Library) 開源項目則彌補了JDK不足。
- 動態代理實作方式：
	1. 自定義實現 InvocationHandler
	2. 根據方法訊息進行攔截，控制
	3. 呼叫傳入代理類別
	4. 根據要使用方法決定返回代理類別的類型


##感謝：
* [动态代理：1 个经纪人如何代理 N 个明星][proxy]
[proxy]:http://blog.csdn.net/u011240877/article/details/52334547
