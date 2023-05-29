package baze.model.implementation.operators;

public class LowerThan extends Oprt {
    boolean equal; // da li je ovo <= ili <

    public LowerThan(String colmn, String variable, boolean equal) {
        super(colmn, variable);
        this.equal = equal;
    }

    @Override
    public void doOperation() {

    }
}
