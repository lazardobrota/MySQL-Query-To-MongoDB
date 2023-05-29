package baze.model.factory;

import baze.model.implementation.operators.Oprt;

public abstract class OprtFactory {
    public Oprt getOprt(String operator) {
        return createOprt(operator);
    }

    public abstract Oprt createOprt(String operator);
}
