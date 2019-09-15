package model.combat;

import model.items.IEquipableItem;
import model.items.magic.Light;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestLightSorcererCombat extends AbstractTestUnit {
    private Sorcerer sorcerer;

    public void testCombatUnequipped(int hpAfterCombat, IUnit targetUnit, int targetHpBeforeCombat, int targetHpAfterCombat) {

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        assertNull(sorcerer.getEquippedItem());
        assertNull(targetUnit.getEquippedItem());

        sorcerer.doCombat(targetUnit);

        assertEquals(targetHpBeforeCombat, targetUnit.getCurrentHitPoints());
        assertEquals(50, sorcerer.getCurrentHitPoints());

        sorcerer.equipItem(light);

        assertEquals(light, sorcerer.getEquippedItem());

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

        sorcerer.equipItem(light);
        targetUnit.equipItem(targetItem);

        assertEquals(light, sorcerer.getEquippedItem());

        sorcerer.doCombat(targetUnit);

        assertEquals(targetHpAfterCombat, targetUnit.getCurrentHitPoints());
        assertEquals(hpAfterCombat, sorcerer.getCurrentHitPoints());

    }

    @Test
    public void testCombatHeroUnequipped() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, heroTarget, 50,
                50 - light.getPower());
    }

    @Test
    public void testCombatHero() {
        Hero heroTarget = new Hero(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * spear.getPower()), heroTarget, 50,
                50 - (int) (1.5 * light.getPower()), spear);
    }

    @Test
    public void testCombatSwordMasterUnequipped() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, swordMasterTarget, 50,
                50 - light.getPower());
    }

    @Test
    public void testCombatSwordMaster() {
        SwordMaster swordMasterTarget = new SwordMaster(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * sword.getPower()), swordMasterTarget, 50,
                50 - (int) (1.5 * light.getPower()), sword);
    }

    @Test
    public void testCombatArcherUnequipped() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, archerTarget, 50,
                50 - light.getPower());
    }

    @Test
    public void testCombatArcher() {
        Archer archerTarget = new Archer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * bow.getPower()), archerTarget, 50,
                50 - (int) (1.5 * light.getPower()), bow);
    }

    @Test
    public void testCombatFighterUnequipped() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, fighterTarget, 50,
                50 - light.getPower());
    }

    @Test
    public void testCombatFighter() {
        Fighter fighterTarget = new Fighter(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * axe.getPower()), fighterTarget, 50,
                50 - (int) (1.5 * light.getPower()), axe);
    }

    @Test
    public void testCombatClericUnequipped() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, clericTarget, 50,
                50 - light.getPower());
    }

    @Test
    public void testCombatCleric() {
        Cleric clericTarget = new Cleric(50, 2, field.getCell(0, 2));
        testCombatEquipped(50, clericTarget, 50,
                50 - light.getPower(), staff);
    }

    @Test
    public void testCombatAnimaSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - light.getPower());
    }

    @Test
    public void testCombatAnimaSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - (int) (1.5 * anima.getPower()), sorcererTarget, 50,
                50 - Math.max(0, light.getPower() - 20), anima);
    }

    @Test
    public void testCombatDarkSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - light.getPower());
    }

    @Test
    public void testCombatDarkSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatEquipped(50 - Math.max(0, dark.getPower() - 20), sorcererTarget, 50,
                50 - (int) (1.5 * light.getPower()), dark);
    }

    @Test
    public void testCombatLightSorcererUnequipped() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        testCombatUnequipped(50, sorcererTarget, 50,
                50 - light.getPower());
    }

    @Test
    public void testCombatLightSorcerer() {
        Sorcerer sorcererTarget = new Sorcerer(50, 2, field.getCell(0, 2));
        Light otherLight = new Light("Light", 10, 1, 3);
        testCombatEquipped(50 - otherLight.getPower(), sorcererTarget, 50,
                50 - light.getPower(), otherLight);
    }

    @Test
    public void testCombatAlpaca() {
        testCombatUnequipped(50, targetAlpaca, 50,
                50 - light.getPower());
    }

    @Test
    public void testUseOnAllUnitsLightSorcerer() {
        Light otherLight = new Light("Light", 10, 1, 3);
        testUseOnAllUnits(otherLight, 50, 50 - otherLight.getPower());
    }

    @Test
    public void testUseOnLowHPLightSorcerer() {
        Light otherLight = new Light("Light", 50, 1, 3);
        testUseOnLowHPAllUnits(otherLight);
    }

    @Test
    public void testUseOnOutOfRangeLightSorcerer() {
        Light otherLight = new Light("Light", 50, 1, 3);
        testUseOnOutOfRangeTargetAllUnits(otherLight);
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