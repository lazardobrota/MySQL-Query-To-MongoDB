package baze.model.implementation.operators;

public class Join extends Oprt{

    // izgled u okviru unosa: nesto join nesto
    public Join() {
    }

    //TODO Mislim da join treba da cuva samo desnu stranu, tj desnu tabelu, a drugi se zna da se nalazi odmah posle from
    @Override
    public void doOperation(String[] line, int c) {
        super.doOperation(line, c);
    }

    @Override
    public String toString() {
        return "Join{" +
                "left=" + left +
                ", right=" + right +
                ", value='" + value + '\'' +
                '}';
    }
}
