package model.units;

import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

  private Hero hero;

  @Test
  public void testEquipFullInventory() {
    getTestUnit().addItemToInventory(spear);
    getTestUnit().addItemToInventory(axe);
    getTestUnit().addItemToInventory(bow);
    getTestUnit().addItemToInventory(sword);

    assertArrayEquals(new IEquipableItem[] {spear, axe, bow},
            getTestUnit().getItems().toArray(new IEquipableItem[0]));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    hero = new Hero(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return hero;
  }

  @Override
  @Test
  public void equipSpearTest() {
    assertNull(hero.getEquippedItem());
    hero.equipItem(spear);
    assertEquals(spear, hero.getEquippedItem());
  }
}