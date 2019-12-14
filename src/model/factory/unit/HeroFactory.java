package model.factory.unit;

import model.units.Hero;
import model.units.IUnit;

/**
 * Factory class for creating Hero type units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class HeroFactory extends AbstractUnitFactory {

    /**
     * @return default Hero type units
     */
    @Override
    public IUnit getDefaultUnit() {
        return new Hero();
    }
}
