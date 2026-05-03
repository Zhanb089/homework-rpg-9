package com.narxoz.rpg.appraiser;

import com.narxoz.rpg.artifact.*;

public class WeightCalculator implements ArtifactVisitor {
    private int totalWeight = 0;

    public int getTotalWeight() { return totalWeight; }

    @Override
    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
        System.out.println("  [WeightCalculator] " + weapon.getName()
                + " <weapon> — " + weapon.getWeight() + " kg");
    }

    @Override
    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
        System.out.println("  [WeightCalculator] " + potion.getName()
                + " <potion> — " + potion.getWeight() + " kg");
    }

    @Override
    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
        System.out.println("  [WeightCalculator] " + scroll.getName()
                + " <scroll> — " + scroll.getWeight() + " kg");
    }

    @Override
    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
        System.out.println("  [WeightCalculator] " + ring.getName()
                + " <ring> — " + ring.getWeight() + " kg");
    }

    @Override
    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
        System.out.println("  [WeightCalculator] " + armor.getName()
                + " <armor> — " + armor.getWeight() + " kg");
    }
}