# Multi-Thread #
## 前言 ##
在多執行緒環境執行底下，約略會有以下問題發生：

* **Resource Sharing**
* **Deadlocks, Livelocks and starvation**
* **Race Condition**
* **Java Thread**
 -	synchronized
 -	Thread Safe

## Starvation ,Deadlocks and livelocks ##

### Starvation ###

當程序因長期無法完成工作，取得所需的全部資源，形成自身無限期停滯。
eq.常發生於unfair、preemptive環境底下

**Solution**:將長期停滯未完工的程序提升其優先權，使其提升取得所需資源機會。

----------

### Deadlocks ###
多執行緒各自持有對方要完成作業的部分資源，造成彼此等待對方的狀況。

Deadlock 成立的四個必要條件：

* Mutual exclution 互斥:資源同一時間內，只允許單一程序使用。其他程序只能等待該程序釋放資源。
* Hold & Wait Partial Allocation:程序持有部分資源，並同時等待其他程序所持有的資源。
* No preemption:程序不可搶奪其他等待程序所持有資源，除非其自願釋放。
* Circular waiting 循環等待

### 案例 ###

當有A、B兩物件，

若 thread a 執行順序為 lock objA -> objB

   thread b 執行順序為 lock objB -> objA

，則前後執行順序不一致導致Deadlock。
設計上應當統一順序，**前後關係一致**。

----------

### Livelocks ###



----------
### Race Condition ###

**源自於兩個訊號試著彼此競爭，來影響誰先輸出。**

描述程序的輸出，受限於不受控制的事件出現順序影響。
若兩個程序同時試圖修改共享記憶體內容，且無並行控制，導致結果依賴於兩個程序的執行順序和時機。

#### Code: ####

通常發生於多執行緒索取共同資源，且該資源無建立同步機制(synchronized)保護。
兩執行緒分別調用RaceCondition#getPlusOneResult().

i++是先給值再+，++i是先加再給值

~~~java
public class RaceCondition {
    
	private int count;

    public int getPlusOneResult() {
        return count++;
    }
}
~~~

### 執行結果 ###

執行緒所取得count值，會重複出現。
~~~

Thread A:0
Thread B:1
Thread B:2
Thread A:2
Thread B:3
Thread A:3
Thread B:4
Thread A:4
Thread B:5
Thread A:6

~~~

### 解決方法: ###

在java語言中可在類別、方法、區塊中添加synchronized關鍵字來解決問題。

~~~

 public synchronized int getSyncPlusOneResult() {
        return count++;
    }

~~~

### 結果 ###

~~~
Thread B:1
Thread A:0
Thread B:2
Thread A:3
Thread B:4
Thread A:5
Thread B:6
Thread A:7
Thread B:8
Thread A:9
Thread B:10
~~~


----------
	
## ThreadSafe ##

多執行緒環境下不會有race condition，我們可以稱此class或method為thread safe。此時你可以很放心的在多執行緒環境，操作這些resource而不用再包synchronized。

可以透過Collections中的很多helper methods來提供。例如

~~~
syncedCol = Collections.synchronizedCollection(myCol);
syncedList = Collections.synchronizedList(myList);
syncedSet = Collections.synchronizedSet(mySet);
syncedMap = Collections.synchronizedMap(myMap);
~~~

則可以把原本的non-thread-safe的容器包成thread safe的容器。

## Asynchronous ##

### Sync ###

同步執行：多個函式交由同一執行緒執行，執行順序則是逐一完成。

### Async ###
非同步執行：將多個函式交由不同執行緒執行，無須逐一等待回傳值。

Java語言中

* Future
* CompletableFuture

## Future ##

非同步呼叫的回傳結果，此結果將在未來某一個時間點取得。
是介面。
如：將衣服交給洗衣機清洗，則我們會預期40分鐘後洗完衣服的結果(Future)。

~~~java

public class LaundaryService {
    private ExecutorService executorService = /*...*/;

    public Future<Clothes> serviceAsync(Clothes dirtyClothes) {
        return executorService.submit(()-> service(dirtyClothes));
    }

    public Clothes service(Clothes dirtyClothes) {
        return dirtyClothes.wash();
    }
}

Future<Clothes> future = laundryService.serviceAsync(myClothes);

~~~

而取得預期結果 Future後，在等待清洗的40分鐘內，我們可以選擇block等待清洗完畢，或者去做其他事情。

