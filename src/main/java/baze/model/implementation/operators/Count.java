package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;

public class Count extends Oprt{
// uzgled u okviru unosa: count(oprt)

    private Oprt operation;
    public Count() {
    }

    @Override
    public void doOperation(String[] line, int c) {
        operation = FactoryUtils.getFactory(line[c]).createOprt(line[c].substring(1));
        if(operation!=null){
            return;
        }
        super.doOperation(line, c);
    }
}
