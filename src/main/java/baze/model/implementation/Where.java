package baze.model.implementation;

import baze.model.implementation.operators.GreaterThan;

public class Where extends Clause{

    public Where(String name) {
        super(name);

        //hashMap.put("<", new GreaterThan())
    }

    @Override
    public void fillOut(String[] lines, int l, int r) {
    }

}
