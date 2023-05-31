package baze.model.factory.clause;

import baze.model.implementation.Clause;

public abstract class ClauseFactory {
    public Clause getClause() {
        return createClause();
    }

    public abstract Clause createClause();
}
