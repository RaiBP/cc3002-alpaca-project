package model.factory.item;

import model.items.IEquipableItem;
import model.items.weapons.Axe;

public class AxeFactory extends AbstractItemFactory {

    @Override
    public IEquipableItem getDefaultItem() {
        return new Axe();
    }
}
