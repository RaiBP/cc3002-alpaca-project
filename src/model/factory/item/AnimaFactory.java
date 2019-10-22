package model.factory.item;

import model.items.IEquipableItem;
import model.items.magic.Anima;

public class AnimaFactory extends AbstractItemFactory {

    @Override
    public IEquipableItem getDefaultItem() {
        return new Anima();
    }
}
