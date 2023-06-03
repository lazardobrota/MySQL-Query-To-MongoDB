package baze.model.implementation;

import baze.model.factory.oprt.ColumnStringFactory;
import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.operators.*;
import baze.model.implementation.operators.agregation.Agregation;
import baze.model.implementation.operators.agregation.Avg;
import baze.model.implementation.operators.agregation.Max;
import baze.model.implementation.operators.agregation.Min;

import java.util.Objects;

public class Having extends Clause{
    public Having() {
    }

    //TODO MORACE DA PRESKACE REDOVE GDE SE POJAVE AGREGACIJE, SAMO UNUTAR >, <, = MOZE DA IH GLEDA
    @Override
    public void fillOut(String[] lines, int l, int r) {
        for (int i = l; i < r; i++) {
            //Kada rec nije obican string to znaci da je operator neki
            if (FactoryUtils.getFactory(lines[i]) instanceof ColumnStringFactory)
                continue;

            // Uzima operator
            Oprt operator = Objects.requireNonNull(FactoryUtils.getFactory(lines[i])).getOprt(lines[i]);

            // Preskace ako je agregacija (cuva ih >, =, <)
            if (operator instanceof Agregation)
                continue;

            //Ako nema last da je AND ili Or
            this.getOperators().add(operator);//dodaje operator
            // Svaki operator
            operator.doOperation(lines, i); // cuva sta treba u tom operatoru
        }

        System.out.println("Having: " + this.getOperators());
    }
}

/*

    @Override
    public void fillOut(String[] lines, int l, int r) {
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
        if(getOperators().size() < 2){
            System.out.println("Having: " + this.getOperators());
            return;
        }
        Oprt o = getOperators().get(getOperators().size()-2);
        if(o instanceof Or ||o instanceof And)
            getOperators().remove(getOperators().size()-1);

        System.out.println("Having: " + this.getOperators());
    }
 */