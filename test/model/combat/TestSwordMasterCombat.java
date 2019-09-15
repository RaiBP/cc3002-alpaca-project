package model.combat;

import model.items.IEquipableItem;
import model.items.weapons.Sword;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestSwordMasterCombat extends AbstractTestUnit {
    private SwordMaster swordMaster;
/*
    public void testAttackUnequipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

        assertNull(swordMaster.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        swordMaster.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

        swordMaster.equipItem(sword);

        assertEquals(sword, swordMaster.getEquippedItem());

        swordMaster.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

    }

    public void testAttackEquipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack, IEquipableItem targetItem) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

        assertNull(swordMaster.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        swordMaster.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

        swordMaster.equipItem(sword);
        targetUnit.equipItem(targetItem);

        assertEquals(sword, swordMaster.getEquippedItem());
        assertEquals(targetItem, targetUnit.getEquippedItem());

        swordMaster.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

    }

    @Test
    public void testAttackHeroOutOfRangeUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 3));
        testAttackUnequipped(heroTarget, 50, 50);
    }

    @Test
    public void testAttackHeroOutOfRange() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 3));
        testAttackEquipped(heroTarget, 50, 50, spear);
    }

    @Test
    public void testAttackHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 1));
        testAttackUnequipped(heroTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testAttackHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 1));
        testAttackEquipped(heroTarget, 50, 50, spear);
    }

    @Test
    public void testAttackSwordMasterOutOfRangeUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 3));
        testAttackUnequipped(swordMasterTarget, 50, 50);
    }

    @Test
    public void testAttackSwordMasterOutOfRange() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 3));
        testAttackEquipped(swordMasterTarget, 50, 50, sword);
    }

    @Test
    public void testAttackSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 1));
        testAttackUnequipped(swordMasterTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testAttackSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 1));
        testAttackEquipped(swordMasterTarget, 50, 50 - sword.getPower(), sword);
    }

    @Test
    public void testAttackArcherOutOfRangeUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 3));
        testAttackUnequipped(archerTarget, 50, 50);
    }

    @Test
    public void testAttackArcherOutOfRange() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 3));
        testAttackEquipped(archerTarget, 50, 50, bow);
    }

    @Test
    public void testAttackArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(archerTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testAttackArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 1));
        testAttackEquipped(archerTarget, 50, 50  - sword.getPower(), bow);
    }

    @Test
    public void testAttackFighterOutOfRangeUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 3));
        testAttackUnequipped(fighterTarget, 50, 50);
    }

    @Test
    public void testAttackFighterOutOfRange() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 3));
        testAttackEquipped(fighterTarget, 50, 50, axe);
    }

    @Test
    public void testAttackFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 1));
        testAttackUnequipped(fighterTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testAttackFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 1));
        testAttackEquipped(fighterTarget, 50, (int) (50  - 1.5 * sword.getPower()), axe);
    }

    @Test
    public void testAttackClericOutOfRangeUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 3));
        testAttackUnequipped(clericTarget, 50, 50);
    }

    @Test
    public void testAttackClericOutOfRange() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 3));
        testAttackEquipped(clericTarget, 50, 50, staff);
    }

    @Test
    public void testAttackClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 1));
        testAttackUnequipped(clericTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testAttackCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 1));
        testAttackEquipped(clericTarget, 50, 50  - sword.getPower(), staff);
    }

    @Test
    public void testAttackDarkSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackDarkSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackEquipped(sorcererTarget, 50, 50, dark);
    }

    @Test
    public void testAttackDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testAttackDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - sword.getPower() * 1.5), dark);
    }

    @Test
    public void testAttackLightSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackLightSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackEquipped(sorcererTarget, 50, 50, light);
    }

    @Test
    public void testAttackLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testAttackLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - sword.getPower() * 1.5), light);
    }

    @Test
    public void testAttackAnimaSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackAnimaSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackEquipped(sorcererTarget, 50, 50, anima);
    }

    @Test
    public void testAttackAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testAttackAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - sword.getPower() * 1.5), anima);
    }

    @Test
    public void testAttackAlpacaOutOfRange() {
        Alpaca alpacaTarget = new Alpaca(50, 2, field.getCell(0, 3));
        testAttackUnequipped(alpacaTarget, 50, 50);
    }

    @Test
    public void testAttackAlpaca() {
        testAttackUnequipped(targetAlpaca, 50, 50 - sword.getPower());
    }

 */

    public void testCombatUnequipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat, int targetHpAfterCombat) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

        assertNull(swordMaster.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        swordMaster.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

        swordMaster.equipItem(sword);

        assertEquals(sword, swordMaster.getEquippedItem());

        swordMaster.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, swordMaster.getCurrentHitPoints());

    }

    public void testCombatEquipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat,
                                   int targetHpAfterCombat, IEquipableItem targetItem) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

        assertNull(swordMaster.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        swordMaster.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, swordMaster.getCurrentHitPoints());

        swordMaster.equipItem(sword);
        targetUnit.equipItem(targetItem);

        assertEquals(sword, swordMaster.getEquippedItem());

        swordMaster.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, swordMaster.getCurrentHitPoints());

    }

    @Test
    public void testCombatHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, heroTarget, 50,
                50 - sword.getPower());
    }

    @Test
    public void testCombatHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * spear.getPower()), heroTarget, 50,
                50 - Math.max(0, sword.getPower() - 20), spear);
    }

    @Test
    public void testCombatSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, swordMasterTarget, 50,
                50 - sword.getPower());
    }

    @Test
    public void testCombatSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        Sword otherSword = new Sword("Sword", 10, 1, 2);
        testCombatEquipped(50 - otherSword.getPower(), swordMasterTarget,
                50, 50 - sword.getPower(), otherSword);
    }

    @Test
    public void testCombatArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, archerTarget, 50, 50 - sword.getPower());
    }

    @Test
    public void testCombatArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - bow.getPower(), archerTarget, 50, 50 - sword.getPower(), bow);
    }

    @Test
    public void testCombatFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, fighterTarget, 50,
                50 - sword.getPower());
    }

    @Test
    public void testCombatFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - Math.max(0, axe.getPower() - 20), fighterTarget, 50,
                50 - (int) (1.5 * sword.getPower()), axe);
    }

    @Test
    public void testCombatClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, clericTarget, 50,
                50 - sword.getPower());
    }

    @Test
    public void testCombatCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, clericTarget, 50,
                50 - sword.getPower(), staff);
    }

    @Test
    public void testCombatAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - sword.getPower());
    }

    @Test
    public void testCombatAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * anima.getPower()), sorcererTarget, 50,
                50 - (int) (1.5 * sword.getPower()), anima);
    }

    @Test
    public void testCombatDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - sword.getPower());
    }

    @Test
    public void testCombatDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * dark.getPower()), sorcererTarget, 50,
                50 - (int) (1.5 * sword.getPower()), dark);    }

    @Test
    public void testCombatLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - sword.getPower());
    }

    @Test
    public void testCombatLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * light.getPower()), sorcererTarget, 50,
                50 - (int) (1.5 * sword.getPower()), light);
    }

    @Test
    public void testUseOnAllUnitsSwordMaster() {
        Sword otherSword = new Sword("Sword", 10, 1, 2);
        testUseOnAllUnits(otherSword, 50, 50 - otherSword.getPower());
    }

    @Test
    public void testCombatAlpaca() {
        testCombatUnequipped(50, targetAlpaca, 50,
                50 - sword.getPower());
    }

    @Test
    public void testUseOnLowHPSwordMaster() {
        Sword otherSword = new Sword("Sword", 50, 1, 3);
        testUseOnLowHPAllUnits(otherSword);
    }

    @Test
    public void testUseOnOutOfRangeSwordMaster() {
        Sword otherSword = new Sword("Sword", 50, 1, 2);
        testUseOnOutOfRangeTargetAllUnits(otherSword);
    }

    @Override
    public void setTestUnit() {
        swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return swordMaster;
    }
}