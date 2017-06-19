package com.pawn.patterns.singleton;

/**
 * Created by Show on 2017/6/19.
 */
public class DoubleCheckedLockingVersion {
    //volatile:JDK 5以上增強其語義，避免編譯器為了優化，自動將執行順序進行重排序。
    /**為什麼 instance 該加入volatile block?
     *  創立物件的過程如下:
     * 1. memory = allocate();   分配物件的記憶體空間
     * 2. instance(memory);     初始化物件
     * 3. instance = memory;    設置instance指向到剛分配位址
     * 4. 初次訪問物件
     * 由於2.3步驟可能因為編譯器優化，而導致顛倒順序，造成多執行緒引用時產生問題:
     * 首先執行緒 A 建立instance 途中，執行緒 B也要調用Singleton.class:
     * 執行緒 A : A1 分配物件的記憶體空間 - A3 instance指向到剛分配位址 - A2 初始化物件 (顛倒步驟1-3-2)
     * 執行緒 B :  當A執行到A3時，判斷instance不為空，則B將訪問instance尚未初始化的物件，造成問題。
     */
    private volatile static DoubleCheckedLockingVersion instance;

    private DoubleCheckedLockingVersion() {
    }

    public static DoubleCheckedLockingVersion getInstance() {
        //第一次檢查，減少因為同步synchronized block，所要等待的時間。
        if (instance == null) {
            //使用synchronized block 確保多執行緒安全。
            synchronized (DoubleCheckedLockingVersion.class) {
                //第二次檢查
                if (instance == null) {
                    instance=new DoubleCheckedLockingVersion();
                }
            }
        }
        return instance;
    }

}
