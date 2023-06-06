package baze.model.validator;

import baze.model.implementation.GroupBy;
import baze.model.implementation.SQLQuery;
import baze.model.implementation.Select;
import baze.model.implementation.operators.ColumnString;
import baze.model.implementation.operators.Oprt;

import java.util.ArrayList;
import java.util.List;

public class SecondRule extends Rule{
    public SecondRule(String name) {
        super(name);
    }

    @Override
    public boolean ruleCheck(SQLQuery sqlQuery) {
        List<Oprt> columns = new ArrayList<>(); // cuva sve sto nije agregacije iz select

        boolean agregation = false, groupBy = false;
        for (int i = 0; i < sqlQuery.getClaues().size(); i++) {
            //Pronasao je select
            if (sqlQuery.getClaues().get(i) instanceof Select) {
                Select select = (Select) sqlQuery.getClaues().get(i);

                //Prolazi kroz sve operatore
                for (int j = 0; j < select.getOperators().size(); j++) {
                    //Ako ima nesto sto nije string znaci da ima agregaciju
                    if (!(select.getOperators().get(j) instanceof ColumnString)) {
                        agregation = true;
                        continue;
                    }
                    //Ako je columnString
                    columns.add(select.getOperators().get(j));
                }
            }
            else if (agregation && sqlQuery.getClaues().get(i) instanceof GroupBy) {
                GroupBy group = (GroupBy) sqlQuery.getClaues().get(i);
                //Proverava da li groupby ima sve sto i select a da nije agregacija
                for (Oprt column : columns) {
                    //Ako ne sadrzi
                    if (!group.getOperators().contains(column)) {
                        setMessage("Fale stvari u group by");
                        return false;
                    }
                }
                groupBy = true;
            }

            //Oba postoje tako da je dobro
            if (agregation && groupBy)
                return true;
        }

        //Ne postoji agregacija
        if (!agregation)
            return true;

        setMessage("Fali group by");
        return false;
    }
}
