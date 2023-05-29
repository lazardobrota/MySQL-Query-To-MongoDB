package baze.model.implementation.operators;

public abstract class Oprt {
    String column; // levo, ono sto uzima iz baze podataka
    String variable; // desno, uslov sta uzima iz baze

    public Oprt() {
    }

    public void fillOutOprt(String[] line, int c){//uzima prethodni i sledeci string i pamti u odgovarajuca polja
        if(c==0 || c== line.length){//provera da li postoje moguci stringovi koji odgovaraju potrebama
            return;
        }
        this.column = line[c-1];
        this.variable = line[c+1];
    }

    public abstract void doOperation();
}
