package model.combat;

import model.items.IEquipableItem;
import model.items.magic.Dark;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestDarkSorcererCombat extends AbstractTestUnit {
    private Sorcerer sorcerer;

    /*
    public void testAttackUnequipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        assertNull(sorcerer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        sorcerer.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        sorcerer.equipItem(dark);

        assertEquals(dark, sorcerer.getEquippedItem());

        sorcerer.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

    }

    public void testAttackEquipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack, IEquipableItem targetItem) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        assertNull(sorcerer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        sorcerer.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        sorcerer.equipItem(dark);
        targetUnit.equipItem(targetItem);

        assertEquals(dark, sorcerer.getEquippedItem());
        assertEquals(targetItem, targetUnit.getEquippedItem());

        sorcerer.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

    }

    @Test
    public void testAttackHeroOutOfRangeUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 4));
        testAttackUnequipped(heroTarget, 50, 50);
    }

    @Test
    public void testAttackHeroOutOfRange() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 4));
        testAttackEquipped(heroTarget, 50, 50, spear);
    }

    @Test
    public void testAttackHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testAttackUnequipped(heroTarget, 50, 50 - dark.getPower());
    }

    @Test
    public void testAttackHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testAttackEquipped(heroTarget, 50, (int) (50  - dark.getPower() * 1.5), spear);
    }

    @Test
    public void testAttackSwordMasterOutOfRangeUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 4));
        testAttackUnequipped(swordMasterTarget, 50, 50);
    }

    @Test
    public void testAttackSwordMasterOutOfRange() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 4));
        testAttackEquipped(swordMasterTarget, 50, 50, sword);

    }

    @Test
    public void testAttackSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testAttackUnequipped(swordMasterTarget, 50, 50 - dark.getPower());
    }

    @Test
    public void testAttackSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testAttackEquipped(swordMasterTarget, 50, (int) (50  - dark.getPower() * 1.5), sword);
    }

    @Test
    public void testAttackArcherOutOfRangeUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 4));
        testAttackUnequipped(archerTarget, 50, 50);
    }

    @Test
    public void testAttackArcherOutOfRange() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 4));
        testAttackEquipped(archerTarget, 50, 50, bow);
    }

    @Test
    public void testAttackArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testAttackUnequipped(archerTarget, 50, 50 - dark.getPower());
    }

    @Test
    public void testAttackArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testAttackEquipped(archerTarget, 50, (int) (50  - dark.getPower() * 1.5), bow);

    }

    @Test
    public void testAttackFighterOutOfRangeUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 4));
        testAttackUnequipped(fighterTarget, 50, 50);
    }

    @Test
    public void testAttackFighterOutOfRange() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 4));
        testAttackEquipped(fighterTarget, 50, 50, axe);
    }

    @Test
    public void testAttackFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testAttackUnequipped(fighterTarget, 50, 50 - dark.getPower());
    }

    @Test
    public void testAttackFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testAttackEquipped(fighterTarget, 50, (int) (50  - 1.5 * dark.getPower()), axe);
    }

    @Test
    public void testAttackClericOutOfRangeUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 4));
        testAttackUnequipped(clericTarget, 50, 50);
    }

    @Test
    public void testAttackClericOutOfRange() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 4));
        testAttackEquipped(clericTarget, 50, 50, staff);
    }

    @Test
    public void testAttackCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testAttackUnequipped(clericTarget, 50, 50  - dark.getPower());
        testAttackEquipped(clericTarget, 50, 50  - dark.getPower(), staff);
    }

    @Test
    public void testAttackDarkSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 4));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackDarkSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 4));
        testAttackEquipped(sorcererTarget, 50, 50, dark);
    }

    @Test
    public void testAttackDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testAttackUnequipped(sorcererTarget, 50, 50 - dark.getPower());
    }

    @Test
    public void testAttackDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testAttackEquipped(sorcererTarget, 50, 50 - dark.getPower(), dark);
    }

    @Test
    public void testAttackLightSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 4));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackLightSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 4));
        testAttackEquipped(sorcererTarget, 50, 50, light);
    }

    @Test
    public void testAttackLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testAttackUnequipped(sorcererTarget, 50, 50 - dark.getPower());
    }

    @Test
    public void testAttackLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testAttackEquipped(sorcererTarget, 50, 50, light);
    }

    @Test
    public void testAttackAnimaSorcererOutOfRangeUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 4));
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackAnimaSorcererOutOfRange() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 4));
        testAttackEquipped(sorcererTarget, 50, 50, anima);
    }

    @Test
    public void testAttackAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testAttackUnequipped(sorcererTarget, 50, 50 - dark.getPower());
    }

    @Test
    public void testAttackAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testAttackEquipped(sorcererTarget, 50, (int) (50  - dark.getPower() * 1.5), anima);
    }

    @Test
    public void testAttackAlpacaOutOfRange() {
        Alpaca alpacaTarget = new Alpaca(50, 2, field.getCell(0, 4));
        testAttackUnequipped(alpacaTarget, 50, 50);
    }

    @Test
    public void testAttackAlpaca() {
        testAttackUnequipped(targetAlpaca, 50, 50 - dark.getPower());
    }


     */
    public void testCombatUnequipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat, int targetHpAfterCombat) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        assertNull(sorcerer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        sorcerer.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        sorcerer.equipItem(dark);

        assertEquals(dark, sorcerer.getEquippedItem());

        sorcerer.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, sorcerer.getCurrentHitPoints());

    }

    public void testCombatEquipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat,
                                   int targetHpAfterCombat, IEquipableItem targetItem) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        assertNull(sorcerer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        sorcerer.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        sorcerer.equipItem(dark);
        targetUnit.equipItem(targetItem);

        assertEquals(dark, sorcerer.getEquippedItem());

        sorcerer.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, sorcerer.getCurrentHitPoints());

    }

    @Test
    public void testCombatHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, heroTarget, 50,
                50 - dark.getPower());
    }

    @Test
    public void testCombatHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * spear.getPower()), heroTarget, 50,
                50 - (int) (1.5 * dark.getPower()), spear);
    }

    @Test
    public void testCombatSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, swordMasterTarget, 50,
                50 - dark.getPower());
    }

    @Test
    public void testCombatSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * sword.getPower()), swordMasterTarget, 50,
                50 - (int) (1.5 * dark.getPower()), sword);
    }

    @Test
    public void testCombatArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, archerTarget, 50,
                50 - dark.getPower());
    }

    @Test
    public void testCombatArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * bow.getPower()), archerTarget, 50,
                50 - (int) (1.5 * dark.getPower()), bow);
    }

    @Test
    public void testCombatFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, fighterTarget, 50,
                50 - dark.getPower());
    }

    @Test
    public void testCombatFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * axe.getPower()), fighterTarget, 50,
                50 - (int) (1.5 * dark.getPower()), axe);
    }

    @Test
    public void testCombatClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, clericTarget, 50,
                50 - dark.getPower());
    }

    @Test
    public void testCombatCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, clericTarget, 50,
                50 - dark.getPower(), staff);
    }

    @Test
    public void testCombatAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - dark.getPower());
    }

    @Test
    public void testCombatAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - Math.max(0, anima.getPower() - 20), sorcererTarget, 50,
                50 - (int) (1.5 * dark.getPower()), anima);
    }

    @Test
    public void testCombatDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - dark.getPower());
    }

    @Test
    public void testCombatDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        Dark otherDark = new Dark("Dark", 10, 1, 3);
        testCombatEquipped(50 - otherDark.getPower(), sorcererTarget, 50,
                50 - dark.getPower(), otherDark);
    }

    @Test
    public void testCombatLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - dark.getPower());
    }

    @Test
    public void testCombatLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * light.getPower()), sorcererTarget, 50,
                50 - Math.max(0, dark.getPower() - 20), light);
    }

    @Test
    public void testCombatAlpaca() {
        testCombatUnequipped(50, targetAlpaca, 50,
                50 - dark.getPower());
    }


    @Test
    public void testUseOnAllUnitsDarkSorcerer() {
        Dark otherDark = new Dark("Dark", 10, 1, 3);
        testUseOnAllUnits(otherDark, 50, 50 - otherDark.getPower());
    }

    @Test
    public void testUseOnLowHPDarkSorcerer() {
        Dark otherDark = new Dark("Dark", 50, 1, 3);
        testUseOnLowHPAllUnits(otherDark);
    }

    @Test
    public void testUseOnOutOfRangeDarkSorcerer() {
        Dark otherDark = new Dark("Dark", 50, 1, 3);
        testUseOnOutOfRangeTargetAllUnits(otherDark);
    }

    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }
}