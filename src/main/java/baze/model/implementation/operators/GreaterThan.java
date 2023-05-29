package baze.model.implementation.operators;

public class GreaterThan extends Operator{
    boolean equal; // da li je ovo >= ili >

    public GreaterThan(String colmn, String variable, boolean equal) {
        super(colmn, variable);
        this.equal = equal;
    }

    @Override
    public void doOperation() {

    }
}
