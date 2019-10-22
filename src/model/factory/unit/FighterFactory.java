package model.factory.unit;

import model.units.Fighter;
import model.units.IUnit;

public class FighterFactory extends AbstractUnitFactory {

    @Override
    public IUnit getDefaultUnit() {
        return new Fighter();
    }
}
