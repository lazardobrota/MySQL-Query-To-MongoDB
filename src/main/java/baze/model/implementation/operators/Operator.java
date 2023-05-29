package baze.model.implementation.operators;

public abstract class Operator {
    String colmn; // levo, ono sto uzima iz baze podataka
    String variable; // desno, uslov sta uzima iz baze

    public Operator(String colmn, String variable) {
        this.colmn = colmn;
        this.variable = variable;
    }

    public abstract void doOperation();
}
