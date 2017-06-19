package com.pawn.patterns.adapter;

/**
 * Created by Show on 2017/6/19.
 */
public class LightningAdapter implements Lightning {
    private TRSPlug TRSPlug;

    public LightningAdapter(TRSPlug TRSPlug) {
        this.TRSPlug = TRSPlug;
    }

    public String insert() {
        return TRSPlug.insert()+" convert to  Lightning插頭.";
    }
}
