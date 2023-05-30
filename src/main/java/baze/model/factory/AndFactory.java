package baze.model.factory;

import baze.model.implementation.operators.And;
import baze.model.implementation.operators.Oprt;

public class AndFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new And();
    }
}
