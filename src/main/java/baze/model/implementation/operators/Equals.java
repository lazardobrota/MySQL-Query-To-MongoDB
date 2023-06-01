package baze.model.implementation.operators;

public class Equals extends Oprt{
    public Equals() {
    }

    @Override
    public void doOperation(String[] line, int c) {
        super.doOperation(line, c);

    }

    //Poziva doOperation od roditelja


    @Override
    public String toString() {
        return "Equals{" +
                "column='" + column + '\'' +
                ", variable='" + variable + '\'' +
                ", agregation=" + agregation +
                '}';
    }
}
