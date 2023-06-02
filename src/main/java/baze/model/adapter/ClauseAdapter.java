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
        Oprt o;
        String str;
        for(int i = 0; i < clause.getOperators().size(); i++){
            o = clause.getOperators().get(i);

            if(o instanceof Avg){
                str = "$avg: <" + o.getVariable() +">";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(o instanceof Max){
                str = "$max: <" + o.getVariable() +">";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(o instanceof Min){
                str = "$min: <" + o.getVariable() +">";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(o instanceof Equals){
                str = "$max: <" + o.getVariable() +">";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
        }
    }
}
