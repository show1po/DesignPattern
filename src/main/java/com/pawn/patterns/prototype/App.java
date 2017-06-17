package com.pawn.patterns.prototype;

/**
 * Created by Show on 2017/6/17.
 */
public class App {
    public static void main(String[] args) {

        Class major= new Class("資訊管理系一年級",1,"資訊管理系");
        Student prototypeA=new Student("mary",22,"female",major,"野雞科技大學");
//        Student prototypeB=new Student("mary",22,"female","資訊管理","野雞科技大學");
//        prototypeA.setName("Tom");
        Student cloneA = prototypeA.clone();
        Student cloneB = prototypeA.clone();
        Student cloneC =cloneB.clone();
        major.setGrade(2);
        System.out.println(cloneA);
        System.out.println(cloneB);
        System.out.println(cloneC);
        System.out.println(cloneA.getName()==prototypeA.getName());
    }
}
