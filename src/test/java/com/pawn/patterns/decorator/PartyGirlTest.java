package com.pawn.patterns.decorator;

import org.junit.Test;

/**
 * Created by Show on 2017/6/29.
 */
public class PartyGirlTest {
    @Test
    public void testPartyGirls() {
        PartyGirl terri=new Terri("關穎","一名要參加派對的女孩",50);
        System.out.println(terri.getName()+","+terri.getDescription()+",初始總分:"+terri.getImpressionScore());
        terri=new Earrings(terri);
        System.out.println(terri.getName()+","+terri.getDescription()+",印象總分:"+terri.getImpressionScore());
        terri=new EyeShadow(terri);
        System.out.println(terri.getName()+","+terri.getDescription()+",印象總分:"+terri.getImpressionScore());
        terri=new Lipstick(terri);
        System.out.println(terri.getName()+","+terri.getDescription()+",印象總分:"+terri.getImpressionScore());
        terri=new Makeup(terri);
        System.out.println(terri.getName()+","+terri.getDescription()+",印象總分:"+terri.getImpressionScore());
    }

}