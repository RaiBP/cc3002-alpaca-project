package model.units;

import model.factory.item.IItemFactory;
import model.factory.item.SwordFactory;
import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public class SwordMaster extends AbstractUnit {
  private static IItemFactory swordFactory = new SwordFactory();

  /**
   * Creates a new SwordMaster unit.
   *
   * @param hitPoints
   *     maximum hit points of the unit
   * @param movement
   *     the amount of cells this unit can move
   * @param location
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public SwordMaster(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Creates default SwordMaster unit:
   *  - 75 HP
   *  - Max. 2 cells per move
   *  - Default Sword in item list
   */
  public SwordMaster() {
    this(75, 2, null, swordFactory.getDefaultItem());
  }

    /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(IEquipableItem item) {
    if (item == null) {
      equippedItem = null;
      return;
    }
    if (!(getItems().contains(item))) {
      List<IEquipableItem> itemsCopy = List.copyOf(getItems());
      if (addItemToInventory(item)) {
        item.equipOnSwordMaster(this);
        if (getEquippedItem() == null) {
          setItemList(itemsCopy);
        }
      }
    }
    else {
      item.equipOnSwordMaster(this);
    }
  }

}
