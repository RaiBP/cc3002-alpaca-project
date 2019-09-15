package model.units;

import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  @Test
  public void testEquipIncorrectItem() {
    getTestUnit().equipItem(axe);
    assertArrayEquals(new IEquipableItem[] {}, getTestUnit().getItems().toArray(new IEquipableItem[0]));
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

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipStaffTest() {
    assertNull(cleric.getEquippedItem());
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
  }
}