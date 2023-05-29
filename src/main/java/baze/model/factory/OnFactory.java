package baze.model.factory;

import baze.model.implementation.operators.On;
import baze.model.implementation.operators.Oprt;

public class OnFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operator) {
        return new On();
    }
}
