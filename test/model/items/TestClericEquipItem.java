package model.items;

import model.items.tools.Staff;
import model.units.*;
import org.junit.jupiter.api.Test;

public class TestClericEquipItem extends AbstractTestUnit {
    private Cleric cleric;

    @Test
    public void testEquipSpear() {
        testEquippingItem(spear, null);
    }

    @Test
    public void testEquipStaffDifferentName() {
        Staff otherStaff = new Staff("Other Staff", 10, 1, 2);
        testEquippingOtherItem(staff, otherStaff);
    }
    @Test
    public void testEquipStaffDifferentPower() {
        Staff otherStaff = new Staff("Staff", 20, 1, 2);
        testEquippingOtherItem(staff, otherStaff);
    }
    @Test
    public void testEquipStaffDifferentRange() {
        Staff otherStaff = new Staff("Staff", 10, 2, 3);
        testEquippingOtherItem(staff, otherStaff);
    }

    @Test
    public void testEquipStaff() {
        Staff otherStaff = new Staff("Staff", 10, 1, 2);
        testEquippingItem(staff, otherStaff);
    }

    @Test
    public void testEquipAxe() {
        testEquippingItem(axe, null);
    }

    @Test
    public void testEquipBow() {
        testEquippingItem(bow, null);
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
    public void testAddAndEquipStaff() {
        testAddAndEquip(staff);
    }

    @Test
    public void testTryEquipStaffWithFullInventory() {
        Staff otherItem = new Staff("Other Staff", 10, 1, 2);
        testTryEquipWithFullInventory(otherItem);
    }

    @Test
    public void testEquipFullInventoryWithEquippedItemStaff() {
        testEquipFullInventoryWithEquippedItem(staff);
    }

    @Test
    public void testAddItemWithOwnerCleric() {
        Cleric otherUnit = new Cleric(50, 2, field.getCell(0, 1));
        testAddItemWithOwner(otherUnit, staff);
    }

    @Test
    public void testEquipItemWithOwnerCleric() {
        Cleric otherUnit = new Cleric(50, 2, field.getCell(0, 1));
        testEquipItemWithOwner(otherUnit, staff);
    }

    @Test
    public void testOwnershipCleric() {
        testOwnership(staff);
    }

    @Override
    public void setTestUnit() {
        cleric = new Cleric(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return cleric;
    }
}