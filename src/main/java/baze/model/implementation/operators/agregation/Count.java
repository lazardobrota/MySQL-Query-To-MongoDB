package baze.model.implementation.operators.agregation;

import baze.model.factory.oprt.FactoryUtils;

public class Count extends Agregation{
// uzgled u okviru unosa: count(oprt)

    public Count() {
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
        return "Count{" +
                "left=" + left +
                ", right=" + right +
                ", value='" + value + '\'' +
                '}';
    }
}