package baze.model.implementation;

import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.operators.Desc;

import java.util.Objects;

public class OrderBy extends Clause{
    @Override
    public void fillOut(String[] lines, int l, int r) {
        Desc desc = new Desc(false); // rastuce je
        desc.setColumn(lines[l + 2]); // uzima sledeci string posle order by
        if (lines[r - 1].equals("desc"))
            desc.setDesc(true); // opadajuce

        this.getOperators().add(desc);
        System.out.println("OrderBy: " + this.getOperators());
    }
}
