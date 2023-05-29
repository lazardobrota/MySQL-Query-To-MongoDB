package baze.model.factory;

import baze.model.implementation.operators.Oprt;

public class FactoryUtils {
    private static final GreaterThanFactory greaterThanFactory = new GreaterThanFactory();
    private static final LikeFactory likeFactory = new LikeFactory();
    private static final LowerThanFactory lowerThanFactory = new LowerThanFactory();
    private static final OnFactory onFactory = new OnFactory();
    private static final UsingFactory usingFactory = new UsingFactory();

    public static OprtFactory getFactory(String operation) {
        if (operation.matches(">") || operation.matches(">="))
            return greaterThanFactory;
        if (operation.matches("like"))
            return likeFactory;
        if (operation.matches("<") || operation.matches("<="))
            return lowerThanFactory;
        if (operation.matches("on"))
            return onFactory;
        if (operation.matches("using"))
            return usingFactory;

        return null;
    }
}
