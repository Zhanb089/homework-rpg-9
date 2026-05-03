package com.narxoz.rpg.appraiser;

import com.narxoz.rpg.artifact.*;

public class CurseDetector implements ArtifactVisitor {
    private int cursedCount = 0;

    public int getCursedCount() { return cursedCount; }

    @Override
    public void visit(Weapon weapon) {
        if (weapon.getAttackBonus() > 25) {
            cursedCount++;
            System.out.println("  [CurseDetector] !! " + weapon.getName()
                    + " is BLOODTHIRSTY — drinks the wielder's vitality");
        } else {
            System.out.println("  [CurseDetector] " + weapon.getName() + " — clean");
        }
    }

    @Override
    public void visit(Potion potion) {
        if (potion.getValue() > potion.getHealing() * 8) {
            cursedCount++;
            System.out.println("  [CurseDetector] !! " + potion.getName()
                    + " is suspiciously overpriced — possibly tainted");
        } else {
            System.out.println("  [CurseDetector] " + potion.getName() + " — clean");
        }
    }

    @Override
    public void visit(Scroll scroll) {
        String n = scroll.getName().toLowerCase();
        String s = scroll.getSpellName().toLowerCase();
        if (n.contains("forbidden") || s.contains("necro") || s.contains("doom")) {
            cursedCount++;
            System.out.println("  [CurseDetector] !! " + scroll.getName()
                    + " — FORBIDDEN script detected (" + scroll.getSpellName() + ")");
        } else {
            System.out.println("  [CurseDetector] " + scroll.getName() + " — clean");
        }
    }

    @Override
    public void visit(Ring ring) {
        if (ring.getMagicBonus() < 0) {
            cursedCount++;
            System.out.println("  [CurseDetector] !! " + ring.getName()
                    + " — NEGATIVE enchantment, do not wear");
        } else {
            System.out.println("  [CurseDetector] " + ring.getName() + " — clean");
        }
    }

    @Override
    public void visit(Armor armor) {
        if (armor.getWeight() > 40) {
            cursedCount++;
            System.out.println("  [CurseDetector] !! " + armor.getName()
                    + " — bound by a weight curse, sluggish");
        } else {
            System.out.println("  [CurseDetector] " + armor.getName() + " — clean");
        }
    }
}