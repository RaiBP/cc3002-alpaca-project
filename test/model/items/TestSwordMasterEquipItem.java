package model.items;

import model.items.weapons.Sword;
import model.units.*;
import org.junit.jupiter.api.Test;

public class TestSwordMasterEquipItem extends AbstractTestUnit {
    private SwordMaster swordMaster;

    @Test
    public void testEquipSpear() {
        testEquippingItem(spear, null);
    }

    @Test
    public void testEquipSwordDifferentName() {
        Sword otherSword = new Sword("Other Sword", 10, 1, 2);
        testEquippingOtherItem(sword, otherSword);
    }
    @Test
    public void testEquipSwordDifferentPower() {
        Sword otherSword = new Sword("Sword", 20, 1, 2);
        testEquippingOtherItem(sword, otherSword);
    }
    @Test
    public void testEquipSwordDifferentRange() {
        Sword otherSword = new Sword("Sword", 10, 2, 3);
        testEquippingOtherItem(sword, otherSword);
    }

    @Test
    public void testEquipSword() {
        Sword otherSword = new Sword("Sword", 10, 1, 2);
        testEquippingItem(sword, otherSword);
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
    public void testAddAndEquipSword() {
        testAddAndEquip(sword);
    }

    @Test
    public void testTryEquipSwordWithFullInventory() {
        Sword otherItem = new Sword("Other Sword", 10, 1, 2);
        testTryEquipWithFullInventory(otherItem);
    }

    @Test
    public void testEquipFullInventoryWithEquippedItemSword() {
        testEquipFullInventoryWithEquippedItem(sword);
    }

    @Test
    public void testAddItemWithOwnerSwordMaster() {
        SwordMaster otherUnit = new SwordMaster(50, 2, field.getCell(0, 1));
        testAddItemWithOwner(otherUnit, sword);
    }

    @Test
    public void testEquipItemWithOwnerSwordMaster() {
        SwordMaster otherUnit = new SwordMaster(50, 2, field.getCell(0, 1));
        testEquipItemWithOwner(otherUnit, sword);
    }

    @Test
    public void testOwnershipSwordMaster() {
        testOwnership(sword);
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