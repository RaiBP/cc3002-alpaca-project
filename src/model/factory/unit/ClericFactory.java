package model.factory.unit;

import model.units.Cleric;
import model.units.IUnit;

public class ClericFactory extends AbstractUnitFactory {
    @Override
    public IUnit getDefaultUnit() {
        return new Cleric();
    }
}
