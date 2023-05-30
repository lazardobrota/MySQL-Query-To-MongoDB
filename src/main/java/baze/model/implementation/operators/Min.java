package baze.model.implementation.operators;

import baze.model.implementation.Clause;

public class Min extends Oprt{


    @Override
    public void doOperation(String[] line, int c) {
        if(line[c].contains("(") && line[c].contains(")")){
            this.variable = line[c].substring(line[c].indexOf('('), line[c].indexOf(')'));
        }else{
            variable = line[c+1].substring(line[c+1].indexOf('('), line[c+1].indexOf(')'));

        }
    }
}
