package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import lombok.Setter;


@Setter
public class SelectAdapter extends ClauseAdapter {

    public SelectAdapter() {
        super();
    }

    public SelectAdapter(Clause clause) {
        super(clause);
    }
    @Override
    public String toString() {
        if(adaptedOprt.contains("\"*\""))return  "select{}";
        String st = "{ ";
        if(!adaptedOprt.contains("\"_id\""))st+="\"_id\": 0, ";
        for(String oprt: adaptedOprt){
            st+=oprt+": 1, ";
        }
        st +="}";
        return "select" +
                st
                ;
    }
}
