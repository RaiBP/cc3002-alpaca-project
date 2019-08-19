package model.items.weapons;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends AbstractWeapon {

  /**
   * Creates a new Spear item
   *
   * @param name
   *     the name of the Spear
   * @param power
   *     the damage of the Spear
   * @param minRange
   *     the minimum range of the Spear
   * @param maxRange
   *     the maximum range of the Spear
   */
  public Spear(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange, "Spear");
  }

  @Override
  public String getWeakness() {
    return "Axe";
  }

  @Override
  public String getStrength() {
    return "Sword";
  }

}
