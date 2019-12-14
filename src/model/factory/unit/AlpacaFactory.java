package model.factory.unit;

import model.units.Alpaca;
import model.units.IUnit;

/**
 * Factory class for creating Alpaca type units.
 *
 * @author Raimundo Becerra Parra
 * @version 2.0
 * @since 2.0
 */
public class AlpacaFactory extends AbstractUnitFactory {

    /**
     * @return default Alpaca type units
     */
    @Override
    public IUnit getDefaultUnit() {
        return new Alpaca();
    }
}
