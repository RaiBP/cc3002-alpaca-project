package model.factory.unit;

import model.units.Fighter;
import model.units.IUnit;

/**
 * Factory class for creating Fighter type units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class FighterFactory extends AbstractUnitFactory {

    /**
     * @return default Fighter type units
     */
    @Override
    public IUnit getDefaultUnit() {
        return new Fighter();
    }
}
