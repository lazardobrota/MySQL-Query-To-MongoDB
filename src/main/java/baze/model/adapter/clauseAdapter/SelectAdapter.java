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
        if(adaptedOprt.contains("\"*\""))return  "{}";
        String str = "{ ";
        if(!adaptedOprt.contains("\"_id\""))str+="\"_id\": 0, ";
        for(String oprt: adaptedOprt){
            str+=oprt+": 1, ";
        }
        str = str.substring(0, str.length() - 2); // uklanja zarez
        str +=" }";
        return str;
    }
}
