package baze.model.implementation.operators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class And extends CombineOprt{
// uzgled u okviru unosa: oprt and oprt
    public And() {
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
        return "And{" +
                "left=" + left +
                ", right=" + right +
                ", value='" + value + '\'' +
                '}';
    }
}
