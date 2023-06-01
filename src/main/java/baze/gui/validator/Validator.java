package baze.gui.validator;

import baze.model.implementation.AClause;
import baze.model.implementation.SQLQuery;
import baze.model.implementation.Where;
import baze.model.implementation.operators.In;
import baze.model.implementation.operators.Oprt;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    List<Rule> rules;
    SQLQuery sqlQuery;

    //TODO VALIDATOR NE RADI ZA PODUPIT, TO PROMENI
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

        // Podupiti
        for (int i = 0; i < sqlQuery.getClaues().size(); i++) {
            AClause aClause = sqlQuery.getClaues().get(i);

            //Trazi where
            if (!(aClause instanceof Where))
                continue;

            Where where = (Where) aClause;

            //Prolazi kroz sve operatore od where
            for (int j = 0; j < where.getOperators().size(); j++) {
                Oprt oprt = where.getOperators().get(j);

                //Trazi instancu In jer se u njemu nalazi podupit
                if (!(oprt instanceof In))
                    continue;

                In in = (In) oprt;
                //Uzima podupit od In
                Validator validator = new Validator((SQLQuery) in.getPodupit());

                //Ako podupit neprodje neki pravilo onda izlazi iz rekurzije i vraca false
                if (!validator.checkRules())
                    return false;
            }
            //Samo jedan where postoji
            break;
        }
        return true;
    }
}
