package baze.model.factory.clause;

import baze.model.implementation.Clause;
import baze.model.implementation.Where;

public class WhereFactory extends ClauseFactory{
    @Override
    public Clause createClause() {
        return new Where();
    }
}
