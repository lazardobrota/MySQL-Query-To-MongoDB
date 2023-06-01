package baze.model.implementation;

import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.operators.On;
import baze.model.implementation.operators.Oprt;

import java.util.*;

public class From extends Clause {

    // Uradjeno kao lista da bi mogao da razlikujem kada postoji string i kada ne
    List<String> column; // ako nema operatora to znaci da ima samo jedan string koju cuva
    // Moze da ima join
    public From() {
        column = new ArrayList<>();
    }

    @Override
    public void fillOut(String[] lines, int l, int r) {
        boolean flag = false;
        for (int i = l + 1; i < r; i++) {
            //Ako je null samo nastavi dalje
            if (FactoryUtils.getFactory(lines[i]) == null)
                continue;

            //Nakon on da preskoci sledeci operator
            if (flag) {
                flag = false;
                continue;
            }
            //Nije null znaci da je naisao na neki operator

            Oprt oprt = Objects.requireNonNull(FactoryUtils.getFactory(lines[i])).getOprt(lines[i]);
            this.getOperators().add(oprt);

            //Da preskoci ono sto je u zagradam posle on
            if (oprt instanceof On)
                flag = true;

            oprt.doOperation(lines, i); // radi njegovu operaciju
        }

        //Ako je operator prazan znaci da nema operatora i da se samo jedna kolona iz tabele koristi
        if (this.getOperators().isEmpty())
            column.add(lines[l + 1]); // upisuje se naziv te kolone
        System.out.println(column);
        System.out.println("Operators: " + this.getOperators());
    }
}
