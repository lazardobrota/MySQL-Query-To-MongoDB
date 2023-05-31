package baze.model.implementation.operators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class And extends Oprt{

    private Oprt left;
    private Oprt right;
    public And() {
    }

    public void combine(Oprt left, Oprt right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "And{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
