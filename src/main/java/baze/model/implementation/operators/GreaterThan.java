package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;

public class GreaterThan extends Oprt {
    // uzgled u okviru unosa: nesto > nesto / nesto >= nesto
    private boolean equal; // da li je ovo >= ili >

    public GreaterThan(boolean equal) {
        this.equal = equal;
    }

    //Poziva doOperation od roditelja


    @Override
    public void doOperation(String[] line, int c) {
        super.doOperation(line, c);
        //Oprt o = FactoryUtils.getFactory(this.variable).createOprt(variable);//provera za slucaj da je drugi atribut neka Oprt
        //if(o!=null){

       // }
    }

    @Override
    public String toString() {
        return "GreaterThan{" +
                "equal=" + equal +
                ", column='" + column + '\'' +
                ", variable='" + variable + '\'' +
                ", agregation=" + agregation +
                '}';
    }
}
