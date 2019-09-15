package model.combat;

import model.items.IEquipableItem;
import model.items.magic.Anima;
import model.items.weapons.Bow;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestArcherCombat extends AbstractTestUnit {
    private Archer archer;

    /*

    public void testAttackUnequipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

        assertNull(archer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        archer.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

        archer.equipItem(spear);

        assertEquals(spear, archer.getEquippedItem());

        archer.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

    }

    public void testAttackEquipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack, IEquipableItem targetItem) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

        assertNull(archer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        archer.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

        archer.equipItem(bow);
        targetUnit.equipItem(targetItem);

        assertEquals(bow, archer.getEquippedItem());
        assertEquals(targetItem, targetUnit.getEquippedItem());

        archer.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

    }

    @Test
    public void testAttackHeroOutOfRangeUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 1));
        testAttackUnequipped(heroTarget, 50, 50);
    }

    @Test
    public void testAttackHeroOutOfRange() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 1));
        testAttackEquipped(heroTarget, 50, 50, spear);
    }

    @Test
    public void testAttackHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 3));
        testAttackUnequipped(heroTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testAttackHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 3));
        testAttackEquipped(heroTarget, 50, 50 - bow.getPower(), spear);
    }

    @Test
    public void testAttackSwordMasterOutOfRangeUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 1));
        testAttackUnequipped(swordMasterTarget, 50, 50);
    }

    @Test
    public void testAttackSwordMasterOutOfRange() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 1));
        testAttackEquipped(swordMasterTarget, 50, 50, sword);
    }

    @Test
    public void testAttackSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 3));
        testAttackUnequipped(swordMasterTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testAttackSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 3));
        testAttackEquipped(swordMasterTarget, 50, 50  - bow.getPower(), sword);
    }

    @Test
    public void testAttackArcherOutOfRangeUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(archerTarget, 50, 50);
    }

    @Test
    public void testAttackArcherOutOfRange() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 1));
        testAttackEquipped(archerTarget, 50, 50, bow);
    }

    @Test
    public void testAttackArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 3));
        testAttackUnequipped(archerTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testAttackArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 3));
        testAttackEquipped(archerTarget, 50, 50  - bow.getPower(), bow);
    }

    @Test
    public void testAttackFighterOutOfRangeUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 1));
        testAttackUnequipped(fighterTarget, 50, 50);
    }

    @Test
    public void testAttackFighterOutOfRange() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 1));
        testAttackEquipped(fighterTarget, 50, 50, axe);
    }

    @Test
    public void testAttackFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 3));
        testAttackUnequipped(fighterTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testAttackFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 3));
        testAttackEquipped(fighterTarget, 50, 50  - bow.getPower(), axe);
    }

    @Test
    public void testAttackClericOutOfRangeUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 1));
        testAttackUnequipped(clericTarget, 50, 50);
    }

    @Test
    public void testAttackClericOutOfRange() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 1));
        testAttackEquipped(clericTarget, 50, 50, staff);
    }

    @Test
    public void testAttackClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 3));
        testAttackUnequipped(clericTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testAttackCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 3));
        testAttackEquipped(clericTarget, 50, 50  - bow.getPower(), staff);
    }

    @Test
    public void testAttackDarkSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackDarkSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, 50, dark);
    }

    @Test
    public void testAttackDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackUnequipped(sorcererTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testAttackDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - bow.getPower() * 1.5), dark);
    }

    @Test
    public void testAttackLightSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackLightSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, 50, light);
    }

    @Test
    public void testAttackLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackUnequipped(sorcererTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testAttackLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - bow.getPower() * 1.5), light);
    }

    @Test
    public void testAttackAnimaSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackAnimaSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, 50, anima);
    }

    @Test
    public void testAttackAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackUnequipped(sorcererTarget, 50, 50 - 10);
    }

    @Test
    public void testAttackAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 3));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - bow.getPower() * 1.5), anima);
    }

    @Test
    public void testAttackAlpacaOutOfRange() {
        testAttackUnequipped(targetAlpaca, 50, 50);
    }

    @Test
    public void testAttackAlpaca() {
        Alpaca alpacaTarget = new Alpaca(50, 2, field.getCell(0, 3));
        testAttackUnequipped(alpacaTarget, 50, 50 - 10);
    }

     */

    public void testCombatUnequipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat, int targetHpAfterCombat) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

        assertNull(archer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        archer.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

        archer.equipItem(bow);

        assertEquals(bow, archer.getEquippedItem());

        archer.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, archer.getCurrentHitPoints());

    }

    public void testCombatEquipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat,
                                   int targetHpAfterCombat, IEquipableItem targetItem) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

        assertNull(archer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        archer.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, archer.getCurrentHitPoints());

        archer.equipItem(bow);
        targetUnit.equipItem(targetItem);

        assertEquals(bow, archer.getEquippedItem());

        archer.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, archer.getCurrentHitPoints());

    }

    @Test
    public void testCombatHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, heroTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testCombatHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - spear.getPower(), heroTarget, 50, 50 - bow.getPower(), spear);
    }

    @Test
    public void testCombatSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, swordMasterTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testCombatSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - sword.getPower(), swordMasterTarget, 50, 50 - bow.getPower(), sword);
    }

    @Test
    public void testCombatArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, archerTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testCombatArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));

        Bow otherBow = new Bow("Bow", 10, 2, 3);
        testCombatEquipped(50 - otherBow.getPower(), archerTarget, 50, 50 - bow.getPower(), otherBow);
    }

    @Test
    public void testCombatFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, fighterTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testCombatFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - axe.getPower(), fighterTarget, 50, 50 - bow.getPower(), axe);
    }

    @Test
    public void testCombatClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, clericTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testCombatCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, clericTarget, 50, 50 - bow.getPower(), staff);
    }

    @Test
    public void testCombatAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testCombatAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped( (int) (50 - 1.5 * anima.getPower()), sorcererTarget, 50, (int) (50 - 1.5 * bow.getPower()), anima);
    }

    @Test
    public void testCombatDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testCombatDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped( (int) (50 - 1.5 * dark.getPower()), sorcererTarget, 50, (int) (50 - 1.5 * bow.getPower()), anima);    }

    @Test
    public void testCombatLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50 - bow.getPower());
    }

    @Test
    public void testCombatLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped( (int) (50 - 1.5 * light.getPower()), sorcererTarget, 50, (int) (50 - 1.5 * bow.getPower()), light);
    }

    @Test
    public void testCombatAlpaca() {
        targetAlpaca.setLocation(field.getCell(0, 2));
        testCombatUnequipped(50, targetAlpaca, 50, 50 - bow.getPower());
    }

    @Test
    public void testUseOnAllUnitsFighter() {
        Bow otherBow = new Bow("Bow", 10, 1, 3);
        testUseOnAllUnits(otherBow, 50, 50 - otherBow.getPower());
    }

    @Test
    public void testUseOnLowHPArcher() {
        Bow otherBow = new Bow("Bow", 50, 2, 3);
        testUseOnLowHPAllUnits(otherBow);
    }

    @Test
    public void testUseOnOutOfRangeArcher() {
        Bow otherBow = new Bow("Bow", 50, 2, 3);
        testUseOnOutOfRangeTargetAllUnits(otherBow);
    }

    @Override
    public void setTestUnit() {
        archer = new Archer(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return archer;
    }
}