package model.units;

import model.items.IEquipableItem;
import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.items.weapons.Axe;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Location;

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
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(IEquipableItem item) {
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
