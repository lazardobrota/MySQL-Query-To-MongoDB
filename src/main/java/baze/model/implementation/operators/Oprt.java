package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class Oprt {
    protected Oprt left; // levo, ono sto uzima iz baze podataka
    protected Oprt right; // desno, uslov sta uzima iz baze
    protected String value;
    //protected Oprt agregation;

    public Oprt() {
    }

    public void doOperation(String[] line, int c){//uzima prethodni i sledeci string i pamti u odgovarajuca polja
        if(c==0 || c== line.length) {//provera da li postoje moguci stringovi koji odgovaraju potrebama
            return;
        }
        String x = line[c-1], y = line[c+1];

        left = FactoryUtils.getFactory(x).getOprt(x);
        left.doOperation(line, c - 1);

        right = FactoryUtils.getFactory(y).getOprt(y);
        right.doOperation(line, c + 1);
        /*
        // Za x ako je neka operacija da operaciju sacuva
        if (FactoryUtils.getFactory(x) != null) {
            agregation = Objects.requireNonNull(FactoryUtils.getFactory(x)).getOprt(x);
            agregation.doOperation(line, c - 1);
        }
        else { // ako nije operacija da sacuva string
            if(x.contains("("))x = x.substring(1);
            this.left = x;
        }

        // Za y ako je neka operacija da operaciju sacuva
        if (FactoryUtils.getFactory(y) != null) {
            agregation = Objects.requireNonNull(FactoryUtils.getFactory(y)).getOprt(y);
            agregation.doOperation(line, c + 1);
        }
        else { // ako nije operacija da sacuva string
            if(y.contains(")"))y = y.substring(0,y.length()-1);
            this.right = y;
        }
         */
    }

}
