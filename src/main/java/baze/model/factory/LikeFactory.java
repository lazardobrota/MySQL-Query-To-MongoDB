package baze.model.factory;

import baze.model.implementation.operators.Like;
import baze.model.implementation.operators.Oprt;

public class LikeFactory extends OprtFactory{
    @Override
    public Oprt createOprt(String operator) {
        return new Like();
    }
}
