package model.units;

import model.items.*;
import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.items.tools.Staff;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Field;
import model.map.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Bow bow;
  protected Field field;
  protected Axe axe;
  protected Sword sword;
  protected Staff staff;
  protected Spear spear;
  protected Anima anima;
  protected Dark dark;
  protected Light light;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
    this.dark = new Dark("Dark", 10, 1, 3);
    this.anima = new Anima("Anima", 10, 1, 3);
    this.light = new Light("Light", 10, 1, 3);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItem(item);
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }

  public void testEquippingItem(IEquipableItem item, IEquipableItem expectedItem) {
    IUnit unit = getTestUnit();

    assertNull(unit.getEquippedItem());

    unit.equipItem(item);

    assertEquals(expectedItem, unit.getEquippedItem());
  }

  public void testEquippingOtherItem(IEquipableItem item, IEquipableItem otherItem) {
    IUnit unit = getTestUnit();

    assertNull(unit.getEquippedItem());

    unit.equipItem(item);

    assertNotEquals(otherItem, unit.getEquippedItem());
  }

  public void testAddAndEquip(IEquipableItem mainItem) {
    getTestUnit().addItemToInventory(spear);
    getTestUnit().addItemToInventory(axe);
    getTestUnit().equipItem(mainItem);

    assertArrayEquals(new IEquipableItem[] {spear, axe, mainItem},
            getTestUnit().getItems().toArray(new IEquipableItem[0]));
  }

  public void testTryEquipWithFullInventory(IEquipableItem mainItem) {
    getTestUnit().addItemToInventory(bow);
    getTestUnit().addItemToInventory(axe);
    getTestUnit().addItemToInventory(sword);
    getTestUnit().equipItem(mainItem);

    assertArrayEquals(new IEquipableItem[] {bow, axe, sword},
            getTestUnit().getItems().toArray(new IEquipableItem[0]));

    assertNull(getTestUnit().getEquippedItem());
  }

  public void testEquipFullInventoryWithEquippedItem(IEquipableItem mainItem) {
    getTestUnit().equipItem(mainItem);
    getTestUnit().addItemToInventory(axe);
    getTestUnit().addItemToInventory(bow);
    getTestUnit().addItemToInventory(sword);

    assertArrayEquals(new IEquipableItem[] {mainItem, axe, bow},
            getTestUnit().getItems().toArray(new IEquipableItem[0]));
  }

  public void testAddItemWithOwner(IUnit otherUnit, IEquipableItem mainItem) {
    otherUnit.equipItem(mainItem);

    getTestUnit().addItemToInventory(mainItem);

    assertArrayEquals(new IEquipableItem[] {},
            getTestUnit().getItems().toArray(new IEquipableItem[0]));
  }

  public void testEquipItemWithOwner(IUnit otherUnit, IEquipableItem mainItem) {
    otherUnit.equipItem(mainItem);

    getTestUnit().equipItem(mainItem);

    assertNull(getTestUnit().getEquippedItem());
  }

  public void testOwnership(IEquipableItem mainItem) {
    getTestUnit().equipItem(mainItem);

    assertEquals(getTestUnit().getEquippedItem().getOwner(), getTestUnit());
  }

  public void testExchangeEquippedItem(IEquipableItem item, IUnit receivingUnit) {
    getTestUnit().equipItem(item);

    getTestUnit().giveItem(receivingUnit, item);
    assertNull(getTestUnit().getEquippedItem());

    assertArrayEquals(new IEquipableItem[] {}, getTestUnit().getItems().toArray(new IEquipableItem[0]));
    assertArrayEquals(new IEquipableItem[] {item}, receivingUnit.getItems().toArray(new IEquipableItem[0]));

    receivingUnit.setItemList(Collections.emptyList());
  }

  public void testExchangeItemInInventory(IEquipableItem item, IUnit receivingUnit) {
    getTestUnit().addItemToInventory(item);

    getTestUnit().giveItem(receivingUnit, item);

    assertArrayEquals(new IEquipableItem[] {}, getTestUnit().getItems().toArray(new IEquipableItem[0]));
    assertArrayEquals(new IEquipableItem[] {item}, receivingUnit.getItems().toArray(new IEquipableItem[0]));

    receivingUnit.setItemList(Collections.emptyList());
  }

  @Test
  public void testExchangeItemInInventoryArcher() {
    Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
    testExchangeItemInInventory(bow, otherArcher);
    testExchangeItemInInventory(axe, otherArcher);
    testExchangeItemInInventory(spear, otherArcher);
    testExchangeItemInInventory(sword, otherArcher);
    testExchangeItemInInventory(staff, otherArcher);
    testExchangeItemInInventory(dark, otherArcher);
    testExchangeItemInInventory(light, otherArcher);
    testExchangeItemInInventory(anima, otherArcher);
  }

  @Test
  public void testExchangeItemInInventorySwordMaster() {
    SwordMaster otherSwordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
    testExchangeItemInInventory(bow, otherSwordMaster);
    testExchangeItemInInventory(axe, otherSwordMaster);
    testExchangeItemInInventory(spear, otherSwordMaster);
    testExchangeItemInInventory(sword, otherSwordMaster);
    testExchangeItemInInventory(staff, otherSwordMaster);
    testExchangeItemInInventory(dark, otherSwordMaster);
    testExchangeItemInInventory(light, otherSwordMaster);
    testExchangeItemInInventory(anima, otherSwordMaster);
  }

  @Test
  public void testExchangeItemInInventoryCleric() {
    Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
    testExchangeItemInInventory(bow, otherCleric);
    testExchangeItemInInventory(axe, otherCleric);
    testExchangeItemInInventory(spear, otherCleric);
    testExchangeItemInInventory(sword, otherCleric);
    testExchangeItemInInventory(staff, otherCleric);
    testExchangeItemInInventory(dark, otherCleric);
    testExchangeItemInInventory(light, otherCleric);
    testExchangeItemInInventory(anima, otherCleric);
  }

  @Test
  public void testExchangeItemInInventoryFighter() {
    Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
    testExchangeItemInInventory(bow, otherFighter);
    testExchangeItemInInventory(axe, otherFighter);
    testExchangeItemInInventory(spear, otherFighter);
    testExchangeItemInInventory(sword, otherFighter);
    testExchangeItemInInventory(staff, otherFighter);
    testExchangeItemInInventory(dark, otherFighter);
    testExchangeItemInInventory(light, otherFighter);
    testExchangeItemInInventory(anima, otherFighter);
  }

  @Test
  public void testExchangeItemInInventoryHero() {
    Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
    testExchangeItemInInventory(bow, otherHero);
    testExchangeItemInInventory(axe, otherHero);
    testExchangeItemInInventory(spear, otherHero);
    testExchangeItemInInventory(sword, otherHero);
    testExchangeItemInInventory(staff, otherHero);
    testExchangeItemInInventory(dark, otherHero);
    testExchangeItemInInventory(light, otherHero);
    testExchangeItemInInventory(anima, otherHero);
  }

  @Test
  public void testExchangeItemInInventoryAlpaca() {
    testExchangeItemInInventory(bow, targetAlpaca);
    testExchangeItemInInventory(axe, targetAlpaca);
    testExchangeItemInInventory(spear, targetAlpaca);
    testExchangeItemInInventory(sword, targetAlpaca);
    testExchangeItemInInventory(staff, targetAlpaca);
    testExchangeItemInInventory(dark, targetAlpaca);
    testExchangeItemInInventory(light, targetAlpaca);
    testExchangeItemInInventory(anima, targetAlpaca);
  }

  @Test
  public void testExchangeItemInInventorySorcerer() {
    Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
    testExchangeItemInInventory(bow, otherSorcerer);
    testExchangeItemInInventory(axe, otherSorcerer);
    testExchangeItemInInventory(spear, otherSorcerer);
    testExchangeItemInInventory(sword, otherSorcerer);
    testExchangeItemInInventory(staff, otherSorcerer);
    testExchangeItemInInventory(dark, otherSorcerer);
    testExchangeItemInInventory(light, otherSorcerer);
    testExchangeItemInInventory(anima, otherSorcerer);
  }

  public void testExchangeSameItemInInventory(IEquipableItem firstItem, IEquipableItem secondItem, IUnit receivingUnit) {
    getTestUnit().addItemToInventory(firstItem);
    getTestUnit().addItemToInventory(secondItem);

    getTestUnit().giveItem(receivingUnit, firstItem);


    assertArrayEquals(new IEquipableItem[] {secondItem}, getTestUnit().getItems().toArray(new IEquipableItem[0]));
    assertArrayEquals(new IEquipableItem[] {firstItem}, receivingUnit.getItems().toArray(new IEquipableItem[0]));

    receivingUnit.setItemList(Collections.emptyList());
    getTestUnit().setItemList(Collections.emptyList());
  }

  public void testExchangeSameItemInInventoryAllItems(IUnit unit) {
    Axe otherAxe = new Axe("Axe", 10, 1, 2);
    Sword otherSword = new Sword("Sword", 10, 1, 2);
    Spear otherSpear = new Spear("Spear", 10, 1, 2);
    Staff otherStaff = new Staff("Staff", 10, 1, 2);
    Bow otherBow = new Bow("Bow", 10, 2, 3);
    Dark otherDark = new Dark("Dark", 10, 1, 3);
    Anima otherAnima = new Anima("Anima", 10, 1, 3);
    Light otherLight = new Light("Light", 10, 1, 3);


    testExchangeSameItemInInventory(bow, otherBow, unit);
    testExchangeSameItemInInventory(axe, otherAxe, unit);
    testExchangeSameItemInInventory(spear, otherSpear, unit);
    testExchangeSameItemInInventory(sword, otherSword, unit);
    testExchangeSameItemInInventory(staff, otherStaff, unit);
    testExchangeSameItemInInventory(dark, otherDark, unit);
    testExchangeSameItemInInventory(light, otherLight, unit);
    testExchangeSameItemInInventory(anima, otherAnima, unit);
  }

  @Test
  public void testExchangeSameItemInInventorySorcerer() {
    Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
    testExchangeSameItemInInventoryAllItems(otherSorcerer);
  }

  @Test
  public void testExchangeSameItemInInventoryHero() {
    Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
    testExchangeSameItemInInventoryAllItems(otherHero);
  }

  @Test
  public void testExchangeSameItemInInventoryFighter() {
    Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
    testExchangeSameItemInInventoryAllItems(otherFighter);
  }

  @Test
  public void testExchangeSameItemInInventoryCleric() {
    Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
    testExchangeSameItemInInventoryAllItems(otherCleric);
  }

  @Test
  public void testExchangeSameItemInInventoryAlpaca() {
    Alpaca otherAlpaca = new Alpaca(50, 2, field.getCell(0, 1));
    testExchangeSameItemInInventoryAllItems(otherAlpaca);
  }

  @Test
  public void testExchangeSameItemInInventoryArcher() {
    Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
    testExchangeSameItemInInventoryAllItems(otherArcher);
  }

  @Test
  public void testExchangeSameItemInInventorySwordMaster() {
    SwordMaster otherSwordMaster = new SwordMaster(50, 2, field.getCell(0, 1));
    testExchangeSameItemInInventoryAllItems(otherSwordMaster);
  }

  public void testEquipItemWithOwner(IEquipableItem item, IUnit otherUnit) {
    item.setOwner(otherUnit);

    getTestUnit().equipItem(item);

    assertNull(getTestUnit().getEquippedItem());
  }

  public void testEquipItemWithOwnerAllItems(IUnit unit) {
    testEquipItemWithOwner(bow, unit);
    testEquipItemWithOwner(axe, unit);
    testEquipItemWithOwner(spear, unit);
    testEquipItemWithOwner(sword, unit);
    testEquipItemWithOwner(staff, unit);
    testEquipItemWithOwner(dark, unit);
    testEquipItemWithOwner(light, unit);
    testEquipItemWithOwner(anima, unit);
  }

  @Test
  public void testEquipItemWithOwnerAllUnits() {
    Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
    SwordMaster otherSwordMaster= new SwordMaster(50, 2, field.getCell(0, 1));
    Alpaca otherAlpaca = new Alpaca(50, 2, field.getCell(0, 1));
    Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
    Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
    Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
    Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
    testEquipItemWithOwnerAllItems(otherArcher);
    testEquipItemWithOwnerAllItems(otherSwordMaster);
    testEquipItemWithOwnerAllItems(otherAlpaca);
    testEquipItemWithOwnerAllItems(otherCleric);
    testEquipItemWithOwnerAllItems(otherSorcerer);
    testEquipItemWithOwnerAllItems(otherFighter);
    testEquipItemWithOwnerAllItems(otherHero);
  }

  @Test
  public void testAddNullItemToInventory() {
    getTestUnit().addItemToInventory(axe);
    getTestUnit().addItemToInventory(null);

    assertArrayEquals(new IEquipableItem[] {axe}, getTestUnit().getItems().toArray(new IEquipableItem[0]));
  }


  public void exchangeNullItem(IUnit unit) {
    getTestUnit().giveItem(unit, null);

    assertArrayEquals(new IEquipableItem[] {}, unit.getItems().toArray(new IEquipableItem[0]));
  }

  @Test
  public void testExchangeNullItemAllUnits() {
    Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
    SwordMaster otherSwordMaster= new SwordMaster(50, 2, field.getCell(0, 1));
    Alpaca otherAlpaca = new Alpaca(50, 2, field.getCell(0, 1));
    Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
    Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
    Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
    Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
    exchangeNullItem(otherArcher);
    exchangeNullItem(otherSwordMaster);
    exchangeNullItem(otherAlpaca);
    exchangeNullItem(otherCleric);
    exchangeNullItem(otherSorcerer);
    exchangeNullItem(otherFighter);
    exchangeNullItem(otherHero);
  }

  public void equipItemAlreadyInInventory(IUnit unit, IEquipableItem item) {
    unit.addItemToInventory(item);
    unit.equipItem(item);

    assertArrayEquals(new IEquipableItem[] {item}, unit.getItems().toArray(new IEquipableItem[0]));

    unit.setItemList(Collections.emptyList());
  }

  @Test
  public void testEquipItemAlreadyInInventoryAllUnits() {
    Archer otherArcher = new Archer(50, 2, field.getCell(0, 1));
    SwordMaster otherSwordMaster= new SwordMaster(50, 2, field.getCell(0, 1));
    Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 1));
    Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 1));
    Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 1));
    Hero otherHero = new Hero(50, 2, field.getCell(0, 1));
    equipItemAlreadyInInventory(otherArcher, bow);
    equipItemAlreadyInInventory(otherSwordMaster, sword);
    equipItemAlreadyInInventory(otherCleric, staff);
    equipItemAlreadyInInventory(otherSorcerer, dark);
    equipItemAlreadyInInventory(otherSorcerer, light);
    equipItemAlreadyInInventory(otherSorcerer, anima);
    equipItemAlreadyInInventory(otherFighter, axe);
    equipItemAlreadyInInventory(otherHero, spear);
  }

  public void testUseOn(IEquipableItem attackingItem, IUnit targetUnit,
                        int targetHPBeforeAttack, int targetHPAfterAttack) {

    getTestUnit().equipItem(attackingItem);

    targetUnit.setHitPoints(targetHPBeforeAttack);

    getTestUnit().useEquippedItemOn(targetUnit);

    assertEquals(targetHPAfterAttack, targetUnit.getCurrentHitPoints());

  }

  public void testUseOnAllUnits(IEquipableItem item, int targetHPBeforeAttack, int targetHPAfterAttack) {
    Archer otherArcher = new Archer(50, 2, field.getCell(0, 2));
    SwordMaster otherSwordMaster= new SwordMaster(50, 2, field.getCell(0, 2));
    Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 2));
    Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 2));
    Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 2));
    Hero otherHero = new Hero(50, 2, field.getCell(0, 2));
    testUseOn(item, otherArcher, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOn(item, otherSwordMaster, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOn(item, otherCleric, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOn(item, otherSorcerer, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOn(item, otherSorcerer, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOn(item, otherSorcerer, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOn(item, otherFighter, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOn(item, otherHero, targetHPBeforeAttack, targetHPAfterAttack);
  }

  public void testUseOnLowHPTarget(IEquipableItem attackingItem,
                                  IUnit targetUnit, IEquipableItem targetItem, int targetHPBeforeAttack, int targetHPAfterAttack) {

    getTestUnit().equipItem(attackingItem);
    targetUnit.equipItem(targetItem);

    targetUnit.setHitPoints(targetHPBeforeAttack);

    getTestUnit().useEquippedItemOn(targetUnit);

    assertEquals(targetHPAfterAttack, targetUnit.getCurrentHitPoints());
    assertEquals(50, getTestUnit().getCurrentHitPoints());
  }

  public void testUseOnLowHPAllUnits(IEquipableItem item) {
    Archer otherArcher = new Archer(50, 2, field.getCell(0, 2));
    SwordMaster otherSwordMaster= new SwordMaster(50, 2, field.getCell(0, 2));
    Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 2));
    Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 2));
    Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 2));
    Hero otherHero = new Hero(50, 2, field.getCell(0, 2));
    targetAlpaca.setLocation(field.getCell(0, 2));
    int targetHPBeforeAttack = 10;
    int targetHPAfterAttack = 0;
    testUseOnLowHPTarget(item, otherArcher, bow, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOnLowHPTarget(item, otherSwordMaster, sword, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOnLowHPTarget(item, otherCleric, staff, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOnLowHPTarget(item, otherSorcerer, dark, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOnLowHPTarget(item, otherSorcerer, light, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOnLowHPTarget(item, otherSorcerer, anima, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOnLowHPTarget(item, otherFighter, axe, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOnLowHPTarget(item, otherHero, spear, targetHPBeforeAttack, targetHPAfterAttack);
    testUseOnLowHPTarget(item, targetAlpaca, null,  targetHPBeforeAttack, targetHPAfterAttack);
  }

  public void testUseOnOutOfRangeTarget(IEquipableItem attackingItem,
                                   IUnit targetUnit, int targetHPBeforeAttack) {

    getTestUnit().equipItem(attackingItem);

    targetUnit.setHitPoints(targetHPBeforeAttack);

    getTestUnit().useEquippedItemOn(targetUnit);

    assertEquals(targetHPBeforeAttack, targetUnit.getCurrentHitPoints());
  }

  public void testUseOnOutOfRangeTargetAllUnits(IEquipableItem item) {
    field.addCells(true, new Location(0, 1), new Location(0, 2),
            new Location(0, 3), new Location(0, 4), new Location(0, 5),
            new Location(0, 6), new Location(0, 7));
    Archer otherArcher = new Archer(50, 2, field.getCell(0, 7));
    SwordMaster otherSwordMaster= new SwordMaster(50, 2, field.getCell(0, 7));
    Cleric otherCleric = new Cleric(50, 2, field.getCell(0, 7));
    Sorcerer otherSorcerer = new Sorcerer(50, 2, field.getCell(0, 7));
    Fighter otherFighter = new Fighter(50, 2, field.getCell(0, 7));
    Hero otherHero = new Hero(50, 2, field.getCell(0, 7));
    targetAlpaca.setLocation(field.getCell(0, 7));
    int targetHPBeforeAttack = 30;
    testUseOnOutOfRangeTarget(item, otherArcher, targetHPBeforeAttack);
    testUseOnOutOfRangeTarget(item, otherSwordMaster, targetHPBeforeAttack);
    testUseOnOutOfRangeTarget(item, otherCleric, targetHPBeforeAttack);
    testUseOnOutOfRangeTarget(item, otherSorcerer, targetHPBeforeAttack);
    testUseOnOutOfRangeTarget(item, otherSorcerer, targetHPBeforeAttack);
    testUseOnOutOfRangeTarget(item, otherSorcerer, targetHPBeforeAttack);
    testUseOnOutOfRangeTarget(item, otherFighter, targetHPBeforeAttack);
    testUseOnOutOfRangeTarget(item, otherHero, targetHPBeforeAttack);
    testUseOnOutOfRangeTarget(item, targetAlpaca, targetHPBeforeAttack);
  }

  public void testEquipIncorrectItem(IEquipableItem item) {
    getTestUnit().equipItem(item);
    assertArrayEquals(new IEquipableItem[] {}, getTestUnit().getItems().toArray(new IEquipableItem[0]));
  }



}
