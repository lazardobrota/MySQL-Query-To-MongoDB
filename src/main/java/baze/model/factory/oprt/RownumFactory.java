package baze.model.factory.oprt;

import baze.model.implementation.operators.Oprt;
import baze.model.implementation.operators.Rownum;

public class RownumFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Rownum();
    }
}
