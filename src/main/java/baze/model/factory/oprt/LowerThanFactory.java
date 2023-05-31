package baze.model.factory.oprt;

import baze.model.implementation.operators.LowerThan;
import baze.model.implementation.operators.Oprt;

public class LowerThanFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operation) {
        if (operation.matches("<"))
            return new LowerThan(false);
        else
            return new LowerThan(true); // <=
    }
}
