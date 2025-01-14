package baze.model.factory.oprt;

import baze.model.implementation.operators.ColumnString;
import baze.model.implementation.operators.Oprt;

public class ColumnStringFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new ColumnString(operation);
    }
}
