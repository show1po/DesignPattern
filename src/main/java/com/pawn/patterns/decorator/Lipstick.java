package com.pawn.patterns.decorator;

/**
 * Created by Show on 2017/6/29.
 */
public class Lipstick extends FaceDecorator {
    private PartyGirl partyGirl;
    public Lipstick(PartyGirl partyGirl) {
        this.partyGirl = partyGirl;
    }

    @Override
    public String getName() {
        return partyGirl.getName();
    }

    @Override
    public String getDescription() {
        return partyGirl.getDescription()+",塗上紅脣";
    }

    @Override
    public double getImpressionScore() {
        return 10+ partyGirl.getImpressionScore();
    }
}
