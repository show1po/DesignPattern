package com.pawn.patterns.javalang;

/**
 * Created by albert on 2017/6/29.
 */
public class AbstractConstructorImpl extends AbstractConstructor {
    private String name;
    private String descrption;

    public AbstractConstructorImpl(String name, String description) {
        super(name, description);
        this.name = "son"+name;
        this.descrption =  "son"+description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }
}
