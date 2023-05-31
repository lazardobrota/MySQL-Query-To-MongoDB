package baze.model.factory.oprt;

import baze.model.implementation.operators.Oprt;

public abstract class OprtFactory {
    public Oprt getOprt(String operation) {
        return createOprt(operation);
    }

    public abstract Oprt createOprt(String operation);
}
