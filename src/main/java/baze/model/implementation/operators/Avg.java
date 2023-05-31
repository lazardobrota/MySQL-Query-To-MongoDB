package baze.model.implementation.operators;

import baze.model.implementation.Clause;
import baze.model.implementation.operators.Oprt;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Avg extends Oprt {

    public Avg() {
    }

    @Override
    public void doOperation(String[] line, int c) {

        //Bez space
        if(line[c].contains("(") && line[c].contains(")")){
            this.variable = line[c].substring(line[c].indexOf('(')+1, line[c].indexOf(')'));
        }else{ // Sa space
            variable = line[c+1].substring(line[c+1].indexOf('(')+1, line[c+1].indexOf(')'));

        }
    }

    @Override
    public String toString() {
        return "Avg{" +
                "variable='" + variable + '\'' +
                '}';
    }
}
