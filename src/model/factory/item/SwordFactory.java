package model.factory.item;

import model.items.IEquipableItem;
import model.items.weapons.Sword;

public class SwordFactory extends AbstractItemFactory {

    @Override
    public IEquipableItem getDefaultItem() {
        return new Sword();
    }
}
