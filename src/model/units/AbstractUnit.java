package model.units;

import static java.lang.Math.min;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.items.IEquipableItem;
import model.items.magic.Anima;
import model.items.magic.Dark;
import model.items.magic.Light;
import model.items.weapons.Axe;
import model.items.weapons.Bow;
import model.items.weapons.Spear;
import model.items.weapons.Sword;
import model.map.Location;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Raimundo Becerra Parra
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

  protected List<IEquipableItem> items = new ArrayList<>();
  private final int maxHitPoints;
  private int currentHitPoints;
  private final int movement;
  protected IEquipableItem equippedItem;
  private Location location;
  
  private PropertyChangeSupport
          unitDeathNotification = new PropertyChangeSupport(this),
          heroDeathNotification = new PropertyChangeSupport(this);

  /**
   * Creates a new Unit.
   *
   * @param hitPoints
   *     the maximum amount of damage a unit can sustain
   * @param movement
   *     the number of panels a unit can move
   * @param location
   *     the current position of this unit on the map
   * @param maxItems
   *     maximum amount of items this unit can carry
   */
  protected AbstractUnit(final int hitPoints, final int movement,
      final Location location, final int maxItems, final IEquipableItem... items) {
    this.currentHitPoints = hitPoints;
    this.maxHitPoints = hitPoints;
    this.movement = movement;
    this.location = location;
    this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    this.items.forEach(item -> item.setOwner(this));
  }

  public void addUnitDeathListener(PropertyChangeListener listener) {
    this.unitDeathNotification.addPropertyChangeListener(listener);
  }

  public void removeUnitDeathListener(PropertyChangeListener listener) {
    this.unitDeathNotification.removePropertyChangeListener(listener);
  }

  @Override
  public void addHeroDeathListener(PropertyChangeListener listener) {
    this.heroDeathNotification.addPropertyChangeListener(listener);
  }

  @Override
  public void removeHeroDeathListener(PropertyChangeListener listener) {
    this.heroDeathNotification.removePropertyChangeListener(listener);
  }

  @Override
  public PropertyChangeSupport getUnitDeathNotification() {
    return unitDeathNotification;
  }

  @Override
  public PropertyChangeSupport getHeroDeathNotification() {
    return heroDeathNotification;
  }

  @Override
  public int getMaxHitPoints() {
    return maxHitPoints;
  }

  @Override
  public int getCurrentHitPoints() {
    return currentHitPoints;
  }

  @Override
  public boolean isAlive() { return currentHitPoints > 0; }

  @Override
  public void setHitPoints(int hitPoints) {
    hitPoints = Math.min(maxHitPoints, hitPoints);
    this.currentHitPoints = Math.max(0, hitPoints);
    if (this.currentHitPoints == 0) {
      fireDeathOfUnitEvent();
    }
  }

  public void fireDeathOfUnitEvent() {
    unitDeathNotification.firePropertyChange(new PropertyChangeEvent(this, "Unit has died",
            null, null));
  }

  @Override
  public List<IEquipableItem> getItems() {
    return List.copyOf(items);
  }

  @Override
  public IEquipableItem getEquippedItem() {
    return equippedItem;
  }

  @Override
  public void setEquippedItem(final IEquipableItem item) {
    this.equippedItem = item;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(final Location location) {
    this.location = location;
  }

  @Override
  public int getMovement() {
    return movement;
  }

  @Override
  public boolean moveTo(final Location targetLocation) {
    if (getLocation().distanceTo(targetLocation) <= getMovement()
        && targetLocation.getUnit() == null) {
      setLocation(targetLocation);
      return true;
    }
    return false;
  }

  @Override
  public int receiveAxeAttack(Axe axe) {
    IEquipableItem item = getEquippedItem();
    if (item == null) {
      return axe.getPower();
    }
    return item.getDamageFromAxeAttack(axe);
  }

  @Override
  public int receiveBowAttack(Bow bow) {
    IEquipableItem item = getEquippedItem();
    if (item == null) {
      return bow.getPower();
    }
    return item.getDamageFromBowAttack(bow);
  }

  @Override
  public int receiveSpearAttack(Spear spear) {
    IEquipableItem item = getEquippedItem();
    if (item == null) {
      return spear.getPower();
    }
    return item.getDamageFromSpearAttack(spear);
  }

  @Override
  public int receiveSwordAttack(Sword sword) {
    IEquipableItem item = getEquippedItem();
    if (item == null) {
      return sword.getPower();
    }
    return item.getDamageFromSwordAttack(sword);
  }

  @Override
  public int receiveAnimaSpellAttack(Anima anima) {
    IEquipableItem item = getEquippedItem();
    if (item == null) {
      return anima.getPower();
    }
    return item.getDamageFromAnimaSpellAttack(anima);
  }

  @Override
  public int receiveDarkSpellAttack(Dark dark) {
    IEquipableItem item = getEquippedItem();
    if (item == null) {
      return dark.getPower();
    }
    return item.getDamageFromDarkSpellAttack(dark);
  }

  @Override
  public int receiveLightSpellAttack(Light light) {
    IEquipableItem item = getEquippedItem();
    if (item == null) {
      return light.getPower();
    }
    return item.getDamageFromLightSpellAttack(light);
  }

  @Override
  public int getDamage(IUnit targetUnit) {
    IEquipableItem item = getEquippedItem();
    double distance = getLocation().distanceTo(targetUnit.getLocation());

    if (item == null) {
      return -1;
    }
    else if (item.isOutOfRange(distance)) {
      return -1;
    }
    else
      return item.getDamageFromAttack(targetUnit);
  }

  protected int attack(IUnit targetUnit){
    int damage = getDamage(targetUnit);
    if (damage >= 0) {
      targetUnit.setHitPoints(targetUnit.getCurrentHitPoints()  - damage);
    }
    return damage;
  }

  public void doCombat(IUnit targetUnit) {
    AbstractUnit abstractUnit = (AbstractUnit) targetUnit;
    if (attack(abstractUnit) < 0) {
      return;
    }
    if (abstractUnit.isAlive()) {
      abstractUnit.attack(this);
    }
  }

  /**
   * {@inheritDoc}
   * <p>
   * All units, except the Alpaca, can add up to 3 items to their inventory
   */
  @Override
  public boolean addItemToInventory(IEquipableItem item) {
    if (getItems().size() < 3 && item != null && item.getOwner() == null) {
      List<IEquipableItem> list = new ArrayList<>(getItems());
      list.add(item);
      setItemList(list);
      item.setOwner(this);
      return true;
    }
    return false;
  }

  public void giveItem(IUnit unit, IEquipableItem item) {
    if (item == null) {
      return;
    }
    if (getItems().contains(item)) {
      ArrayList<IEquipableItem> itemList = new ArrayList<>(getItems());

      if (getEquippedItem() == item && Collections.frequency(itemList, item) == 1) {
        setEquippedItem(null);
      }

      item.setOwner(null);
      unit.addItemToInventory(item);
      itemList.remove(item);
      setItemList(itemList);
    }
  }

  public void setItemList(List<IEquipableItem> list) {
    items = list;
  }

  public void useEquippedItemOn(IUnit targetUnit) {
    IEquipableItem item = getEquippedItem();
    if (item != null) {
      item.useOn(targetUnit);
    }
  }

  /**
   * Two objects can be equal regardless of their ownership
   */
  @Override
  public boolean equals(Object object){
    if (object instanceof AbstractUnit) {
      AbstractUnit unit = (AbstractUnit) object;
      return unit.maxHitPoints == maxHitPoints && unit.movement == movement && unit.items.equals(items);
    }
    return false;
  }

}
