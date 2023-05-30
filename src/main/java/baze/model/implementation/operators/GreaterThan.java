package baze.model.implementation.operators;

public class GreaterThan extends Oprt {
    boolean equal; // da li je ovo >= ili >

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
