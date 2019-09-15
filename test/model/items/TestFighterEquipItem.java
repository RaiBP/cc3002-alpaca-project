package model.items;

import model.items.weapons.Axe;
import model.units.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestFighterEquipItem extends AbstractTestUnit {
    private Fighter fighter;

    @Test
    public void testEquipSpear() {
        testEquippingItem(spear, null);
    }

    @Test
    public void testEquipAxeDifferentName() {
        Axe otherAxe = new Axe("Other Axe", 10, 1, 2);
        testEquippingOtherItem(axe, otherAxe);
    }
    @Test
    public void testEquipAxeDifferentPower() {
        Axe otherAxe = new Axe("Axe", 20, 1, 2);
        testEquippingOtherItem(axe, otherAxe);
    }
    @Test
    public void testEquipAxeDifferentRange() {
        Axe otherAxe = new Axe("Axe", 10, 2, 3);
        testEquippingOtherItem(axe, otherAxe);
    }

    @Test
    public void testEquipAxe() {
        Axe otherAxe = new Axe("Axe", 10, 1, 2);
        testEquippingItem(axe, otherAxe);
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
    public void testAddAndEquipAxe() {
        Axe otherAxe = new Axe("Axe", 10, 1, 2);
        getTestUnit().addItemToInventory(spear);
        getTestUnit().addItemToInventory(axe);
        getTestUnit().equipItem(otherAxe);

        assertArrayEquals(new IEquipableItem[] {spear, axe},
                getTestUnit().getItems().toArray(new IEquipableItem[0]));
    }

    @Test
    public void testTryEquipAxeWithFullInventory() {
        Axe otherItem = new Axe("Other Axe", 10, 1, 2);
        testTryEquipWithFullInventory(otherItem);
    }

    @Test
    public void testEquipFullInventoryWithEquippedItemAxe() {
        Axe otherAxe = new Axe("Axe", 10, 1, 2);
        testEquipFullInventoryWithEquippedItem(otherAxe);
    }

    @Test
    public void testAddItemWithOwnerFighter() {
        Fighter otherUnit = new Fighter(50, 2, field.getCell(0, 1));
        testAddItemWithOwner(otherUnit, axe);
    }

    @Test
    public void testEquipItemWithOwnerFighter() {
        Fighter otherUnit = new Fighter(50, 2, field.getCell(0, 1));
        testEquipItemWithOwner(otherUnit, axe);
    }

    @Test
    public void testOwnershipFighter() {
        testOwnership(axe);
    }

    @Override
    public void setTestUnit() {
        fighter = new Fighter(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return fighter;
    }
}