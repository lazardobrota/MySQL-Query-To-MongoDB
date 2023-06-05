package baze.model.factory.oprt;

import baze.model.implementation.operators.Oprt;
import baze.model.implementation.operators.agregation.Sum;

public class SumFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Sum();
    }
}
