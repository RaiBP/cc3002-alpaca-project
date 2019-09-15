package model.combat;

import model.items.IEquipableItem;
import model.items.weapons.Spear;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestHeroCombat extends AbstractTestUnit {
    private Hero hero;
/*
    public void testAttackUnequipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

        assertNull(hero.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        hero.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

        hero.equipItem(spear);

        assertEquals(spear, hero.getEquippedItem());

        hero.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

    }

    public void testAttackEquipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack, IEquipableItem targetItem) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

        assertNull(hero.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        hero.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

        hero.equipItem(spear);
        targetUnit.equipItem(targetItem);

        assertEquals(spear, hero.getEquippedItem());
        assertEquals(targetItem, targetUnit.getEquippedItem());

        hero.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

    }

    @Test
    public void testAttackHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 1));
        testAttackUnequipped(heroTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testAttackHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 1));
        testAttackEquipped(heroTarget, 50, 50 - spear.getPower(), spear);
    }

    @Test
    public void testAttackSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 1));
        testAttackUnequipped(swordMasterTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testAttackSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 1));
        testAttackEquipped(swordMasterTarget, 50, (int) (50  - 1.5 * spear.getPower()), sword);

    }

    @Test
    public void testAttackArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(archerTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testAttackArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 1));
        testAttackEquipped(archerTarget, 50, 50  - spear.getPower(), bow);
    }

    @Test
    public void testAttackFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 1));
        testAttackUnequipped(fighterTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testAttackFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 1));
        testAttackEquipped(fighterTarget, 50, 50, axe);
    }

    @Test
    public void testAttackClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 1));
        testAttackUnequipped(clericTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testAttackCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 1));
        testAttackEquipped(clericTarget, 50, 50  - spear.getPower(), staff);
    }

    @Test
    public void testAttackDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testAttackDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - spear.getPower() * 1.5), dark);
    }

    @Test
    public void testAttackLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testAttackLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - spear.getPower() * 1.5), light);
    }

    @Test
    public void testAttackAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testAttackAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - spear.getPower() * 1.5), anima);
    }

    @Test
    public void testAttackAlpaca() {
        testAttackUnequipped(targetAlpaca, 50, 50 - spear.getPower());
    }

 */

    public void testCombatUnequipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat, int targetHpAfterCombat) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

        assertNull(hero.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        hero.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

        hero.equipItem(spear);

        assertEquals(spear, hero.getEquippedItem());

        hero.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, hero.getCurrentHitPoints());

    }

    public void testCombatEquipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat,
                                   int targetHpAfterCombat, IEquipableItem targetItem) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

        assertNull(hero.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        hero.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, hero.getCurrentHitPoints());

        hero.equipItem(spear);
        targetUnit.equipItem(targetItem);

        assertEquals(spear, hero.getEquippedItem());

        hero.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, hero.getCurrentHitPoints());

    }

    @Test
    public void testCombatHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, heroTarget, 50,
                50 - spear.getPower());
    }

    @Test
    public void testCombatHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        Spear otherSpear = new Spear("Spear", 10, 1, 2);
        testCombatEquipped(50 - otherSpear.getPower(), heroTarget, 50,
                50 - spear.getPower(), otherSpear);
    }

    @Test
    public void testCombatSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, swordMasterTarget, 50,
                50 - spear.getPower());
    }

    @Test
    public void testCombatSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - Math.max(0, sword.getPower() - 20), swordMasterTarget,
                50, 50 - (int) (1.5 * spear.getPower()), sword);
    }

    @Test
    public void testCombatArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, archerTarget, 50, 50 - spear.getPower());
    }

    @Test
    public void testCombatArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - bow.getPower(), archerTarget, 50, 50 - spear.getPower(), bow);
    }

    @Test
    public void testCombatFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, fighterTarget, 50,
                50 - spear.getPower());
    }

    @Test
    public void testCombatFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * axe.getPower()), fighterTarget, 50,
                50 - Math.max(0, spear.getPower() - 20), axe);
    }

    @Test
    public void testCombatClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, clericTarget, 50,
                50 - spear.getPower());
    }

    @Test
    public void testCombatCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, clericTarget, 50,
                50 - spear.getPower(), staff);
    }

    @Test
    public void testCombatAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - spear.getPower());
    }

    @Test
    public void testCombatAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * anima.getPower()), sorcererTarget, 50,
                50 - (int) (1.5 * spear.getPower()), anima);
    }

    @Test
    public void testCombatDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - spear.getPower());
    }

    @Test
    public void testCombatDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * dark.getPower()), sorcererTarget, 50,
                50 - (int) (1.5 * spear.getPower()), dark);    }

    @Test
    public void testCombatLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - spear.getPower());
    }

    @Test
    public void testCombatLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * light.getPower()), sorcererTarget, 50,
                50 - (int) (1.5 * spear.getPower()), light);
    }

    @Test
    public void testCombatAlpaca() {
        testCombatUnequipped(50, targetAlpaca, 50,
                50 - spear.getPower());
    }

    @Test
    public void testUseOnAllUnitsHero() {
        Spear otherSpear = new Spear("Spear", 10, 1, 2);
        testUseOnAllUnits(otherSpear, 50, 50 - otherSpear.getPower());
    }

    @Test
    public void testUseOnLowHPHero() {
        Spear otherSpear = new Spear("Spear", 50, 1, 3);
        testUseOnLowHPAllUnits(otherSpear);
    }

    @Test
    public void testUseOnOutOfRangeHero() {
        Spear otherSpear = new Spear("Spear", 50, 1, 2);
        testUseOnOutOfRangeTargetAllUnits(otherSpear);
    }


    @Override
    public void setTestUnit() {
        hero = new Hero(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return hero;
    }
}
