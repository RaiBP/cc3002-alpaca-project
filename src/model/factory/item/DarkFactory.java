package model.factory.item;

import model.items.IEquipableItem;
import model.items.magic.Dark;

/**
 * Factory class for creating Dark type spells.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class DarkFactory extends AbstractItemFactory {

    /**
     * @return default Dark type spells
     */
    @Override
    public IEquipableItem getDefaultItem() {
        return new Dark();
    }
}
