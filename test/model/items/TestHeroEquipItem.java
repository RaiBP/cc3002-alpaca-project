package model.items;

import model.items.weapons.Spear;
import model.units.*;
import org.junit.jupiter.api.Test;

public class TestHeroEquipItem extends AbstractTestUnit {
    private Hero hero;

    @Test
    public void testEquipSword() {
        testEquippingItem(sword, null);
    }

    @Test
    public void testEquipSpearDifferentName() {
        Spear otherSpear = new Spear("Other Spear", 10, 1, 2);
        testEquippingOtherItem(spear, otherSpear);
    }
    @Test
    public void testEquipSpearDifferentPower() {
        Spear otherSpear = new Spear("Spear", 20, 1, 2);
        testEquippingOtherItem(spear, otherSpear);
    }
    @Test
    public void testEquipSpearDifferentRange() {
        Spear otherSpear = new Spear("Spear", 10, 2, 3);
        testEquippingOtherItem(spear, otherSpear);
    }

    @Test
    public void testEquipSpear() {
        Spear otherSpear = new Spear("Spear", 10, 1, 2);
        testEquippingItem(spear, otherSpear);
    }

    @Test
    public void testEquipStaff() {
        testEquippingItem(staff, null);
    }

    @Test
    public void testEquipBow() {
        testEquippingItem(bow, null);
    }

    @Test
    public void testEquipAxe() {
        testEquippingItem(axe, null);
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
    public void testAddAndEquipSpear() {
        Spear otherItem = new Spear("Other Spear", 10, 1, 2);
        testAddAndEquip(otherItem);
    }

    @Test
    public void testTryEquipSpearWithFullInventory() {
        Spear otherItem = new Spear("Other Spear", 10, 1, 2);
        testTryEquipWithFullInventory(otherItem);
    }

    @Test
    public void testEquipFullInventoryWithEquippedItemSpear() {
        testEquipFullInventoryWithEquippedItem(spear);
    }

    @Test
    public void testAddItemWithOwnerHero() {
        Hero otherUnit = new Hero(50, 2, field.getCell(0, 1));
        testAddItemWithOwner(otherUnit, spear);
    }

    @Test
    public void testEquipItemWithOwnerHero() {
        Hero otherUnit = new Hero(50, 2, field.getCell(0, 1));
        testEquipItemWithOwner(otherUnit, spear);
    }

    @Test
    public void testOwnershipHero() {
        testOwnership(spear);
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