package baze.model.factory.oprt;

import baze.model.implementation.operators.Equals;
import baze.model.implementation.operators.Oprt;

public class EqualsFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Equals();
    }
}
