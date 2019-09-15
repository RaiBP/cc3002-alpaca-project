package model.exchange;

import model.units.*;
import org.junit.jupiter.api.Test;

public class TestSorcererExchange extends AbstractTestUnit {
    private Sorcerer sorcerer;

    @Test
    public void testExchangeEquippedItemArcher() {
        Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(light, otherArcher);
        testExchangeEquippedItem(dark, otherArcher);
        testExchangeEquippedItem(anima, otherArcher);
    }

    @Test
    public void testExchangeEquippedItemSwordMaster() {
        SwordMaster otherSwordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(dark, otherSwordMaster);
        testExchangeEquippedItem(anima, otherSwordMaster);
        testExchangeEquippedItem(light, otherSwordMaster);
    }

    @Test
    public void testExchangeEquippedItemCleric() {
        Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(light, otherCleric);
        testExchangeEquippedItem(dark, otherCleric);
        testExchangeEquippedItem(anima, otherCleric);
    }

    @Test
    public void testExchangeEquippedItemFighter() {
        Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(light, otherFighter);
        testExchangeEquippedItem(dark, otherFighter);
        testExchangeEquippedItem(anima, otherFighter);
    }

    @Test
    public void testExchangeEquippedItemHero() {
        Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(dark, otherHero);
        testExchangeEquippedItem(light, otherHero);
        testExchangeEquippedItem(anima, otherHero);
    }

    @Test
    public void testExchangeEquippedItemAlpaca() {
        testExchangeEquippedItem(light, targetAlpaca);
        testExchangeEquippedItem(dark, targetAlpaca);
        testExchangeEquippedItem(anima, targetAlpaca);
    }

    @Test
    public void testExchangeEquippedItemSorcerer() {
        Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
        testExchangeEquippedItem(light, otherSorcerer);
        testExchangeEquippedItem(dark, otherSorcerer);
        testExchangeEquippedItem(anima, otherSorcerer);
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
