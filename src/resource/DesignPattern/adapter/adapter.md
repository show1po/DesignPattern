# 轉接器模式(Adapter):愛瘋愛瘋，夜裡CookCook #

## 前言 ##

還記得在2007年時，賈伯斯宣布 ***「今天！蘋果重新發明了電話」*** 。
至此以後展開了『大愛瘋時代』，對許多人來說iphone就代表著創新、新潮的系列作品，風靡於各大果粉之間，也有人為[買iPhone 6s不惜一切到醫院賣腎](http://www.appledaily.com.tw/realtimenews/article/new/20150915/691838/)。

事實上創新就是等同改變，在講求推陳出新的iphone系列中，不乏許多頂尖的創新，屏除從jobs換成Tim Cook這個執行長改變以外。

![Tim Cook](data:image/jpeg;base64,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)

對我來說最劃時代的改變，就是在iphone7中採用無線耳機，並且取消了存在幾十年的傳統3.5mm標準耳機接口。
那你一定忍不住想問：那我的傳統3.55mm耳機，只能丟垃圾桶了嗎?
### 不! ###
你的心聲，還沒說出口，蘋果就都聽到了~

![mind](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/adapter/mind.jpg)

你可以花NT$350到蘋果商店，購買
[Lightning 對 3.5 公釐耳機插孔轉接器
](https://www.apple.com/tw/shop/product/MMX62FE/A/lightning-%E5%B0%8D-35-%E5%85%AC%E9%87%90%E8%80%B3%E6%A9%9F%E6%8F%92%E5%AD%94%E8%BD%89%E6%8E%A5%E5%99%A8)

再搭配你的iphone7

### 就可以 ###

![AdapterIphone](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/adapter/Lightning.jpg)

-

PS.至於無線耳機呢？

它並不是標準配備，需要額外加購，加購價:NT$5,490

[AirPods](https://www.apple.com/tw/shop/product/MMEF2TA/A/airpods?fnode=36a4f29340c054879ca1f90063ad61dfe7976a7103a6bd1f51b225bddf79c8d2d2f5c5c6128fc7b17d5aad2012e85b56f68284f5295b1642fab80004a83457cea7d18c2a650ab29cd3345fd542e6e15270151d32edc59b7b6218a604f64a366b43224ea3b3c63c44e6ffe5422a5cb72b942672f41900e7bfcfabf94006d9518e)


## 主角登場 ##

轉接器模式:將一個接口轉成另一個接口，提升相容性。

- 物件轉接器
- 類別轉接器

## 物件轉接器 ##

![images](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/adapter/images.png)

#### 過程: ####

1. 客戶透過目標介面呼叫轉接器方法，對轉接器發出要求。
2. 轉接器透過被轉接者介面，呼叫被轉接者的方法，將請求轉交給被轉接者。
3. 客戶接受到呼叫結果，但並未察覺這一切是透過轉接器居中聯繫。


#### 釐清角色： ####

* 男主角 TRSPlug飾演：平凡的插頭，他整天夢想要成為人生勝利組。
* 人生勝利組 Lightning飾演:IPHONE 7的標準插頭介面。
* 師傅 LightningAdapter飾演：男主角導師，也就是將TRSPlug變成人生勝利組 (Lightning)的形狀。

![adapterClass](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/adapter/adapter_class.png)

1. 找到兩大主角，目標介面和被轉換類別。

~~~java
/**
 * Target 目標介面，可以是具體或抽象類別。
 */
public interface Lightning {
    String insert();
}

/**
 * Adaptee 需要被轉換類別。
 * TRS connector 是一種常用於連接音響設備，用以傳遞音頻訊號的連接器。
 */
public class TRSPlug {
    public String insert() {
        return "TRS 3.5mm 插頭";
    }
}
~~~

2. 實作目標介面的所有方法,傳入需要被轉換類別(TRSPlug)，呼叫被轉換類別的相對應實作方法。

~~~java
/**
 * Adapter 類別，透過內部將Adaptee物件進行包裝。把原始介面轉換成目標介面。
 */
//要實踐目標介面
public class LightningAdapter implements Lightning {
    private TRSPlug TRSPlug;

    public LightningAdapter(TRSPlug TRSPlug) {
        this.TRSPlug = TRSPlug;
    }
    //實踐目標介面所需的方法
    public String insert() {
        return TRSPlug.insert()+" convert to  Lightning插頭.";
    }
}
~~~

3. 應用端可利用instanceof去識別出不同介面，所因應的操作環節。

~~~java
/**
 * Created by Show on 2017/6/19.
 */
public class IPhone7 {
    public void playMusic(Object plug) {
        if (plug instanceof Lightning ) {
            Lightning lightningSlot = (Lightning) plug;
            System.out.println(lightningSlot.insert()+" 連接成功，播放音樂中。");
        }else{
            System.out.println("連接失敗，請使用 Lightning 接口的插頭.");
        }
    }
}
~~~

4. 單元測試:

~~~java
@Test
public void testAdapter(){
    IPhone7 iPhone7=new IPhone7();

    System.out.println("使用傳統3.5mm插頭");
    iPhone7.playMusic(new TRSPlug());

    System.out.println("\n使用Apple限定的Lightning插頭。");
    iPhone7.playMusic(new LightningPlug());

    System.out.println("\n透過轉接器將傳統3.5mm插頭，轉接成Lightning插頭。");
    iPhone7.playMusic(new LightningAdapter(new TRSPlug()));
}
~~~

![console](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/adapter/adapter_consloe.png)