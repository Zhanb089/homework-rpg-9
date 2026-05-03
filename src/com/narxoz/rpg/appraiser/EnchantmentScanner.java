package com.narxoz.rpg.appraiser;

import com.narxoz.rpg.artifact.*;

public class EnchantmentScanner implements ArtifactVisitor {
    private int magicalCount = 0;

    public int getMagicalCount() { return magicalCount; }

    @Override
    public void visit(Weapon weapon) {
        if (weapon.getAttackBonus() >= 10) {
            magicalCount++;
            System.out.println("  [EnchantmentScanner] " + weapon.getName()
                    + " glows — runic weapon (+" + weapon.getAttackBonus() + " ATK)");
        } else {
            System.out.println("  [EnchantmentScanner] " + weapon.getName()
                    + " is mundane steel");
        }
    }

    @Override
    public void visit(Potion potion) {
        magicalCount++;
        System.out.println("  [EnchantmentScanner] " + potion.getName()
                + " — alchemical brew (heals " + potion.getHealing() + ")");
    }

    @Override
    public void visit(Scroll scroll) {
        magicalCount++;
        System.out.println("  [EnchantmentScanner] " + scroll.getName()
                + " — spell signature: " + scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        magicalCount++;
        System.out.println("  [EnchantmentScanner] " + ring.getName()
                + " — enchantment level: " + ring.getMagicBonus());
    }

    @Override
    public void visit(Armor armor) {
        if (armor.getDefenseBonus() >= 8) {
            magicalCount++;
            System.out.println("  [EnchantmentScanner] " + armor.getName()
                    + " hums with wards (+" + armor.getDefenseBonus() + " DEF)");
        } else {
            System.out.println("  [EnchantmentScanner] " + armor.getName()
                    + " is plain craftsmanship");
        }
    }
}