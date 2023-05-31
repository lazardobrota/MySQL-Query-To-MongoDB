package baze.model.factory.oprt;

import baze.model.implementation.operators.GreaterThan;
import baze.model.implementation.operators.Oprt;

public class GreaterThanFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        if (operation.matches(">"))
            return new GreaterThan(false);
        else
            return new GreaterThan(true); // >=
    }
}
