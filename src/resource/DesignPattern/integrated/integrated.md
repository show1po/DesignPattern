# 設計模式：爭什麼？參在一起做成撒尿牛丸啊！笨蛋！  #
## 前言 ##

## Creational Patterns 建立模式 ##

如何有效率的產生、管理 與操作物件，一直都是值得討論的課題， Creational 模式即與物件的建立相關，在這個分類下的模式給出了一些指導原則及設計的方向。

|  | 要點 | 相關應用 |
| :-| :- | :- |
| simple factory 簡單工廠模式| 短文本 | DateFormat,getTimeInstance |
| factory method 工廠方法模式| 短文本 | Iterator,URL與URLConnection |
| abstract factory 抽象工廠模式| 中等文本 | 窗體控件 |
| builder 建造者模式| 將複雜物件的建構與表示分離 ，使得同樣的建構過程可以創建不同表示| Java Mail |
| prototype 雛形模式| clone原型，創建新的物件 | 實現Cloneable的類 |
| singleton 單例模式| 確保一個類別只有一個實例，並向整個系統提供這個實例 | Runtime |


## Structual Patterns 結構模式 ##

如何設計物件之間 的靜態結構，如何完成物件之間的繼承、實現與依賴關係，這關乎著系統設計出來是否健壯（robust）：像是易懂、易維護、易修改、耦合度低等等議題。Structural 模式正如其名，其分類下的模式給出了在不同場合下所適用的各種物件關係結構。

|  | 要點 | 相關應用 |
| :----| :---- | :---- |
| adapter 轉接器模式|將不相容的介面，進行介面轉換成相容。 | DAO轉接、Cache功能轉接。類別轉接器(須多重繼承)、物件轉接器 |
| decorator 裝飾者模式| 透過合成在執行期動態地，將責任加於物件中。 | Java I/O類庫設計 |
| composite 合成模式| 將物件合成樹狀結構，呈現「部分/整體」，供客戶端一致的方式處理。 | 分類樹、權限樹 |
| proxy 代理人模式| 控制且管理物件存取。 | WebService的本地代理、權限訪問代理、引用計數代理 |
| flyweight 享元模式| 令類別的單一實體，能提供許多虛擬實體，缺點:不可獨立擁有其他行為。 | Java String、編譯器系統 |
| facade 表象模式| 包裝多個類別，並簡化介面，將客戶從元件的次系統中鬆綁出來。 | SOA框架編成中，不同系統之間接口|
| bridge 橋接模式| 將介面與實踐鬆綁，抽象和實踐可各自擴充不會影響對方。缺點:增加複雜度 | 跨平台的驅動器，如:JDBC Driver |

## Behavioral Patterns 行為模式： ##

物件之間的合作行為構成了程式最終的行為，物件之間若有設計良好的行為互動，不僅使得程式執行時更有效率，更可以讓物件的職責更為清晰、整個程式的動態結構（像是物件調度）更有彈性。

|  | 要點 | 相關應用 |
| :-| :- | :- |
| Chain of Responsibility 責任鏈模式| 中等文本 | |
| Command 命令模式| 短文本 | |
| Iterator 疊代器模式| 短文本 | |
| Strategy 策略模式| 短文本 | |
| Template Method 樣板模式| 短文本 |  |
| Observer 觀察者模式| 短文本 ||
| Mediator 中介者模式| 短文本 | |
| State 狀態模式| 短文本 |  |
| Memento 備忘錄模式| 短文本 | |
| Visitor 訪問者模式| 短文本 |  |
| Interpreter 解譯器模式| 短文本 |  |


關心資料結構和程式碼之間關係，從三個角度對軟體描述單例/靜態、線程安全、有無狀態。
有狀態：就是有儲存資料功能。
無狀態：屬功能操作、物件是線程安全的。

## 模式彼此之間差異 ##

1. Adapter pattern vs Decorator vs Facade pattern ?

使用的目的不同：

- Adapter   :包裝物件以改變介面，符合客戶端期望。
- Decorator :包裝物件動態增加新的責任。
- Facade    :提供次系統一個簡化的統一介面。


2. Strategy pattern vs Template pattern ?

都是用來封裝演算法：

- Strategy :僅封裝演算法，採用合成。委任模型，且客戶端執行期可改變演算法。
- Template :封裝、控制演算法，採用繼承。定義演算法的大綱，由次類別定義某步驟內容，則架構維持不變，但可有不同實踐細節。

3. Bridge pattern vs Strategy pattern ?

- Bridge :設計編譯時期結構。
- Strategy :設計執行時期行為。

4. 

## 複合模式 ##

結合兩個以上的模式，所組成的新解決方案，且可解決一再發生的一般性問題。

### Model-View-Controller (MVC) ###

MVC模式結合了四種模式：

- 轉接器模式: 將 Model 轉接成其他 Model，去符合某個既有的 View and Controller 。
- 合成模式: View 使用合成模式將許多使用者介面元件組織起來。
- 觀察者模式: Model 讓觀察者保持對 Model 狀態掌控，且依然鬆綁。
- 策略模式: Controller 封裝各種演算法，提供 View 依照不同需求調用。

