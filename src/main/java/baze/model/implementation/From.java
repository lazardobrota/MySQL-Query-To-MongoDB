package baze.model.implementation;

import baze.model.factory.oprt.ColumnStringFactory;
import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.operators.ColumnString;
import baze.model.implementation.operators.On;
import baze.model.implementation.operators.Oprt;

import java.util.*;

public class From extends Clause {

    // Moze da ima join
    public From() {

    }

    @Override
    public void fillOut(String[] lines, int l, int r) {
        boolean flag = false;
        for (int i = l + 1; i < r; i++) {
            //Ako je string samo nastavi dalje
            if (FactoryUtils.getFactory(lines[i]) instanceof ColumnStringFactory)
                continue;

            //Nakon on da preskoci sledeci operator
            if (flag) {
                flag = false;
                continue;
            }
            //Nije null znaci da je naisao na neki operator

            Oprt oprt = FactoryUtils.getFactory(lines[i]).getOprt(lines[i]);
            this.getOperators().add(oprt);

            //Da preskoci ono sto je u zagradam posle on
            if (oprt instanceof On)
                flag = true;

            oprt.doOperation(lines, i); // radi njegovu operaciju
        }

        //Ako je operator prazan znaci da nema operatora i da se samo jedna kolona iz tabele koristi
        if (this.getOperators().isEmpty())
            this.getOperators().add(FactoryUtils.getFactory(lines[l + 1]).getOprt(lines[l + 1])); // Zove ColumnString
        System.out.println("From: " + this.getOperators());
    }

    @Override
    public String toString() {
        return "From{" +
                "operators=" + getOperators() +
                '}';
    }
}
