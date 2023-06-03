package baze.model.factory.oprt;

import baze.model.implementation.operators.agregation.Count;
import baze.model.implementation.operators.Oprt;

public class CountFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Count();
    }
}
