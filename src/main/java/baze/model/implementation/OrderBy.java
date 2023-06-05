package baze.model.implementation;

import baze.model.factory.oprt.FactoryUtils;

public class OrderBy extends Clause{
    private int rastuce = 1; // sortira se rastuce


    @Override
    public void fillOut(String[] lines, int l, int r) {

        //Moze da postji samo jedan string posle za kolonu a moze i drugi string koji govori da je opadajuce
        for (int i = l + 2; i  < r; i++) {

            //Dosao je do kraja i postoji desc sto znaci da se sortira opadajuce
            if (lines[i].equals("desc")) {
                rastuce = -1;
                continue;
            }
            String[] str = lines[i].split(","); // bez zareza da uzme string
            //Upisuje sve sto soritra
            this.getOperators().add(FactoryUtils.getFactory(str[0]).getOprt(str[0]));
        }
        this.getOperators().add(FactoryUtils.getFactory(String.valueOf(rastuce)).getOprt(String.valueOf(rastuce)));

        System.out.println("OrderBy: " + rastuce + " operatori: " + this.getOperators());
    }

}
