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

### 感謝 ###

[Java多執行緒的基本知識](Java多執行緒的基本知識 "https://popcornylu.gitbooks.io/java_multithread/content/sync/message_passing.html")