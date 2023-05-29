package baze.model.implementation.operators;

public class On extends Oprt {
    public On() {
    }

    @Override
    public void fillOutOprt(String[] line, int c) {
        if(line[c].contains("(")&&line[c].contains(")")){//u slucaju da nema razmaka izmedju zagrade

        }else{
            String x = line[c+1];

        }

    }

    @Override
    public void doOperation() {

    }
}
