package com.pawn.patterns.decorator;

/**
 * Created by Show on 2017/6/29.
 */
public class EyeShadow extends FaceDecorator {
    private final PartyGirl partyGirl;

    public EyeShadow(PartyGirl partyGirl) {
        this.partyGirl = partyGirl;
    }
    @Override
    public String getName() {
        return partyGirl.getName();
    }

    @Override
    public String getDescription() {
        return partyGirl.getDescription()+",抹上眼影";
    }

    @Override
    public double getImpressionScore() {
        return 10+ partyGirl.getImpressionScore();
    }
}
