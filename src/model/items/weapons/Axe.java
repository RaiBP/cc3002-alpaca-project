package model.items.weapons;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak against swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends AbstractWeapon {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the Axe
   * @param minRange
   *     the minimum range of the Axe
   * @param maxRange
   *     the maximum range of the Axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange, "Axe");
  }

  @Override
  public String getWeakness() {
    return "Sword";
  }

  @Override
  public String getStrength() {
    return "Spear";
  }

}
