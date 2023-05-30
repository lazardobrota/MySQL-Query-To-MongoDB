package baze.model.implementation.operators;

public class Equals extends Oprt{
    public Equals() {
    }

    //Poziva doOperation od roditelja

    @Override
    public String toString() {
        return "Equals{" +
                "column='" + column + '\'' +
                ", variable='" + variable + '\'' +
                '}';
    }
}
