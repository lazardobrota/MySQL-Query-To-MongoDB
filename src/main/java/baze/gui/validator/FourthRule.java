package baze.gui.validator;

import baze.gui.view.MainFrame;
import baze.model.implementation.AClause;
import baze.model.implementation.From;
import baze.model.implementation.SQLQuery;
import baze.model.implementation.operators.Join;
import baze.model.implementation.operators.On;
import baze.model.implementation.operators.Using;

public class FourthRule extends Rule{
    public FourthRule(String name) {
        super(name);
    }

    @Override
    public boolean ruleCheck(SQLQuery sqlQuery) {

        boolean join = false;
        for (int i = 0; i < sqlQuery.getClaues().size(); i++) {
            //Ako nije from
            if (!(sqlQuery.getClaues().get(i) instanceof From))
                continue;

            //Ako je from
            From from = (From) sqlQuery.getClaues().get(i);

            //Prolazi kroz sve operatore u from i trazi
            for (int j = 0; j < from.getOperators().size(); j++) {
                if (from.getOperators().get(j) instanceof Join) {
                    join = true;
                    continue;
                }

                //Ako oni postoje onda sigurno postoji join
                if (from.getOperators().get(j) instanceof On || from.getOperators().get(j) instanceof Using)
                    return true;
            }

            //Ako join ne postoji onda nemea veze sto ne postoje ON i USING
            if (!join)
                return true;

            //Nece se ponovo pojaviti from, samo jednom moze u jednom upitu
            break;
        }
        message();
        return false;
    }

    @Override
    public void message() {
        MainFrame.getInstance().errorMessage("Fale Using ili On");
    }
}
