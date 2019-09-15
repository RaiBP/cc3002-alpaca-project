package model.exchange;

import model.units.*;
import org.junit.jupiter.api.Test;

public class TestFighterExchange extends AbstractTestUnit {
    private Fighter fighter;

    @Test
    public void testExchangeEquippedItemArcher() {
        Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(axe, otherArcher);
    }

    @Test
    public void testExchangeEquippedItemSwordMaster() {
        SwordMaster otherSwordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(axe, otherSwordMaster);
    }

    @Test
    public void testExchangeEquippedItemCleric() {
        Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(axe, otherCleric);
    }

    @Test
    public void testExchangeEquippedItemFighter() {
        Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(axe, otherFighter);
    }

    @Test
    public void testExchangeEquippedItemHero() {
        Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(axe, otherHero);
    }

    @Test
    public void testExchangeEquippedItemAlpaca() {
        testExchangeEquippedItem(axe, targetAlpaca);
    }

    @Test
    public void testExchangeEquippedItemSorcerer() {
        Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(axe, otherSorcerer);
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
