package baze.model.implementation.operators;

public class LowerThan extends Oprt {
    boolean equal; // da li je ovo <= ili <

    public LowerThan(boolean equal) {
        this.equal = equal;
    }

    //Poziva doOperation od roditelja

    @Override
    public String toString() {
        return "LowerThan{" +
                "equal=" + equal +
                ", column='" + column + '\'' +
                ", variable='" + variable + '\'' +
                '}';
    }
}
