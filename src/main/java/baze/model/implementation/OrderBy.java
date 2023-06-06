package baze.model.implementation;

import baze.model.factory.oprt.FactoryUtils;

public class OrderBy extends Clause{


    //MORA DA SE PISE "ASC" ILI "DESC" ZA POSLEDNJI
    @Override
    public void fillOut(String[] lines, int l, int r) {

        int rastuce = 1; // sortira se rastuce
        int columnOr = 0; // 0 - kolona je, 1 - rastuce ili opadajuce je
        //Moze da postji samo jedan string posle za kolonu a moze i drugi string koji govori da je opadajuce
        for (int i = l + 2; i < r; i++) {
            rastuce = 1;

            //Gleda da li je rastuce ili opadajuce
            if (columnOr == 1) {
                //Ako postoji desc znaci da se sortira opadajuce
                if (lines[i].equals("desc") || lines[i].equals("desc,")) {
                    rastuce = -1;
                }
                // Ako ne postoji asc a ni desc podrazumeva se da je asc i samo da se vrati za 1 unazad da ne bi preskocio sledecu kolonu
                else if (!lines[i].equals("asc") && !lines[i].equals("asc,")){
                    i--;
                }

                this.getOperators().add(FactoryUtils.getFactory(String.valueOf(rastuce)).getOprt(String.valueOf(rastuce)));
                columnOr = 0;
                continue;
            }

            //Da uzme kolonu

            String[] str = lines[i].split(","); // bez zareza da uzme string
            //Upisuje sve sto soritra
            this.getOperators().add(FactoryUtils.getFactory(str[0]).getOprt(str[0]));
            columnOr = 1;
        }

        System.out.println("OrderBy: " + rastuce + " operatori: " + this.getOperators());
    }

}
