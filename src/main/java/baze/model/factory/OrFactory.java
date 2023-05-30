package baze.model.factory;

import baze.model.implementation.operators.Oprt;
import baze.model.implementation.operators.Or;

public class OrFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Or();
    }
}
