package baze.model.factory.clause;

import baze.model.implementation.Clause;
import baze.model.implementation.OrderBy;

public class OrderByFactory extends ClauseFactory{
    @Override
    public Clause createClause() {
        return new OrderBy();
    }
}
