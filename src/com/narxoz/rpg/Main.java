package com.narxoz.rpg;

import com.narxoz.rpg.appraiser.WeightCalculator;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  HOMEWORK 9 — CHRONOMANCER'S VAULT");
        System.out.println("  Visitor + Memento");
        System.out.println("=".repeat(60));

        Inventory zhanibekStart = new Inventory();
        zhanibekStart.addArtifact(new Weapon("Iron Sword", 50, 8, 12));

        Hero zhanibek = new Hero("Zhanibek", 120, 30, 22, 14, 50, zhanibekStart);
        Hero elara    = new Hero("Elara",     90, 60, 16, 11, 80, new Inventory());
        List<Hero> party = Arrays.asList(zhanibek, elara);

        System.out.println();
        System.out.println("  PARTY ENTERS THE VAULT");
        for (Hero h : party) {
            System.out.println("    - " + h.getName()
                    + " | HP: " + h.getHp() + "/" + h.getMaxHp()
                    + " | Mana: " + h.getMana()
                    + " | Gold: " + h.getGold()
                    + " | Inventory: " + h.getInventory().size());
        }

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(party);

        System.out.println();
        System.out.println("--- OPEN/CLOSED PROOF: 4TH VISITOR (WeightCalculator) ---");
        System.out.println("Re-walking the survivor's inventory with a brand-new visitor:");
        WeightCalculator weights = new WeightCalculator();
        party.get(1).getInventory().accept(weights);
        System.out.println("  Total carry weight: " + weights.getTotalWeight() + " kg");

        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println("  FINAL PARTY STATE");
        System.out.println("=".repeat(60));
        for (Hero h : party) {
            System.out.println("  - " + h.getName()
                    + " | HP: " + h.getHp() + "/" + h.getMaxHp()
                    + " | Mana: " + h.getMana()
                    + " | Gold: " + h.getGold()
                    + " | Inventory: " + h.getInventory().size()
                    + " | " + (h.isAlive() ? "ALIVE" : "FALLEN"));
        }

        System.out.println();
        System.out.println("====== VAULT RUN RESULT ======");
        System.out.println("  " + result);
    }
}