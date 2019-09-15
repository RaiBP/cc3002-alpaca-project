package model.exchange;

import model.units.*;

public class TestAlpacaExchange extends AbstractTestUnit {
    private Alpaca alpaca;

    @Override
    public void setTestUnit() {
        alpaca = new Alpaca(50, 2, field.getCell(0, 0));
    }

    @Override
    public IUnit getTestUnit() {
        return alpaca;
    }
}
