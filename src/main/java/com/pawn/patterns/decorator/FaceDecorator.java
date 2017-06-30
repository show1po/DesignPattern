package com.pawn.patterns.decorator;

/**
 * 裝飾者與被裝飾者必須是相同介面，才可將責任動態地加諸於物件上。
 * 不論是戴耳環的Terri、戴耳環畫眼影塗口紅的Terri ，都還是Party PartyGirl。
 */
public abstract class FaceDecorator extends PartyGirl {
    public abstract String getName();
    public abstract String getDescription();
    public abstract double getImpressionScore();
}
