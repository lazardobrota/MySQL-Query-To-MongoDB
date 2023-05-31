package baze.model.factory.clause;

import baze.model.implementation.Clause;
import baze.model.implementation.Select;

public class SelectFactory extends ClauseFactory{
    @Override
    public Clause createClause() {
        return new Select();
    }
}
