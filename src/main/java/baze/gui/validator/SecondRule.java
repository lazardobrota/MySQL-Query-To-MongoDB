package baze.gui.validator;

import baze.gui.view.MainFrame;
import baze.model.implementation.AClause;
import baze.model.implementation.GroupBy;
import baze.model.implementation.SQLQuery;
import baze.model.implementation.Select;

public class SecondRule extends Rule{
    public SecondRule(String name) {
        super(name);
    }

    @Override
    public boolean ruleCheck(SQLQuery sqlQuery) {
        boolean agregation = false, groupBy = false;
        for (int i = 0; i < sqlQuery.getClaues().size(); i++) {
            //Pronasao je select
            if (sqlQuery.getClaues().get(i) instanceof Select) {
                Select select = (Select) sqlQuery.getClaues().get(i);
                //Ako nije prazno znaci da ima operatore
                if (!select.getOperators().isEmpty())
                    agregation = true;
            }
            else if (sqlQuery.getClaues().get(i) instanceof GroupBy) {
                groupBy = true;
            }

            //Oba postoje tako da je dobro
            if (agregation && groupBy)
                return true;
        }

        //Ne postoji agregacija
        if (!agregation)
            return true;

        message();
        return false;
    }

    @Override
    public void message() {
        MainFrame.getInstance().errorMessage("Fali group by");
    }
}
