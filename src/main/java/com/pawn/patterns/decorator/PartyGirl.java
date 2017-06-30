package com.pawn.patterns.decorator;

import lombok.AllArgsConstructor;

/**
 * 被裝飾者
 * 參加宴會的女孩，都會精心裝扮自己，希望可以提高他人對於女孩的印象分數。
 */
@AllArgsConstructor
public abstract class PartyGirl {
    private String name = "unknown name.";
    private String description="unknown party girl.";
    private double impressionScore = 0;

    public PartyGirl() {
    }

    public double getImpressionScore() {
        return impressionScore;
    }

    public void setImpressionScore(double impressionScore) {
        this.impressionScore = impressionScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
