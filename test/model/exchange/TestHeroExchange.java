package model.exchange;

import model.units.*;
import org.junit.jupiter.api.Test;

public class TestHeroExchange extends AbstractTestUnit {
    private Hero hero;

    @Test
    public void testExchangeEquippedItemArcher() {
        Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(spear, otherArcher);
    }

    @Test
    public void testExchangeEquippedItemSwordMaster() {
        SwordMaster otherSwordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(spear, otherSwordMaster);
    }

    @Test
    public void testExchangeEquippedItemCleric() {
        Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(spear, otherCleric);
    }

    @Test
    public void testExchangeEquippedItemFighter() {
        Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(spear, otherFighter);
    }

    @Test
    public void testExchangeEquippedItemHero() {
        Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(spear, otherHero);
    }

    @Test
    public void testExchangeEquippedItemAlpaca() {
        testExchangeEquippedItem(spear, targetAlpaca);
    }

    @Test
    public void testExchangeEquippedItemSorcerer() {
        Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(spear, otherSorcerer);
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
