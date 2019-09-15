package model.units;

import model.items.IEquipableItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

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
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
  }
}