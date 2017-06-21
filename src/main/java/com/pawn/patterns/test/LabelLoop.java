package com.pawn.patterns.test;

/**
 * Created by Show on 2017/6/20.
 */
public class LabelLoop {
    public static void main(String[] args) {
        int i=0,j=0;
        String[][] exams = {{"a","b","c"}, {"A","B","C"}};
        OuterLoop:
        for(String[] array:exams) {
            System.out.println("\n外部迴圈轉起來");
            InnerLoop:
            for(String s:array) {
                System.out.printf("%s,",s);
                break OuterLoop;
            }
            System.out.println("\n外部迴圈轉一次");
//            break;
        }
    }
}
