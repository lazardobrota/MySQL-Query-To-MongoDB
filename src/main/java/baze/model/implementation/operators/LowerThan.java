package baze.model.implementation.operators;

public class LowerThan extends Oprt {
    boolean equal; // da li je ovo <= ili <

    public LowerThan(String operation) {
        if (operation.matches("<"))
            equal = false;
        else if (operation.matches("<="))
            equal = true;
    }

    @Override
    public void doOperation() {

    }
}
