package baze.model.factory;

import baze.model.implementation.operators.Equals;
import baze.model.implementation.operators.Max;
import baze.model.implementation.operators.Oprt;

public class MaxFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Max();
    }
}