~~~
// block until result is available
Clothes clothes = future.get();
~~~

## CompletableFuture ##

java8支援lambdas函式庫，具備Functional Language裡Monad的精神，函式可以接二連三的串接並接續進行的概念。

CompletableFuture是一個Future的實作，分為Completable四個特性來討論

1. Completable
2. Listenable
3. Composible
4. Combinable


----------
### 1. Completable ###

對於caller看到就是future，對於callee就是看到promise。

而CompletableFuture就同時扮演了Future跟Promise兩種角色。

所以CompletableFuture會被下面這樣使用
在非同步呼叫時，會先產生一個CompletableFuture，並且回傳給caller
這個CompletableFuture會連同async task一起傳到worker thread中。
當執行完這個async task，callee會呼叫CompletableFuture的complete()
此時caller可以透過CompletableFuture的get()取得結果的值。

**在Google的Guava library中也可以看到completable的蹤影，那就是SettableFuture。**

----------

### 2. Listenable ###

Future只提供了一個pulling result的方法，更多時候我們想要的是好了叫我這種語意。因此Listenable的特性，就是我們可以註冊一個callback，讓我可以listen執行完成的event。
在CompletableFuture主要是透過whenComplete()跟handle()這兩個method。

**同樣在Guava library中也可以看到listenable的蹤影，那就是ListenableFuture。**

----------

### 3. Composible ###

有了Listenable的特性之後，我們就可以做到當完成時，在做下一件事情。如果接下來又是一個非同步的工作，那就可能會串成非常多層，我們稱之為callback hell。

~~~java
public static void main(String[] args) throws InterruptedException {
    CompletableFuture<Void> future = 
    CompletableFuture
    .runAsync(() -> sleep(1000))
    .whenComplete((result, throwable) -> {
        if (throwable != null) {
            return;
        }

        CompletableFuture
        .runAsync(() -> sleep(1000))
        .whenComplete((result2, throwable2) -> {
            if (throwable2 != null) {
                return;
            }

            CompletableFuture
            .runAsync(() -> sleep(1000))
            .whenComplete((result3, throwable3) -> {
                if (throwable2 != null) {
                    return;
                }

                System.out.println("Done");
            });
        });
    });
~~~

這個程式碼這樣三層可能已經受不了了，如果更多層應該會有噁心的感覺。這還不打緊，如果再加上錯誤處理，那可能更是暈頭轉向。
對於這種一連串的invocation，如果可以把這些async function組起來，變成一個單一future，可能會舒服許多。先來看最後的結果，我們再來討論細節。

~~~java

CompletableFuture
.runAsync(() -> sleep(1000))
.thenRunAsync(() -> sleep(1000))
.thenRunAsync(() -> sleep(1000))
.whenComplete((r, ex) -> System.out.println("done"));

~~~
他們都有一個特性，就是把原本某個CompletableFuture的type parameter，經過一個transformer後，轉成另外一個Type的CompletableFuture，這就是Monad中的map。而最後一個因為他的回傳值本來就是CompletableFuture，這種轉換我們稱之為flatmap。其實同樣的概念在Optional API跟Stream API都找得到

**同樣在guava library中，我們可以看到composible的蹤影，他是放在Futures下面的transformXXX()相關的methods。**

----------

### 4. Combinable ###

async的流程有些時候不會是單一條路的，有時候更像是DAG(Directed Acyclic Graph)。例如做一個爬蟲程式(Crawler)，我們排一個文章的時候，可能會抓到很多個外部鏈結，這時候就會繼續展開更多非同步的task。等到到了某個停止條件，我們就要等所有爬蟲的task完成，最終等於執行完這個大的async task。
這時候我們會希望把多個future完成時當作一個future的complete，這就是combinable的概念。跟composible的概念不同的是，composible是一個串一個，比較像是串連的感覺；相對的combinable，就比較像是並聯。
CompletableFuture還有提供兩個static methods來做combine多個futures。

Method	Description
allOf(...)	回傳一個future，其中所有的future都完成此future才算完成。
anyOf(...)	回傳一個future，其中任何一個future完成則此future就算完成。

----------


### 感謝 ###

[Java多執行緒的基本知識](Java多執行緒的基本知識 "https://popcornylu.gitbooks.io/java_multithread/content/sync/message_passing.html")