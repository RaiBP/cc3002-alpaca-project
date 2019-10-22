package model.factory.unit;

import model.units.Hero;
import model.units.IUnit;

public class HeroFactory extends AbstractUnitFactory {
    @Override
    public IUnit getDefaultUnit() {
        return new Hero();
    }
}
