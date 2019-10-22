package model.factory.item;

import model.items.IEquipableItem;
import model.items.weapons.Spear;

public class SpearFactory extends AbstractItemFactory {

    @Override
    public IEquipableItem getDefaultItem() {
        return new Spear();
    }
}
