package baze.model.implementation.operators;

public class Or extends Oprt{
    // uzgled u okviru unosa: oprt or oprt
    private Oprt left;
    private Oprt right;
    public Or() {
    }

    public void combine(Oprt left, Oprt right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Or{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
