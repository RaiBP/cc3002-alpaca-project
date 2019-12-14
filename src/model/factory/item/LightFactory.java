package model.factory.item;

import model.items.IEquipableItem;
import model.items.magic.Light;

/**
 * Factory class for creating Light type spells.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class LightFactory extends AbstractItemFactory {

    /**
     * @return default Light type spell
     */
    @Override
    public IEquipableItem getDefaultItem() {
        return new Light();
    }
}
