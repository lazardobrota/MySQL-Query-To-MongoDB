package baze.model.implementation.operators;

public class Like extends Oprt {
// uzgled u okviru unosa: nesto like nesto

    public Like() {
    }

    //Poziva doOperation od roditelja

    @Override
    public String toString() {
        return "Like{" +
                "left=" + left +
                ", right=" + right +
                ", value='" + value + '\'' +
                '}';
    }
}
