package baze.model.validator;

import baze.model.implementation.From;
import baze.model.implementation.SQLQuery;
import baze.model.implementation.Select;

public class FirstRule extends Rule{
    public FirstRule(String name) {
        super(name);
    }

    @Override
    public boolean ruleCheck(SQLQuery sqlQuery) {

        boolean select = false, from = false;
        for (int i = 0; i < sqlQuery.getClaues().size(); i++) {
            if (sqlQuery.getClaues().get(i) instanceof Select)
                select = true;
            else if (sqlQuery.getClaues().get(i) instanceof From)
                 from = true;

            //Postoje oba
            if (select && from)
                return true;
        }

        //Ako je dovde dosao znaci da ne postoje select i from
        setMessage("Fali Select ili From");
        return false;
    }
}
