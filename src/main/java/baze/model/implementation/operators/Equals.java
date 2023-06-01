package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;

public class Equals extends Oprt{
    // uzgled u okviru unosa: nesto = nesto
    public Equals() {
    }

    @Override
    public void doOperation(String[] line, int c) {
        super.doOperation(line, c);
        //Oprt o = FactoryUtils.getFactory(this.variable).createOprt(variable);//provera za slucaj da je drugi atribut neka Oprt
       // if(o!=null){

        //}

    }

    //Poziva doOperation od roditelja


    @Override
    public String toString() {
        return "Equals{" +
                "column='" + column + '\'' +
                ", variable='" + variable + '\'' +
                ", agregation=" + agregation +
                '}';
    }
}
