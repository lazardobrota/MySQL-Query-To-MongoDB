package baze.model.implementation.operators;

import baze.model.factory.clause.FactoryUtilsClause;
import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.AClause;
import baze.model.implementation.Clause;

import java.util.Objects;

//In se koristi za podupit
public class In extends Oprt{
// izgled u okviru unosa:

    private AClause podupit;

    @Override
    public void doOperation(String[] line, int c) {

        this.column = line[c - 1];
    }

    public void setPodupit(AClause podupit) {
        this.podupit = podupit;
    }

    @Override
    public String toString() {
        return "In{" +
                "podupit=" + podupit +
                ", column='" + column + '\'' +
                '}';
    }
}
