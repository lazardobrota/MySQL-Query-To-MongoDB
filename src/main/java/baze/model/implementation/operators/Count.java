package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;

public class Count extends Oprt{

    private Oprt operation;
    public Count() {
    }

    @Override
    public void doOperation(String[] line, int c) {
        operation = FactoryUtils.getFactory(line[c+1].substring(1)).createOprt(line[c+1].substring(1));
        if(operation!=null){
            return;
        }
        super.doOperation(line, c);
    }
}
