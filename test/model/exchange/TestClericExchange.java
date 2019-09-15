package model.exchange;

import model.units.*;
import org.junit.jupiter.api.Test;

public class TestClericExchange extends AbstractTestUnit {
    private Cleric cleric;

    @Test
    public void testExchangeEquippedItemArcher() {
        Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(staff, otherArcher);
    }

    @Test
    public void testExchangeEquippedItemSwordMaster() {
        SwordMaster otherSwordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(staff, otherSwordMaster);
    }

    @Test
    public void testExchangeEquippedItemCleric() {
        Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(staff, otherCleric);
    }

    @Test
    public void testExchangeEquippedItemFighter() {
        Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(staff, otherFighter);
    }

    @Test
    public void testExchangeEquippedItemHero() {
        Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(staff, otherHero);
    }

    @Test
    public void testExchangeEquippedItemAlpaca() {
        testExchangeEquippedItem(staff, targetAlpaca);
    }

    @Test
    public void testExchangeEquippedItemSorcerer() {
        Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(staff, otherSorcerer);
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
