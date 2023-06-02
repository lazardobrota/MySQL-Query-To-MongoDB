package baze.model.implementation.operators;

import baze.model.factory.clause.FactoryUtilsClause;
import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.AClause;
import baze.model.implementation.Clause;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
//In se koristi za podupit
public class In extends Oprt{
// izgled u okviru unosa: in (nesto)

    private AClause podupit;

    @Override
    public void doOperation(String[] line, int c) {
        variable = line[c+1].substring(1,line[c+1].length()-1);

        //this.column = line[c - 1];
    }


    @Override
    public String toString() {
        return "In{" +
                "podupit=" + podupit +
                ", column='" + column + '\'' +
                '}';
    }
}
