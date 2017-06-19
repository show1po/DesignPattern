package com.pawn.patterns.prototype;

/**
 * Created by Show on 2017/6/17.
 */
public class PrototypeApp {
    public static void main(String[] args) {

        Class major= new Class("資訊管理系一年級",1,"資訊管理系");
        Student prototypeA=new Student("mary",22,"female",major,"野雞科技大學");
        Student cloneA = prototypeA.clone();
        System.out.println("原型:"+prototypeA);
        System.out.println("複製:"+cloneA);
        System.out.println("原型/複製 是否相同物件:"+(prototypeA==cloneA));

        System.out.println("\n原型類別中的欄位值是Class型態，且無實作cloneable，因而屬於淺層複製。只是引用，所以當major改變時:");
        major.setGrade(2);
        System.out.println("原型:"+prototypeA);
        System.out.println("複製:"+cloneA);

    }
}
