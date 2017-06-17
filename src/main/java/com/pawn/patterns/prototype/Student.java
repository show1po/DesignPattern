package com.pawn.patterns.prototype;

import lombok.Data;

/**
 * Created by Show on 2017/6/17.
 */
@Data
public class Student implements Cloneable {
    private String name;
    private int age;
    private String sex;
    private Class major;
    private String university;

    public Student(String name, int age, String sex, Class aClass, String university) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.major = aClass;
        this.university = university;
        System.out.println("run constructor.");
    }
    public Student clone()
    {
        Student cpStudent=null;
        try
        {
            cpStudent=(Student)super.clone();
        }
        catch(CloneNotSupportedException e)
        {
        }
        return cpStudent;
    }
}
