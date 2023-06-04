package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;

public class Using extends Oprt {
    // uzgled u okviru unosa: using nesto
    public Using() {
    }
    @Override
    public void doOperation(String[] line, int c) {
        String x;
        if(line[c].contains("(") && line[c].contains(")")){//u slucaju da nema razmaka izmedju zagrade
            x = line[c];
            x = x.substring(x.indexOf('(')+1,x.length()-1);

        }else{
            x = line[c+1];
            if(!x.contains("(") || !x.contains(")"))return;
            x = x.substring(1,x.length()-1);
        }

        this.right = FactoryUtils.getFactory(x).getOprt(x);
    }

    @Override
    public String toString() {
        return "Using{" +
                ", right=" + right.getValue() +
                '}';
    }
}
