package baze.model.implementation.operators;

public class Using extends Oprt {
    public Using() {
    }
    @Override
    public void doOperation(String[] line, int c) {
        String x;
        if(line[c].contains("(") && line[c].contains(")")){//u slucaju da nema razmaka izmedju zagrade
            x = line[c];
            x = x.substring(x.indexOf('(')+1,x.length()-1);
            this.variable = x;

        }else{
            x = line[c+1];
            if(!x.contains("(") || !x.contains(")"))return;
            x = x.substring(1,x.length()-1);
            this.variable = x;
        }

    }
}
