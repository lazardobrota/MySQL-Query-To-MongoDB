package baze.model.implementation.operators;

public class Or extends CombineOprt{
    // uzgled u okviru unosa: oprt or oprt
    public Or() {
    }

    public void combine(Oprt left, Oprt right) {
        //this.left = left;
        //this.right = right;
    }

    @Override
    public void doOperation(String[] line, int c) {
    }

    @Override
    public String toString() {
        return "Or{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
