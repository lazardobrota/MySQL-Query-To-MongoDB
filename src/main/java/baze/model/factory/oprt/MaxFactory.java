package baze.model.factory.oprt;

import baze.model.implementation.operators.Max;
import baze.model.implementation.operators.Oprt;

public class MaxFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Max();
    }
}
