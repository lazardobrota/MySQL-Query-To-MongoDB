package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.Clause;

import java.util.Objects;

public class On extends Oprt {
// uzgled u okviru unosa: on (a = b)


    public On() {
    }

    @Override
    public void doOperation(String[] line, int c) {
        //kreira uslovni Oprt i zove mu doOperation
        right = FactoryUtils.getFactory(line[c + 2]).getOprt(line[c+2]);

        right.doOperation(line, c+2);
    }

    @Override
    public String toString() {
        return "On{" +
                "right=" + right +
                '}';
    }
}
