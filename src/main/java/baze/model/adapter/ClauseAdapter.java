package baze.model.adapter;

import baze.model.implementation.Clause;
import baze.model.implementation.operators.*;

import java.util.ArrayList;
import java.util.List;

public class ClauseAdapter implements Adapter{
    protected Clause clause;
    protected List<String> adaptedOprt;

    public ClauseAdapter(Clause clause) {
        this.clause = clause;
        this.adaptedOprt = new ArrayList<>();
        fillOutList();

    }


    public void fillOutList(){
        Oprt oprt;
        String str;
        for(int i = 0; i < clause.getOperators().size(); i++){
            oprt = clause.getOperators().get(i);

            if(oprt instanceof Avg){
                str = "$avg: <" + oprt.getVariable() +">";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Max){
                str = "$max: <" + oprt.getVariable() +">";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Min){
                str = "$min: <" + oprt.getVariable() +">";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Equals){
                str = "$max: <" + oprt.getVariable() +">";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
        }
    }
}
