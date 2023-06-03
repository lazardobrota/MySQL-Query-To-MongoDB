package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Avg extends Oprt {
// uzgled u okviru unosa: avg(nesto) / avg (nesto)

    public Avg() {
    }

    @Override
    public void doOperation(String[] line, int c) {

        String value;
        //Bez space
        if(line[c].contains("(") && line[c].contains(")")){
            value = line[c].substring(line[c].indexOf('(')+1, line[c].indexOf(')'));
        }else{ // Sa space
            value = line[c+1].substring(line[c+1].indexOf('(')+1, line[c+1].indexOf(')'));
        }

        this.right = FactoryUtils.getFactory(value).getOprt(value); // Pravi ColumnString
    }

    @Override
    public String toString() {
        return "Avg{" +
                "left=" + left +
                ", right=" + right +
                ", value='" + value + '\'' +
                '}';
    }
}
