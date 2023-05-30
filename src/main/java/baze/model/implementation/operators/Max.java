package baze.model.implementation.operators;

import baze.model.implementation.Clause;

public class Max extends Oprt{


    @Override
    public void doOperation(String[] line, int c) {
        //Bez space
        if(line[c].contains("(") && line[c].contains(")")){
            this.variable = line[c].substring(line[c].indexOf('('), line[c].indexOf(')'));
        }else{ // Sa space
            variable = line[c+1].substring(line[c+1].indexOf('('), line[c+1].indexOf(')'));
        }
    }

    @Override
    public String toString() {
        return "Max{ variable='" + variable + '\'' + '}';
    }
}
