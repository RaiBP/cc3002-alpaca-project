package model.items;

import model.items.weapons.Sword;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlpacaEquipItem extends AbstractTestUnit {
    private Alpaca alpaca;

    @Test
    public void testEquipSpear() {
        testEquippingItem(spear, null);
    }

    @Test
    public void testEquipSword() {
        testEquippingItem(sword, null);
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
        getTestUnit().addItemToInventory(spear);
        getTestUnit().addItemToInventory(axe);
        getTestUnit().equipItem(sword);
        getTestUnit().equipItem(spear);
        getTestUnit().equipItem(axe);
        getTestUnit().equipItem(staff);
        getTestUnit().equipItem(light);
        getTestUnit().equipItem(dark);
        getTestUnit().equipItem(anima);

        assertArrayEquals(new IEquipableItem[] {spear, axe},
                getTestUnit().getItems().toArray(new IEquipableItem[0]));
    }

    @Test
    public void testAddManyItemsToInventory() {
        getTestUnit().addItemToInventory(spear);
        getTestUnit().addItemToInventory(axe);
        getTestUnit().addItemToInventory(sword);
        getTestUnit().addItemToInventory(bow);
        getTestUnit().addItemToInventory(staff);
        getTestUnit().addItemToInventory(light);
        getTestUnit().addItemToInventory(dark);
        getTestUnit().addItemToInventory(anima);

        assertArrayEquals(new IEquipableItem[] {spear, axe, sword, bow, staff, light, dark, anima},
                getTestUnit().getItems().toArray(new IEquipableItem[0]));
    }

    @Test
    public void testAddItemWithOwnerAlpaca() {
        targetAlpaca.addItemToInventory(spear);
        targetAlpaca.addItemToInventory(axe);
        targetAlpaca.addItemToInventory(sword);
        targetAlpaca.addItemToInventory(bow);
        targetAlpaca.addItemToInventory(staff);
        targetAlpaca.addItemToInventory(dark);
        targetAlpaca.addItemToInventory(anima);
        targetAlpaca.addItemToInventory(light);

        getTestUnit().addItemToInventory(spear);
        getTestUnit().addItemToInventory(sword);
        getTestUnit().addItemToInventory(axe);
        getTestUnit().addItemToInventory(bow);
        getTestUnit().addItemToInventory(staff);
        getTestUnit().addItemToInventory(light);
        getTestUnit().addItemToInventory(dark);
        getTestUnit().addItemToInventory(anima);

        assertArrayEquals(new IEquipableItem[] {},
                getTestUnit().getItems().toArray(new IEquipableItem[0]));
    }

    @Test
    public void testOwnershipAlpaca() {
        getTestUnit().addItemToInventory(spear);
        getTestUnit().addItemToInventory(axe);
        getTestUnit().addItemToInventory(sword);
        getTestUnit().addItemToInventory(spear);
        getTestUnit().addItemToInventory(axe);
        getTestUnit().addItemToInventory(staff);
        getTestUnit().addItemToInventory(light);
        getTestUnit().addItemToInventory(dark);
        getTestUnit().addItemToInventory(anima);

        for (IEquipableItem item : getTestUnit().getItems().toArray(new IEquipableItem[0])) {
            assertEquals(item.getOwner(), alpaca);
        }
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