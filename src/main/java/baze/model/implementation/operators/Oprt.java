package baze.model.implementation.operators;

public abstract class Oprt {
    String column; // levo, ono sto uzima iz baze podataka
    String variable; // desno, uslov sta uzima iz baze

    public Oprt(String column, String variable) {
        this.column = column;
        this.variable = variable;
    }

    public abstract void doOperation();
}
