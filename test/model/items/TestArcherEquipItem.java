package model.items;

import model.items.weapons.Bow;
import model.units.*;
import org.junit.jupiter.api.Test;

public class TestArcherEquipItem extends AbstractTestUnit {
    private Archer archer;

    @Test
    public void testEquipSpear() {
        testEquippingItem(spear, null);
    }

    @Test
    public void testEquipBowDifferentName() {
        Bow otherBow = new Bow("Other Bow", 10, 2, 3);
        testEquippingOtherItem(bow, otherBow);
    }
    @Test
    public void testEquipBowDifferentPower() {
        Bow otherBow = new Bow("Bow", 20, 2, 3);
        testEquippingOtherItem(bow, otherBow);
    }
    @Test
    public void testEquipBowDifferentRange() {
        Bow otherBow = new Bow("Bow", 10, 2, 4);
        testEquippingOtherItem(bow, otherBow);
    }

    @Test
    public void testEquipBow() {
        Bow otherBow = new Bow("Bow", 10, 2, 3);
        testEquippingItem(bow, otherBow);
    }

    @Test
    public void testEquipStaff() {
        testEquippingItem(staff, null);
    }

    @Test
    public void testEquipAxe() {
        testEquippingItem(axe, null);
    }

    @Test
    public void testEquipSword() {
        testEquippingItem(sword, null);
    }

    @Test
    public void testEquipAnimaSpell() {
        testEquippingItem(anima, null);
    }

    @Test
    public void testEquipDarkSpell() {
        testEquippingItem(dark, null);
    }

    @Test
    public void testEquipLightSpell() {
        testEquippingItem(light, null);
    }

    @Test
    public void testAddAndEquipBow() {
        testAddAndEquip(bow);
    }

    @Test
    public void testTryEquipBowWithFullInventory() {
        Bow otherItem = new Bow("Other Bow", 10, 1, 2);
        testTryEquipWithFullInventory(otherItem);
    }

    @Test
    public void testEquipFullInventoryWithEquippedItemBow() {
        Bow otherItem = new Bow("Bow", 10, 1, 2);
        testEquipFullInventoryWithEquippedItem(otherItem);
    }

    @Test
    public void testAddItemWithOwnerArcher() {
        Archer otherUnit = new Archer(50, 2, field.getCell(0, 1));
        testAddItemWithOwner(otherUnit, bow);
    }

    @Test
    public void testEquipItemWithOwnerArcher() {
        Archer otherUnit = new Archer(50, 2, field.getCell(0, 1));
        testEquipItemWithOwner(otherUnit, bow);
    }

    @Test
    public void testOwnershipArcher() {
        testOwnership(bow);
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