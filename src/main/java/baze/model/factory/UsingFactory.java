package baze.model.factory;

import baze.model.implementation.operators.Oprt;
import baze.model.implementation.operators.Using;

public class UsingFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operator) {
        return new Using();
    }
}
