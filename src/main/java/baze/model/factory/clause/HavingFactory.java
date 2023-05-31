package baze.model.factory.clause;

import baze.model.implementation.Clause;
import baze.model.implementation.Having;

public class HavingFactory extends ClauseFactory{
    @Override
    public Clause createClause() {
        return new Having();
    }
}
