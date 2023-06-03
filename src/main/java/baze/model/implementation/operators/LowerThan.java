package baze.model.implementation.operators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LowerThan extends Oprt {
    // uzgled u okviru unosa: nesto < nesto / nesto <= nesto
    private boolean equal; // da li je ovo <= ili <

    public LowerThan(boolean equal) {
        this.equal = equal;
    }

    //Poziva doOperation od roditelja

    @Override
    public void doOperation(String[] line, int c) {
        super.doOperation(line, c);
    }

    @Override
    public String toString() {
        return "LowerThan{" +
                "equal=" + equal +
                ", left=" + left +
                ", right=" + right +
                ", value='" + value + '\'' +
                '}';
    }
}
