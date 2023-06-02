package baze.model.validator;

import baze.model.implementation.AClause;
import baze.model.implementation.SQLQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Rule {
    private String name;
    private String message; // ako je losa funkcija

    public Rule(String name) {
        this.name = name;
    }

    public abstract boolean ruleCheck(SQLQuery sqlQuery); // proverav funkciju
}
