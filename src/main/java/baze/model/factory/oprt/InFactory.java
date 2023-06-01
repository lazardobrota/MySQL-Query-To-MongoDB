package baze.model.factory.oprt;

import baze.model.implementation.operators.In;
import baze.model.implementation.operators.Oprt;

public class InFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new In();
    }
}
