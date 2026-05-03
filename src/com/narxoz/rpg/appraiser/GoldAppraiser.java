package com.narxoz.rpg.appraiser;

import com.narxoz.rpg.artifact.*;

public class GoldAppraiser implements ArtifactVisitor {
    private int totalGold = 0;

    public int getTotalGold() { return totalGold; }

    @Override
    public void visit(Weapon weapon) {
        int worth = weapon.getValue() + weapon.getAttackBonus() * 5;
        totalGold += worth;
        System.out.println("  [GoldAppraiser] " + weapon.getName()
                + " — weapon valued at " + worth + " gp");
    }

    @Override
    public void visit(Potion potion) {
        int worth = potion.getValue();
        totalGold += worth;
        System.out.println("  [GoldAppraiser] " + potion.getName()
                + " — consumable, valued at " + worth + " gp");
    }

    @Override
    public void visit(Scroll scroll) {
        int worth = scroll.getValue() * 2;
        totalGold += worth;
        System.out.println("  [GoldAppraiser] " + scroll.getName()
                + " — rare scroll, valued at " + worth + " gp");
    }

    @Override
    public void visit(Ring ring) {
        int worth = ring.getValue() + ring.getMagicBonus() * 10;
        totalGold += worth;
        System.out.println("  [GoldAppraiser] " + ring.getName()
                + " — enchanted ring, valued at " + worth + " gp");
    }

    @Override
    public void visit(Armor armor) {
        int worth = armor.getValue() + armor.getDefenseBonus() * 4;
        totalGold += worth;
        System.out.println("  [GoldAppraiser] " + armor.getName()
                + " — armor, valued at " + worth + " gp");
    }
}