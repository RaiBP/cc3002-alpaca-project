package model.items;

import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestSorcererEquipItem extends AbstractTestUnit {
    private Sorcerer sorcerer;

    @Test
    public void testEquipSpear() {
        testEquippingItem(spear, null);
    }

    @Test
    public void testEquipAnimaDifferentName() {
        Anima otherAnima = new Anima("Other Anima", 10, 1, 3);
        testEquippingOtherItem(anima, otherAnima);
    }
    @Test
    public void testEquipAnimaDifferentPower() {
        Anima otherAnima = new Anima("Anima", 20, 1, 3);
        testEquippingOtherItem(anima, otherAnima);
    }
    @Test
    public void testEquipAnimaDifferentRange() {
        Anima otherAnima = new Anima("Anima", 10, 2, 4);
        testEquippingOtherItem(anima, otherAnima);
    }

    @Test
    public void testEquipAnimaSpell() {
        Anima otherAnima = new Anima("Anima", 10, 1, 3);
        testEquippingItem(anima, otherAnima);
    }

    @Test
    public void testEquipLightDifferentName() {
        Light otherLight = new Light("Other Light", 10, 1, 3);
        testEquippingOtherItem(light, otherLight);
    }
    @Test
    public void testEquipLightDifferentPower() {
        Light otherLight = new Light("Light", 20, 1, 3);
        testEquippingOtherItem(light, otherLight);
    }
    @Test
    public void testEquipLightDifferentRange() {
        Light otherLight = new Light("Light", 10, 2, 4);
        testEquippingOtherItem(light, otherLight);
    }

    @Test
    public void testEquipLightSpell() {
        Light otherLight = new Light("Light", 10, 1, 3);
        testEquippingItem(light, otherLight);
    }

    @Test
    public void testEquipDarkDifferentName() {
        Dark otherDark = new Dark("Other Dark", 10, 1, 3);
        testEquippingOtherItem(dark, otherDark);
    }
    @Test
    public void testEquipDarkDifferentPower() {
        Dark otherDark = new Dark("Dark", 20, 1, 3);
        testEquippingOtherItem(dark, otherDark);
    }
    @Test
    public void testEquipDarkDifferentRange() {
        Dark otherDark = new Dark("Dark", 10, 2, 4);
        testEquippingOtherItem(dark, otherDark);
    }

    @Test
    public void testEquipDarkSpell() {
        Dark otherDark = new Dark("Dark", 10, 1, 3);
        testEquippingItem(dark, otherDark);
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
    public void testEquipSword() {
        testEquippingItem(sword, null);
    }

    @Test
    public void testEquipAxe() {
        testEquippingItem(axe, null);
    }

    @Test
    public void testAddAndEquipDark() {
        testAddAndEquip(dark);
    }

    @Test
    public void testAddAndEquipLight() {
        testAddAndEquip(light);
    }

    @Test
    public void testAddAndEquipAnima() {
        testAddAndEquip(anima);
    }

    @Test
    public void testEquipFullInventory() {
        getTestUnit().addItemToInventory(spear);
        getTestUnit().addItemToInventory(axe);
        getTestUnit().addItemToInventory(bow);
        getTestUnit().addItemToInventory(sword);

        assertArrayEquals(new IEquipableItem[] {spear, axe, bow},
                getTestUnit().getItems().toArray(new IEquipableItem[0]));
    }

    @Test
    public void testTryEquipDarkWithFullInventory() {
        Dark otherItem = new Dark("Other Dark", 10, 1, 3);
        testTryEquipWithFullInventory(otherItem);
    }

    @Test
    public void testEquipFullInventoryWithEquippedItemDark() {
        testEquipFullInventoryWithEquippedItem(dark);
    }

    @Test
    public void testAddItemWithOwnerDarkSorcerer() {
        Sorcerer otherUnit = new Sorcerer(50, 2, field.getCell(0, 1));
        testAddItemWithOwner(otherUnit, dark);
    }

    @Test
    public void testAddItemWithOwnerLightSorcerer() {
        Sorcerer otherUnit = new Sorcerer(50, 2, field.getCell(0, 1));
        testAddItemWithOwner(otherUnit, light);
    }

    @Test
    public void testAddItemWithOwnerAnimaSorcerer() {
        Sorcerer otherUnit = new Sorcerer(50, 2, field.getCell(0, 1));
        testAddItemWithOwner(otherUnit, anima);
    }

    @Test
    public void testEquipItemWithOwnerDarkSorcerer() {
        Sorcerer otherUnit = new Sorcerer(50, 2, field.getCell(0, 1));
        testEquipItemWithOwner(otherUnit, dark);
    }

    @Test
    public void testEquipItemWithOwnerLightSorcerer() {
        Sorcerer otherUnit = new Sorcerer(50, 2, field.getCell(0, 1));
        testEquipItemWithOwner(otherUnit, light);
    }

    @Test
    public void testEquipItemWithOwnerAnimaSorcerer() {
        Sorcerer otherUnit = new Sorcerer(50, 2, field.getCell(0, 1));
        testEquipItemWithOwner(otherUnit, anima);
    }


    @Test
    public void testOwnershipDark() {
        testOwnership(dark);
    }

    @Test
    public void testOwnershipLight() {
        testOwnership(light);
    }

    @Test
    public void testOwnershipAnima() {
        testOwnership(anima);
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