package baze.model.factory.clause;

import baze.model.implementation.Clause;
import baze.model.implementation.GroupBy;

public class GroupByFactory extends ClauseFactory{
    @Override
    public Clause createClause() {
        return new GroupBy();
    }
}
