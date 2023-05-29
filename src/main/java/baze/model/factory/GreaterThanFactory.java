package baze.model.factory;

import baze.model.implementation.operators.GreaterThan;
import baze.model.implementation.operators.Oprt;

public class GreaterThanFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operator) {
        return new GreaterThan(operator);
    }
}
