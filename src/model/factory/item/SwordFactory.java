package model.factory.item;

import model.items.IEquipableItem;
import model.items.weapons.Sword;

/**
 * Factory class for creating Sword type weapons.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class SwordFactory extends AbstractItemFactory {

    /**
     * @return default Sword type weapon
     */
    @Override
    public IEquipableItem getDefaultItem() {
        return new Sword();
    }
}
