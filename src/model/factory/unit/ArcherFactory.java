package model.factory.unit;

import model.units.Archer;
import model.units.IUnit;

public class ArcherFactory extends AbstractUnitFactory {
    @Override
    public IUnit getDefaultUnit() {
        return new Archer();
    }
}
