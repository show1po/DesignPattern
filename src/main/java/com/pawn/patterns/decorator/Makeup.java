package com.pawn.patterns.decorator;

/**
 * Created by Show on 2017/6/29.
 */
public class Makeup extends FaceDecorator {
    private PartyGirl partyGirl;
    public Makeup(PartyGirl partyGirl) {
        this.partyGirl = partyGirl;
    }

    @Override
    public String getDescription() {
        return partyGirl.getDescription()+",畫了淡妝";
    }
    @Override
    public String getName() {
        return partyGirl.getName();
    }


    @Override
    public double getImpressionScore() {
        return 10+ partyGirl.getImpressionScore();
    }
}
