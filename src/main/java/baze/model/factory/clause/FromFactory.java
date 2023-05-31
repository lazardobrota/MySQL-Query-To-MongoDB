package baze.model.factory.clause;

import baze.model.implementation.Clause;
import baze.model.implementation.From;

public class FromFactory extends ClauseFactory{
    @Override
    public Clause createClause() {
        return new From();
    }
}
