package baze.model.implementation;

import baze.model.factory.FactoryUtils;
import baze.model.factory.OprtFactory;
import baze.model.implementation.operators.And;
import baze.model.implementation.operators.GreaterThan;
import baze.model.implementation.operators.Oprt;
import baze.model.implementation.operators.Or;

import java.util.Objects;

public class Where extends Clause{

    public Where(String name) {
        super(name);

        //hashMap.put("<", new GreaterThan())
    }

    //Where: Equals, GreaterThan, LowerThan, Like, AND, OR
    //TODO IDEJA ZA KASNIJE KLASE : Dodaje u listu i proverav svaki drugi da li je AND ili OR ako nisu onda znaci da radi sa jednom stvari, u suprotnom nek ulazi u AND i OR samo
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
            }
            else if (last instanceof Or) { // Ako je OR operator
                Or or = (Or) last;
                //Uzima operator levo od OR i desni operator tj onaj koji se trenutno gleda
                or.combine(this.getOperators().get(this.getOperators().size() - 2), operator);
            }

            // Svaki operator
            operator.doOperation(lines, i); // cuva sta treba u tom operatoru

            this.getOperators().add(operator);//dodaje operator
        }

        System.out.println(this.getOperators());
    }

}
