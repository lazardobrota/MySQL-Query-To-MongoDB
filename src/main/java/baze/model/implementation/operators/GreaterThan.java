package baze.model.implementation.operators;

public class GreaterThan extends Oprt {
    private boolean equal; // da li je ovo >= ili >
    private Oprt agregation;

    public GreaterThan(boolean equal) {
        this.equal = equal;
    }

    //Poziva doOperation od roditelja


    @Override
    public String toString() {
        return "GreaterThan{" +
                "equal=" + equal +
                ", column='" + column + '\'' +
                ", variable='" + variable + '\'' +
                '}';
    }
}
