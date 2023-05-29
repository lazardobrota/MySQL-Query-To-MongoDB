package baze.model.factory;

import baze.model.implementation.operators.LowerThan;
import baze.model.implementation.operators.Oprt;

public class LowerThanFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operator) {
        return new LowerThan(operator);
    }
}
