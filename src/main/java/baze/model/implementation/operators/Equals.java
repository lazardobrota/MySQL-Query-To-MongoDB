package baze.model.implementation.operators;

public class Equals extends Oprt{
    // uzgled u okviru unosa: nesto = nesto
    public Equals() {
    }

    //Poziva doOperation od roditelja
    @Override
    public void doOperation(String[] line, int c) {
        super.doOperation(line, c);
        //Oprt o = FactoryUtils.getFactory(this.variable).createOprt(variable);//provera za slucaj da je drugi atribut neka Oprt
       // if(o!=null){

        //}

    }

    @Override
    public String toString() {
        return "Equals{" +
                "left=" + left +
                ", right=" + right +
                ", value='" + value + '\'' +
                '}';
    }
}
