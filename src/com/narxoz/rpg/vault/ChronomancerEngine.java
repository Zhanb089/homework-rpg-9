package com.narxoz.rpg.vault;

import com.narxoz.rpg.appraiser.CurseDetector;
import com.narxoz.rpg.appraiser.EnchantmentScanner;
import com.narxoz.rpg.appraiser.GoldAppraiser;
import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import java.util.List;

public class ChronomancerEngine {

    public VaultRunResult runVault(List<Hero> party) {
        Caretaker caretaker = new Caretaker();
        int mementosCreated = 0;
        int restoredCount = 0;

        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println("  THE CHRONOMANCER'S VAULT — sealed door creaks open");
        System.out.println("=".repeat(60));

        Inventory vaultLoot = buildVaultLoot();
        System.out.println("Vault loot uncovered: " + vaultLoot.size() + " artifact(s)");

        System.out.println();
        System.out.println("--- PHASE 1: ARTIFACT APPRAISAL ---");

        GoldAppraiser gold     = new GoldAppraiser();
        EnchantmentScanner mag = new EnchantmentScanner();
        CurseDetector curse    = new CurseDetector();

        System.out.println(">> Applying GoldAppraiser:");
        vaultLoot.accept(gold);
        System.out.println(">> Applying EnchantmentScanner:");
        vaultLoot.accept(mag);
        System.out.println(">> Applying CurseDetector:");
        vaultLoot.accept(curse);

        int artifactsAppraised = vaultLoot.size() * 3;

        System.out.println();
        System.out.println("Appraisal summary: total worth " + gold.getTotalGold() + " gp, "
                + mag.getMagicalCount() + " magical, "
                + curse.getCursedCount() + " cursed");

        System.out.println();
        System.out.println("--- PHASE 2: TIME CRYSTAL SNAPSHOTS ---");

        int share = gold.getTotalGold() / Math.max(1, party.size());
        for (Hero h : party) {
            for (Artifact a : vaultLoot.getArtifacts()) {
                h.getInventory().addArtifact(a);
            }
            h.addGold(share);
        }

        for (int i = party.size() - 1; i >= 0; i--) {
            Hero h = party.get(i);
            caretaker.save(h.createMemento());
            mementosCreated++;
            System.out.println("  [Crystal] " + h.getName() + " etched into time "
                    + "(HP: " + h.getHp()
                    + ", Mana: " + h.getMana()
                    + ", Gold: " + h.getGold()
                    + ", Inventory: " + h.getInventory().size() + ")");
        }
        System.out.println("  Caretaker holds " + caretaker.size() + " snapshot(s).");

        System.out.println();
        System.out.println("--- PHASE 3: VAULT TRAP TRIGGERS ---");
        System.out.println("  *RUUMBLE* The Chronomancer's wards activate!");

        Hero victim = party.get(0);
        System.out.println("  " + victim.getName() + " is engulfed by a time-rending storm!");

        int beforeHp   = victim.getHp();
        int beforeMana = victim.getMana();
        int beforeGold = victim.getGold();
        int beforeInv  = victim.getInventory().size();

        victim.takeDamage(40);
        victim.spendMana(Math.min(20, victim.getMana()));
        victim.spendGold(Math.min(50, victim.getGold()));
        victim.setInventory(new Inventory()); // artifacts vaporized

        System.out.println("  " + victim.getName() + " ravaged: "
                + "HP " + beforeHp + " -> " + victim.getHp()
                + ", Mana " + beforeMana + " -> " + victim.getMana()
                + ", Gold " + beforeGold + " -> " + victim.getGold()
                + ", Inventory " + beforeInv + " -> " + victim.getInventory().size());

        System.out.println();
        System.out.println("--- PHASE 4: TIME CRYSTAL REWIND ---");
        System.out.println("  Caretaker peeks at top crystal...");
        HeroMemento top = caretaker.peek();
        if (top != null) {
            System.out.println("  Crystal found. Activating undo...");
            HeroMemento snap = caretaker.undo();
            victim.restoreFromMemento(snap);
            restoredCount++;
        } else {
            System.out.println("  No crystals available — rewind failed.");
        }
        System.out.println("  Caretaker now holds " + caretaker.size() + " snapshot(s).");

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }

    private Inventory buildVaultLoot() {
        Inventory loot = new Inventory();
        loot.addArtifact(new Weapon("Sunsteel Blade",     120, 6, 18));
        loot.addArtifact(new Weapon("Bloodthirst Axe",    200, 12, 30));
        loot.addArtifact(new Potion("Greater Healing",     40, 1, 50));
        loot.addArtifact(new Potion("Shimmering Vial",    400, 1, 5));   
        loot.addArtifact(new Scroll("Forbidden Tome",     180, 2, "Necrotic Bind")); 
        loot.addArtifact(new Ring  ("Mage's Signet",       90, 0, 7));
        loot.addArtifact(new Armor ("Dragonscale Plate",  220, 35, 12));
        return loot;
    }
}