package baze.model.implementation;

import baze.model.factory.oprt.ColumnStringFactory;
import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.operators.*;


public class Where extends Clause{

    public Where() {

        //hashMap.put("<", new GreaterThan())
    }

    //Where: Equals, GreaterThan, LowerThan, Like, AND, OR
    @Override
    public void fillOut(String[] lines, int l, int r) {
        // TODO OVO KORISTIS KAD POZIVAS FACTORY
        // FactoryUtils.getFactory(lines[l]).getOprt(lines[l]);

        for (int i = l; i < r; i++) {
            //Kada rec nije obican string to znaci da je operator neki
            if (FactoryUtils.getFactory(lines[i]) instanceof ColumnStringFactory)
                continue;

            // Uzima operator
            Oprt operator = FactoryUtils.getFactory(lines[i]).getOprt(lines[i]);

            // Preskace ako je agregacija
            if (operator instanceof Max || operator instanceof Avg || operator instanceof Min)
                continue;

             //Ako nema last da je AND ili Or
            this.getOperators().add(operator);//dodaje operator
            // Svaki operator
            operator.doOperation(lines, i); // cuva sta treba u tom operatoru
        }

        System.out.println("Where: " + this.getOperators());
    }

}


/*
    package baze.model.implementation;

import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.operators.*;

import java.util.Objects;

public class Where extends Clause{

    public Where() {

        //hashMap.put("<", new GreaterThan())
    }

    //Where: Equals, GreaterThan, LowerThan, Like, AND, OR
    @Override
    public void fillOut(String[] lines, int l, int r) {
        // TODO OVO KORISTIS KAD POZIVAS FACTORY
        // FactoryUtils.getFactory(lines[l]).getOprt(lines[l]);

        for (int i = l; i < r; i++) {
            //Kada rec nije null to znaci da je operator neki
            if (FactoryUtils.getFactory(lines[i]) == null)
                continue;

            // Uzima operator
            Oprt operator = Objects.requireNonNull(FactoryUtils.getFactory(lines[i])).getOprt(lines[i]);

            //
            if (operator instanceof Max || operator instanceof Avg || operator instanceof Min)
                continue;

            //Posebno za AND i OR
            Oprt last = null;
            //Ako nije prazna lista operatora
            if (!this.getOperators().isEmpty()) {
                last = this.getOperators().get(this.getOperators().size() - 1); // uzima poslednji operator
            }

            if (last instanceof And) { // Ako je AND operator
                And and = (And) last;
                //Uzima operator levo od AND i desni operator tj onaj koji se trenutno gleda
                and.combine(this.getOperators().get(this.getOperators().size() - 2), operator);
                this.getOperators().remove(this.getOperators().size() - 2); // brise operator pre AND

            }
            else if (last instanceof Or) { // Ako je OR operator
                Or or = (Or) last;
                //Uzima operator levo od OR i desni operator tj onaj koji se trenutno gleda
                or.combine(this.getOperators().get(this.getOperators().size() - 2), operator);
                this.getOperators().remove(this.getOperators().size() - 2); // brise operator pre OR
            }
             //Ako nema last da je AND ili Or
            this.getOperators().add(operator);//dodaje operator
            // Svaki operator
            operator.doOperation(lines, i); // cuva sta treba u tom operatoru
        }
        //proverava da li treba poslednji Oprt da se brise jer se vec nalazu u And/Or
        if(getOperators().size() < 2){
            System.out.println("Where: " + this.getOperators());
            return;
        }
        Oprt o = getOperators().get(getOperators().size()-2);
        if(o instanceof Or ||o instanceof And)
            getOperators().remove(getOperators().size()-1);

        System.out.println("Where: " + this.getOperators());
    }

}

 */