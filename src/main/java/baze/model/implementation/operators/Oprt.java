package baze.model.implementation.operators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Oprt {
    protected String column; // levo, ono sto uzima iz baze podataka
    protected String variable; // desno, uslov sta uzima iz baze

    public Oprt() {
    }

    public void doOperation(String[] line, int c){//uzima prethodni i sledeci string i pamti u odgovarajuca polja
        if(c==0 || c== line.length) {//provera da li postoje moguci stringovi koji odgovaraju potrebama
            return;
        }
        String x = line[c-1], y = line[c+1];

        if(x.contains("("))x = x.substring(1);
        if(y.contains(")"))y = y.substring(0,y.length()-1);

        this.column = x;
        this.variable = y;
    }

}
