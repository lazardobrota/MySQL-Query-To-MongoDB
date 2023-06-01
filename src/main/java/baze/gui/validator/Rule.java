package baze.gui.validator;

import baze.model.implementation.AClause;
import baze.model.implementation.SQLQuery;

public abstract class Rule {
    private String name;

    public Rule(String name) {
        this.name = name;
    }

    public abstract boolean ruleCheck(SQLQuery sqlQuery); // proverav funkciju
    public abstract void message(); // ako je losa funkcija
}
