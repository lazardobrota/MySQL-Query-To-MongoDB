package baze.model.factory;

import baze.model.implementation.operators.Avg;
import baze.model.implementation.operators.Equals;
import baze.model.implementation.operators.Oprt;

public class AvgFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Avg();
    }
}
