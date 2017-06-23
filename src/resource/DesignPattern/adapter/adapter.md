# 轉接器模式(Adapter):愛瘋愛瘋，夜裡CookCook #

## 前言 ##

還記得在2007年時，賈伯斯宣布 ***「今天！蘋果重新發明了電話」*** 。
至此以後展開了『大愛瘋時代』，對許多人來說iphone就代表著創新、新潮的系列作品，風靡於各大果粉之間，也有人為[買iPhone 6s不惜一切到醫院賣腎](http://www.appledaily.com.tw/realtimenews/article/new/20150915/691838/)。

事實上創新就是等同改變，在講求推陳出新的iphone系列中，不乏許多頂尖的創新，屏除從jobs換成Tim Cook這個執行長改變以外。

![Tim Cook](http://123)

對我來說最劃時代的改變，就是在iphone7中採用無線耳機，並且取消了存在幾十年的傳統3.5mm標準耳機接口。
那你一定忍不住想問：那我的傳統3.55mm耳機，只能丟垃圾桶了嗎?
### 不! ###
你的心聲，還沒說出口，蘋果就都聽到了~

![mind](http://123)

你可以花NT$350到蘋果商店，購買
[Lightning 對 3.5 公釐耳機插孔轉接器
](https://www.apple.com/tw/shop/product/MMX62FE/A/lightning-%E5%B0%8D-35-%E5%85%AC%E9%87%90%E8%80%B3%E6%A9%9F%E6%8F%92%E5%AD%94%E8%BD%89%E6%8E%A5%E5%99%A8)

再搭配你的iphone7

### 就可以 ###

![AdapterIphone](http://123)

-

PS.至於無線耳機呢？

它並不是標準配備，需要額外加購，加購價:NT$5,490

[AirPods](https://www.apple.com/tw/shop/product/MMEF2TA/A/airpods?fnode=36a4f29340c054879ca1f90063ad61dfe7976a7103a6bd1f51b225bddf79c8d2d2f5c5c6128fc7b17d5aad2012e85b56f68284f5295b1642fab80004a83457cea7d18c2a650ab29cd3345fd542e6e15270151d32edc59b7b6218a604f64a366b43224ea3b3c63c44e6ffe5422a5cb72b942672f41900e7bfcfabf94006d9518e)


## 主角登場 ##

轉接器模式:將一個接口轉成另一個接口，提升相容性。

- 物件轉接器
- 類別轉接器

## 物件轉接器 ##

![images](http://123)

#### 過程: ####

1. 客戶透過目標介面呼叫轉接器方法，對轉接器發出要求。
2. 轉接器透過被轉接者介面，呼叫被轉接者的方法，將請求轉交給被轉接者。
3. 客戶接受到呼叫結果，但並未察覺這一切是透過轉接器居中聯繫。


#### 釐清角色： ####

男主角 TRSPlug飾演：平凡的插頭，他整天夢想要成為人生勝利組。
人生勝利組 Lightning飾演:IPHONE 7的標準插頭介面。
師傅 LightningAdapter飾演：男主角導師，也就是將TRSPlug變成人生勝利組 (Lightning)的形狀。

![adapterClass](http://123)

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

4. 