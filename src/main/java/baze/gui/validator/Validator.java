package baze.gui.validator;

import baze.model.implementation.SQLQuery;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    List<Rule> rules;
    SQLQuery sqlQuery;


    public Validator(SQLQuery sqlQuery) {
        this.sqlQuery = sqlQuery;
        this.rules = new ArrayList<>();
        this.rules.add(new FirstRule("SelectFrom"));
        this.rules.add(new SecondRule("GroupBy"));
        this.rules.add(new ThirdRule("WhereAgregation"));
        this.rules.add(new FourthRule("UsingOn"));
    }

    public boolean checkRules() {
        //Prolazi kroz svako pravilo
        for (Rule rule : rules) {
            //Proverava da li je pravilo zadovoljeno
            if (!rule.ruleCheck(sqlQuery))
                return false;
        }
        return true;
    }
}
