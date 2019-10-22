package model.units;

import model.factory.item.IItemFactory;
import model.factory.item.StaffFactory;
import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public class Cleric extends AbstractUnit {
  private static IItemFactory staffFactory = new StaffFactory();

  /**
   * Creates a new Cleric unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the initial position of this unit
   * @param items
   *     the items carried by this unit
   */
  public Cleric(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Creates default Cleric unit:
   *  - 50 HP
   *  - Max. 2 cells per move
   *  - Default Staff in item list
   */
  public Cleric() {
    this(50, 2, null, staffFactory.getDefaultItem());
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    if (!(getItems().contains(item))) {
      List<IEquipableItem> itemsCopy = List.copyOf(getItems());
      if (addItemToInventory(item)) {
        item.equipOnCleric(this);
        if (getEquippedItem() == null) {
          setItemList(itemsCopy);
        }
      }
    }
    else {
      item.equipOnCleric(this);
    }
  }
}
