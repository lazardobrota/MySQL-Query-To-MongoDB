package baze.model.implementation.operators;

public class Join extends Oprt{

    // izgled u okviru unosa: nesto join nesto
    public Join() {
    }

    @Override
    public void doOperation(String[] line, int c) {
        super.doOperation(line, c);
    }

    @Override
    public String toString() {
        return "Join{" +
                "column='" + column + '\'' +
                ", variable='" + variable + '\'' +
                '}';
    }
}
