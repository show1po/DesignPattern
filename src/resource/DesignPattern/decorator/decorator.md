# Decorator 裝飾者模式:滿分的女人我找不到，0分的女人都到處跑 #

## 前言 ##

標題是引用自黃立行2007年發行的《[打分數](打分數 "https://www.youtube.com/watch?v=kX-zOqQZaaI")》，歌曲直接了當點出，人與人之間相處都是互相評分，而評分標準則因人而異，大多都是受裝扮、個性、外貌所影響。由於評分變數太多不利於解釋，所以我們就取單一變化:裝扮來進行說明。

## 情境案例 ##

Terri終於18歲了，迫不及待地去參加同學們的成年趴，為了吸引趴體上大家的目光，需要精心的打扮，從臉部的裝飾品開始，從眼影、口紅、耳環、化妝品。

![Terri](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/decorator/terri.jpg)

### 裝飾者模式 ###

利用物件合成賦予新的責任，達成執行期能動態地裝飾類別。實現開放關閉守則。

* 裝飾者(FaceDecorator)和被裝飾者(PartyGirl)有相同的超類別，可再次包裝已裝飾的物件。
* 透過一個或多個裝飾品包裝一個物件。
* 裝飾者可以在所委派被裝飾者的行為之前/之後**加上自己行為**達到特定目的。
* 不論經過幾次包裝，都仍是被裝飾者女孩自己，本身元件。
* 裝飾者會導致出現多小類別，令程式過於複雜。

![class](https://raw.githubusercontent.com/show1po/DesignPattern/master/src/resource/DesignPattern/decorator/decorator.png)

### 一 介面(抽象類別) ###

~~~java

/**
 * <<被裝飾者介面>>
 * 參加宴會的女孩，都會精心裝扮自己，希望可以提高他人對於女孩的印象分數。
 */
@AllArgsConstructor
public abstract class PartyGirl {
    private String name = "unknown name.";
    private String description="unknown party girl.";
    private double impressionScore = 0;

    public PartyGirl() {
    }

    public double getImpressionScore() {
        return impressionScore;
    }

    public void setImpressionScore(double impressionScore) {
        this.impressionScore = impressionScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


/**
 * <<裝飾者介面>>
 * 裝飾者與被裝飾者必須是相同介面，才可將責任動態地加諸於物件上。
 * 不論是戴耳環的Terri、戴耳環畫眼影塗口紅的Terri ，都還是Party PartyGirl。
 */
public abstract class FaceDecorator extends PartyGirl {
    public abstract String getName();
    public abstract String getDescription();
    public abstract double getImpressionScore();
}

~~~

### 二 實作 ###

1. 被裝飾者的子類別

~~~java

//關穎想去參加趴體的女孩兒
public class Terri extends PartyGirl {
    public Terri(String name, String description, double impressionScore) {
        super(name, description,impressionScore);
    }
}
~~~

2. 裝飾者的子類別

~~~java

public class Earrings extends FaceDecorator {
    private final PartyGirl partyGirl;

    public Earrings(PartyGirl partyGirl) {
        this.partyGirl = partyGirl;
    }
    @Override
    public String getName() {
        return partyGirl.getName();
    }


    @Override
    public String getDescription() {
        return partyGirl.getDescription()+",戴上耳環";
    }

    @Override
    public double getImpressionScore() {
        return 10+ partyGirl.getImpressionScore();
    }
}

public class EyeShadow extends FaceDecorator {
    private final PartyGirl partyGirl;

    public EyeShadow(PartyGirl partyGirl) {
        this.partyGirl = partyGirl;
    }
    @Override
    public String getName() {
        return partyGirl.getName();
    }

    @Override
    public String getDescription() {
        return partyGirl.getDescription()+",抹上眼影";
    }

    @Override
    public double getImpressionScore() {
        return 10+ partyGirl.getImpressionScore();
    }
}
public class Lipstick extends FaceDecorator {
    private PartyGirl partyGirl;
    public Lipstick(PartyGirl partyGirl) {
        this.partyGirl = partyGirl;
    }

    @Override
    public String getName() {
        return partyGirl.getName();
    }

    @Override
    public String getDescription() {
        return partyGirl.getDescription()+",塗上紅脣";
    }

    @Override
    public double getImpressionScore() {
        return 10+ partyGirl.getImpressionScore();
    }
}

public class Makeup extends FaceDecorator {
    private PartyGirl partyGirl;
    public Makeup(PartyGirl partyGirl) {
        this.partyGirl = partyGirl;
    }

    @Override
    public String getDescription() {
        return partyGirl.getDescription()+",畫了淡妝";
    }
    @Override
    public String getName() {
        return partyGirl.getName();
    }


    @Override
    public double getImpressionScore() {
        return 10+ partyGirl.getImpressionScore();
    }
}

~~~

#### 單元測試 ####

~~~java
    @Test
    public void testPartyGirls() {
        PartyGirl terri=new Terri("關穎","一名要參加派對的女孩",50);
        System.out.println(terri.getName()+","+terri.getDescription()+",初始總分:"+terri.getImpressionScore());
        terri=new Earrings(terri);
        System.out.println(terri.getName()+","+terri.getDescription()+",印象總分:"+terri.getImpressionScore());
        terri=new EyeShadow(terri);
        System.out.println(terri.getName()+","+terri.getDescription()+",印象總分:"+terri.getImpressionScore());
        terri=new Lipstick(terri);
        System.out.println(terri.getName()+","+terri.getDescription()+",印象總分:"+terri.getImpressionScore());
        terri=new Makeup(terri);
        System.out.println(terri.getName()+","+terri.getDescription()+",印象總分:"+terri.getImpressionScore());
    }


~~~

	關穎,一名要參加派對的女孩,初始總分:50.0
	關穎,一名要參加派對的女孩,戴上耳環,印象總分:60.0
	關穎,一名要參加派對的女孩,戴上耳環,抹上眼影,印象總分:70.0
	關穎,一名要參加派對的女孩,戴上耳環,抹上眼影,塗上紅脣,印象總分:80.0
	關穎,一名要參加派對的女孩,戴上耳環,抹上眼影,塗上紅脣,畫了淡妝,印象總分:90.0