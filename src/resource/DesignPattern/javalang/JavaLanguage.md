# Java #
## 目錄 ##
1. 語法基礎篇
	* 基本資料型態與參考資料型別
	* String建立與操作:格式化(%b,%c,%d,%f and %s)
	* 運算子優先順序
	* 字串比較使用 == 與 equals() 
	* 存取權限:private/default(無修飾字)/protected/public
	* 覆寫與過載
	* 覆寫equals()與hashCode()
	* 關鍵字static/final
2. 流程控制篇
3. 物件導向篇
	* 多型架構下型別轉換(casting)
	* super/this
	* is-a/has-a
	* 設計模式:Singleton/Dao/Factory
4. 例外處理篇
	* throw/throws
	* try-with-resources自動關閉資源語法
5. 多執行序篇
	* 同步並行Concurrency:Executors與ThreadPools/Concurrent集合
6. JVM資源回收機制篇
7. Collection架構與泛型篇
	* Diamond語法
	* 外包類別:autoboxing/auto-unboxing
	* Comparator/Comparable
8. I/O 輸入輸出篇
	* streams串流讀取檔案
	* File I/O:WatchService監控目錄下資源
9. 實用API
	* JDBC連接資料庫/交易模式(Auto-commit/rolling back)

---
## 語法基礎篇 ##
1. 宣告順序:package->import->class
2. 存取修飾詞Modifier，宣告類別、屬性與方法(含建構子)的存取權限。

|Modifier |同class  |同package|子類別|不同package|
|---------|---------|---------|-----|-----------|
|private  | yes     | -|-|-|
|default  | yes     |yes|-|-|     
|protected| yes     | yes|yes|yes(要繼承)|
|public   | yes     | yes|yes|yes|
3. 變數位於記憶體中狀況:
 * Global:儲存static類別成員變數。
 * Stack:primitive type/物件變數參考內容值，利用指標來回移動指向變數。
 * Heap:Reference Type的物件實體。
 * new 參考資料型別的物件實體時物件參考值會存在STACK，物件實體則會存在HEAP。
4. 基本資料型別(PrimitiveType):類別和物件變數系統給初始值，區域變數則不會。	
 *  字元char(unicode:'\u0000')
 *  整數byte,shot,int(0),long(0L)
 *  浮點數float(0.0F),double(0.0D/0.0)
 *  布林值boolean(false)
----
- char:'',String:"".
- \r:return,enter.\n:斷行.\b:倒退.
----
5. 算術運算子:
	- ++遞增:
		- 前序遞增:x=0,++x;表示x先+1之後再指x，結果x=1;
		- 後序遞增:x=0,x++;x會先指給x後才會執行++動作，x為0.
	- --遞減:
		- 前序遞減
		- 後序遞減
		
`Q:int a = 0;int b=0;b = a++ + ++a - a++;求a,b?`

~~~java

	a= 0(1) + (2)2 -2(3);//括號中為a當前階段值，最後括號則為最後值。
	
	b= 0+ 2-2=0

	ans:a=3,b=0.
~~~

	- 優先順序:&>|>&&>|| 
	- &(logic):a&b,ab都會運算. vs &&(短路):a=false,會跳掉b.
        
----
## 流程控制篇 ##
1. Loop:continue:強制執行下個判斷,break跳離迴圈,label聲明.

~~~java
  public static void main(String[] args) {
        int i=0,j=0;
        String[][] exams = {{"a","b","c"}, {"A","B","C"}};
        OuterLoop://label:外部迴圈
        for(String[] array:exams) {
            InnerLoop://label:內部迴圈
            for(String s:array) {
                System.out.printf("%s,",s);
                continue OuterLoop;//繼續至外部迴圈
            }
            break;
        }
    }
~~~

2. 預設建構子:
	* 若無定義，編譯時期自動加入 預設建構子。
	* 預設建構子存取權限與類別相同。
	* 預設無參數
	* 繼承super();
3. 方法多個參數位置:
	* void calc(int x,int... c) ok 省略號放置最後參數
	* void calc(int... x,int c) error
	* void calc(int... x,int... c) error >最多一個省略號

4. primitive wrapper classes:將基本資料型別物件化，所建立內容和字串相同，永不改變(immutable)特性，除非重new。

5. Autoboxing/unboxing:編輯器語法糖果 Integer x=100 ,int y=x;
 - 陷阱題:

~~~java
	Integer x=200;//Integer採取快取機制127以下會自動參照先前統一建立heap實體
	Integer y=200;
	x==y; //false
	x.equals(y);//true
	
~~~

