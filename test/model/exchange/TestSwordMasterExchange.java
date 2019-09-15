package model.exchange;

import model.units.*;
import org.junit.jupiter.api.Test;

public class TestSwordMasterExchange extends AbstractTestUnit {
    private SwordMaster swordMaster;

    @Test
    public void testExchangeEquippedItemArcher() {
        Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(sword, otherArcher);
    }

    @Test
    public void testExchangeEquippedItemCleric() {
        Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(sword, otherCleric);
    }

    @Test
    public void testExchangeEquippedItemSwordMaster() {
        SwordMaster otherSwordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(sword, otherSwordMaster);
    }

    @Test
    public void testExchangeEquippedItemFighter() {
        Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(sword, otherFighter);
    }

    @Test
    public void testExchangeEquippedItemHero() {
        Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(sword, otherHero);
    }

    @Test
    public void testExchangeEquippedItemAlpaca() {
        testExchangeEquippedItem(sword, targetAlpaca);
    }

    @Test
    public void testExchangeEquippedItemSorcerer() {
        Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(sword, otherSorcerer);
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
