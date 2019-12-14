package model.units;

import model.factory.item.IItemFactory;
import model.factory.item.SpearFactory;
import model.items.IEquipableItem;
import model.map.Location;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author  Raimundo Becerra Parra
 * @since 1.0
 */
public class Hero extends AbstractUnit {
  private static IItemFactory spearFactory = new SpearFactory();

  /**
   * Creates a new Hero unit.
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
  public Hero(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Creates default Hero unit:
   *  - 100 HP
   *  - Max. 2 cells per move
   *  - Default Spear in item list
   */
  public Hero() {
    this(100, 2, null, spearFactory.getDefaultItem());
  }

  @Override
  public void fireDeathOfUnitEvent() {
    getHeroDeathNotification().firePropertyChange(new PropertyChangeEvent(this, "Hero has died",
            null, null));
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
        item.equipOnHero(this);
        if (getEquippedItem() == null) {
          setItemList(itemsCopy);
        }
      }
    }
    else {
      item.equipOnHero(this);
    }
  }
}
