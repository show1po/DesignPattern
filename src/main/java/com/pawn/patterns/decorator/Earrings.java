package com.pawn.patterns.decorator;

/**
 * Created by Show on 2017/6/29.
 */
public class Earrings extends FaceDecorator {
    private final PartyGirl partyGirl;

    public Earrings(PartyGirl partyGirl) {
        this.partyGirl = partyGirl;
    }
    @Override
    public String getName() {
        return partyGirl.getName();
    }


    @Override
    public String getDescription() {
        return partyGirl.getDescription()+",戴上耳環";
    }

    @Override
    public double getImpressionScore() {
        return 10+ partyGirl.getImpressionScore();
    }
}
