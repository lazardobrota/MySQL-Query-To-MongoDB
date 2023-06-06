package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.factory.adapter.AdapterFactoryUtils;
import baze.model.implementation.Clause;
import baze.model.implementation.operators.*;
import baze.model.implementation.operators.agregation.*;
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
    public void fillOutList() {
        Oprt oprt;
        String str;
        // prolazi kroz listu operatora i u zavisnosti od njegove instance odgovarajuce ga konvertuje u String
        for(int i = 0; i < clause.getOperators().size(); i++){
            oprt = clause.getOperators().get(i);
            adaptedOprt.add(stringConverter(oprt));
        }
    }

    @Override
    public String stringConverter(Oprt oprt) {
        //pomocna funkcija za pripremu String-ova za podupite
        String str;
        if(oprt instanceof Sum){
            String buf = "\"$" + oprt.getRight().getValue() +"\"";
            if(buf.contains("*"))buf = "1";
            str = oprt.getRight().getValue()+"Sum: { $sum: " + buf+" }";
            return str;
        }
        if(oprt instanceof Count){
            String buf = "\"$" + oprt.getRight().getValue() +"\"";
            if(buf.contains("*"))buf = "1";
            str = oprt.getRight().getValue()+"Count: { $sum: " + buf+" }";
            return str;
        }
        if(oprt instanceof Avg){
            str = oprt.getRight().getValue()+"Avg: { $avg: \"$" + oprt.getRight().getValue() +"\" }";
            return str;
        }
        if(oprt instanceof Max){
            str = oprt.getRight().getValue()+"Max: { $max: \"$" + oprt.getRight().getValue() +"\" }";
            return str;
        }
        if(oprt instanceof Min){
            str = oprt.getRight().getValue()+"Min: { $min: \"$" + oprt.getRight().getValue() +"\" }";
            return str;
        }

        if(oprt == null) return "";

        str = "\""+oprt.getValue()+"\"";
        return str;
    }

    @Override
    public String toString() {
        if(adaptedOprt.contains("\"*\""))return  "";
        String str = "{ ";
        if(!adaptedOprt.contains("\"_id\""))str+="\"_id\": 0, ";
        for(String oprt: adaptedOprt){
            if(oprt.contains("{")){
                str+= oprt+", ";
            }else{
                str+=oprt+": 1, ";
            }
        }
        str = str.substring(0, str.length() - 2); // uklanja zarez
        str +=" }";
        return str;
    }

    @Override
    public String adapterToString(ClauseAdapter clauseAdapter) {
        if(adaptedOprt.contains("\"*\""))return  "";
        String str = "{ ";
        if(!adaptedOprt.contains("\"_id\""))str+="\"_id\": 0, ";
        for(String oprt: adaptedOprt){
            if(oprt.contains("{")){
                str+= oprt+", ";
            }else{
                str+=oprt+": 1, ";
            }
        }
        str = str.substring(0, str.length() - 2); // uklanja zarez
        str +=" }";
        return str;
    }
}
