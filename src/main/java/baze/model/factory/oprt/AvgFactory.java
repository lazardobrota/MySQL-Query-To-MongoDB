package baze.model.factory.oprt;

import baze.model.implementation.operators.agregation.Avg;
import baze.model.implementation.operators.Oprt;

public class AvgFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        return new Avg();
    }
}
