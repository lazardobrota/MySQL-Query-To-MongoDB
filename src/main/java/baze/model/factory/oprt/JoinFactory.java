package baze.model.factory.oprt;

import baze.model.implementation.operators.Join;
import baze.model.implementation.operators.Oprt;

public class JoinFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Join();
    }
}
