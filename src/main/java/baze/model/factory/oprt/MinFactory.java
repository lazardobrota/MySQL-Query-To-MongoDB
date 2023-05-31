package baze.model.factory.oprt;

import baze.model.implementation.operators.Min;
import baze.model.implementation.operators.Oprt;

public class MinFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Min();
    }
}
