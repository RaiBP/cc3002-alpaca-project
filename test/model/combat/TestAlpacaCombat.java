package model.combat;

import model.items.IEquipableItem;
import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.items.tools.Staff;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestAlpacaCombat extends AbstractTestUnit {
    private Alpaca alpaca;

    /*

    public void testAttackUnequipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

        assertNull(alpaca.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        alpaca.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

        alpaca.equipItem(staff);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(sword);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(bow);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(dark);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(anima);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(light);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(spear);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(axe);
        assertNull(alpaca.getEquippedItem());

        alpaca.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

    }

    public void testAttackEquipped(IUnit targetUnit, int hpBeforeAttack, int hpAfterAttack, IEquipableItem targetItem) {

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

        assertNull(alpaca.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        alpaca.attack(targetUnit);

        assertEquals(hpBeforeAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

        alpaca.equipItem(staff);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(sword);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(bow);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(dark);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(anima);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(light);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(spear);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(axe);
        assertNull(alpaca.getEquippedItem());

        targetUnit.equipItem(targetItem);
        assertEquals(targetItem, targetUnit.getEquippedItem());

        alpaca.attack(targetUnit);

        assertEquals(hpAfterAttack, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

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
        testAttackUnequipped(heroTarget, 50, 50);
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
        testAttackUnequipped(swordMasterTarget, 50, 50);
    }

    @Test
    public void testAttackSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 1));
        testAttackEquipped(swordMasterTarget, 50, 50, sword);
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
        testAttackUnequipped(archerTarget, 50, 50);
    }

    @Test
    public void testAttackArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 1));
        testAttackEquipped(archerTarget, 50, 50, bow);
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
        testAttackUnequipped(fighterTarget, 50, 50);
    }

    @Test
    public void testAttackFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 1));
        testAttackEquipped(fighterTarget, 50, 50, axe);
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
        testAttackUnequipped(clericTarget, 50, 50);
    }

    @Test
    public void testAttackCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 1));
        testAttackEquipped(clericTarget, 50, 50, staff);
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
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, 50, dark);
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
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, 50, light);
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
        testAttackUnequipped(sorcererTarget, 50, 50);
    }

    @Test
    public void testAttackAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 1));
        testAttackEquipped(sorcererTarget, 50, 50, anima);
    }

    @Test
    public void testAttackAlpacaOutOfRange() {
        Alpaca alpacaTarget = new Alpaca(50, 2, field.getCell(0, 3));
        testAttackUnequipped(alpacaTarget, 50, 50);
    }

    @Test
    public void testAttackAlpaca() {
        testAttackUnequipped(targetAlpaca, 50, 50);
    }

     */

    public void testCombatUnequipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat, int targetHpAfterCombat) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

        assertNull(alpaca.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        alpaca.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

        alpaca.equipItem(staff);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(sword);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(bow);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(dark);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(anima);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(light);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(spear);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(axe);
        assertNull(alpaca.getEquippedItem());

        alpaca.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, alpaca.getCurrentHitPoints());

    }

    public void testCombatEquipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat,
                                   int targetHpAfterCombat, IEquipableItem targetItem) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

        assertNull(alpaca.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        alpaca.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, alpaca.getCurrentHitPoints());

        alpaca.equipItem(staff);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(sword);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(bow);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(dark);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(anima);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(light);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(spear);
        assertNull(alpaca.getEquippedItem());

        alpaca.equipItem(axe);
        assertNull(alpaca.getEquippedItem());

        targetUnit.equipItem(targetItem);

        alpaca.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, alpaca.getCurrentHitPoints());

    }

    @Test
    public void testCombatHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, heroTarget, 50, 50);
    }

    @Test
    public void testCombatHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, heroTarget, 50, 50, spear);
    }

    @Test
    public void testCombatSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, swordMasterTarget, 50, 50);
    }

    @Test
    public void testCombatSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, swordMasterTarget, 50, 50, sword);
    }

    @Test
    public void testCombatArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, archerTarget, 50, 50);
    }

    @Test
    public void testCombatArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, archerTarget, 50, 50, bow);
    }

    @Test
    public void testCombatFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, fighterTarget, 50, 50);
    }

    @Test
    public void testCombatFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, fighterTarget, 50, 50, axe);
    }

    @Test
    public void testCombatClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, clericTarget, 50, 50);
    }

    @Test
    public void testCombatCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, clericTarget, 50, 50, staff);
    }

    @Test
    public void testCombatAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50);
    }

    @Test
    public void testCombatAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, sorcererTarget, 50, 50, anima);
    }

    @Test
    public void testCombatDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50);
    }

    @Test
    public void testCombatDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, sorcererTarget, 50, 50, anima);    }

    @Test
    public void testCombatLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50, 50);
    }

    @Test
    public void testCombatLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, sorcererTarget, 50, 50, light);
    }

    @Test
    public void testCombatAlpaca() {
        testCombatUnequipped(50, targetAlpaca, 50, 50);
    }

    @Test
    public void testUseOnAllUnitsArcher() {
        Axe otherAxe = new Axe("Axe", 10, 1, 2);
        Sword otherSword = new Sword("Sword", 10, 1, 2);
        Spear otherSpear = new Spear("Spear", 10, 1, 2);
        Staff otherStaff = new Staff("Staff", 10, 1, 2);
        Dark otherDark = new Dark("Dark", 10, 1, 3);
        Bow otherBow = new Bow("Bow", 10, 2, 3);
        Anima otherAnima = new Anima("Anima", 10, 1, 3);
        Light otherLight = new Light("Light", 10, 1, 3);

        testUseOnAllUnits(otherAxe, 50, 50);
        testUseOnAllUnits(otherSpear, 50, 50);
        testUseOnAllUnits(otherSword, 50, 50);
        testUseOnAllUnits(otherStaff, 50, 50);
        testUseOnAllUnits(otherDark, 50, 50);
        testUseOnAllUnits(otherAnima, 50, 50);
        testUseOnAllUnits(otherBow, 50, 50);
        testUseOnAllUnits(otherLight, 50, 50);

    }

    @Override
    public void setTestUnit() {
        alpaca = new Alpaca(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return alpaca;
    }
}