package baze.model.implementation;

import baze.model.factory.FactoryUtils;
import baze.model.factory.OprtFactory;
import baze.model.implementation.operators.GreaterThan;

public class Where extends Clause{

    public Where(String name) {
        super(name);

        //hashMap.put("<", new GreaterThan())
    }

    @Override
    public void fillOut(String[] lines, int l, int r) {
        // TODO OVO KORISTIS KAD POZIVAS FACTORY
        // FactoryUtils.getFactory(lines[l]).getOprt(lines[l]);
    }

}
