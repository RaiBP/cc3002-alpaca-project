package model.combat;

import model.items.IEquipableItem;
import model.items.weapons.Axe;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestFighterCombat extends AbstractTestUnit {
    private Fighter fighter;

    @Test
    public void testUseOnAllUnitsArcher() {
        Axe otherAxe = new Axe("Axe", 10, 1, 2);
        testUseOnAllUnits(otherAxe, 50, 50 - otherAxe.getPower());
    }

    @Test
    public void testUseOnLowHPFighter() {
        Axe otherAxe = new Axe("Axe", 50, 1, 2);
        testUseOnLowHPAllUnits(otherAxe);
    }

    @Test
    public void testUseOnOutOfRangeFighter() {
        Axe otherAxe = new Axe("Axe", 50, 1, 2);
        testUseOnOutOfRangeTargetAllUnits(otherAxe);
    }

    public void testCombatUnequipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat, int targetHpAfterCombat) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, fighter.getCurrentHitPoints());

        assertNull(fighter.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        fighter.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, fighter.getCurrentHitPoints());

        fighter.equipItem(axe);

        assertEquals(axe, fighter.getEquippedItem());

        fighter.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, fighter.getCurrentHitPoints());

    }

    public void testCombatEquipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat,
                                   int targetHpAfterCombat, IEquipableItem targetItem) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, fighter.getCurrentHitPoints());

        assertNull(fighter.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        fighter.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, fighter.getCurrentHitPoints());

        fighter.equipItem(axe);
        targetUnit.equipItem(targetItem);

        assertEquals(axe, fighter.getEquippedItem());

        fighter.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, fighter.getCurrentHitPoints());

    }

    @Test
    public void testCombatHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, heroTarget, 50, 50 - axe.getPower());
    }

    @Test
    public void testCombatHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - Math.max(0, spear.getPower() - 20), heroTarget, 50, 50 - (int) (1.5 * axe.getPower()), spear);
    }

    @Test
    public void testCombatSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, swordMasterTarget, 50, 50 - axe.getPower());
    }

    @Test
    public void testCombatSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * sword.getPower()), swordMasterTarget, 50, 50 - Math.max(0, axe.getPower() - 20), sword);
    }

    @Test
    public void testCombatArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, archerTarget, 50, 50 - axe.getPower());
    }

    @Test
    public void testCombatArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - bow.getPower(), archerTarget, 50, 50 - axe.getPower(), bow);
    }

    @Test
    public void testCombatFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, fighterTarget, 50, 50 - axe.getPower());
    }

    @Test
    public void testCombatFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        Axe otherAxe = new Axe("Axe", 10, 1, 2);
        testCombatEquipped(50 - otherAxe.getPower(), fighterTarget, 50, 50 - axe.getPower(), otherAxe);
    }

    @Test
    public void testCombatClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, clericTarget, 50, 50 - axe.getPower());
    }

    @Test
    public void testCombatCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, clericTarget, 50, 50 - axe.getPower(), staff);
    }

    @Test
    public void testCombatAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50 - axe.getPower());
    }

    @Test
    public void testCombatAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped( (int) (50 - 1.5 * anima.getPower()), sorcererTarget, 50, (int) (50 - 1.5 * axe.getPower()), anima);
    }

    @Test
    public void testCombatDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50 - axe.getPower());
    }

    @Test
    public void testCombatDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * dark.getPower()), sorcererTarget, 50, 50 - (int) (1.5 * axe.getPower()), dark);    }

    @Test
    public void testCombatLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50 - axe.getPower());
    }

    @Test
    public void testCombatLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * light.getPower()), sorcererTarget, 50, 50 - (int) (1.5 * axe.getPower()), light);
    }

    @Test
    public void testCombatAlpaca() {
        testCombatUnequipped(50, targetAlpaca, 50, 50 - axe.getPower());
    }

    @Override
    public void setTestUnit() {
        fighter = new Fighter(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return fighter;
    }
}