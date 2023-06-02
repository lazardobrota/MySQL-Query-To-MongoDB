package baze.model.adapter;

import baze.model.factory.adapter.AdapterFactoryUtils;
import baze.model.implementation.Clause;
import baze.model.implementation.operators.*;

import java.util.ArrayList;
import java.util.List;

public class ClauseAdapter implements Adapter{
    protected Clause clause;
    protected  ClauseAdapter podupitAdapter;
    protected List<String> adaptedOprt;

    public ClauseAdapter(Clause clause) {
        this.clause = clause;
        this.adaptedOprt = new ArrayList<>();
        fillOutList();

    }


    public void fillOutList(){
        Oprt oprt;
        String str;
        // prolazi kroz listu operatora i u zavisnosti od njegove instance odgovarajuce ga konvertuje u String
        for(int i = 0; i < clause.getOperators().size(); i++){
            oprt = clause.getOperators().get(i);

            if(oprt instanceof Avg){
                // sastavljanje stringa za avg
                str = "$avg: \"" + oprt.getVariable() +"\"";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Max){
                // sastavljanje stringa za max
                str = "$max: \"" + oprt.getVariable() +"\"";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Min){
                // sastavljanje stringa za min
                str = "$min: \"" + oprt.getVariable() +"\"";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Equals){
                // sastavljanje stringa za =
                String left, right;
                left =oprt.getVariable()+"\"";
                try {
                    Integer.parseInt(oprt.getVariable());
                    left = "\""+left;
                }catch (NumberFormatException nfe){
                    left  = "\"$"+left;
                }

                if(oprt.getAgregation()!=null){
                    right = stringConverter(oprt.getAgregation());
                }else{
                    right =oprt.getColumn()+"\"";
                    try {
                        Integer.parseInt(oprt.getColumn());
                        right = "\""+right;
                    }catch (NumberFormatException nfe){
                        right  = "\"$"+right;
                    }
                    String temp = right;
                    right = left;
                    left = temp;
                }
                str = "$eq: [ " + left+", "+right +" ]";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof GreaterThan){
                // sastavljanje stringa za >/>=
                String left, right;
                left =oprt.getVariable()+"\"";
                try {
                    Integer.parseInt(oprt.getVariable());
                    left = "\""+left;
                }catch (NumberFormatException nfe){
                    left  = "\"$"+left;
                }

                if(oprt.getAgregation()!=null){
                    right = stringConverter(oprt.getAgregation());
                }else{
                    right =oprt.getColumn()+"\"";
                    try {
                        Integer.parseInt(oprt.getColumn());
                        right = "\""+right;
                    }catch (NumberFormatException nfe){
                        right  = "\"$"+right;
                    }
                    String temp = right;
                    right = left;
                    left = temp;
                }
                str = "$gt";
                if(((GreaterThan) oprt).isEqual())str+="e";
                str += " [ " + left+", "+right +" ]";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof LowerThan){
                // sastavljanje stringa za </<=
                String left, right;
                left =oprt.getVariable()+"\"";
                try {
                    Integer.parseInt(oprt.getVariable());
                    left = "\""+left;
                }catch (NumberFormatException nfe){
                    left  = "\"$"+left;
                }

                if(oprt.getAgregation()!=null){
                    right = stringConverter(oprt.getAgregation());
                }else{
                    right =oprt.getColumn()+"\"";
                    try {
                        Integer.parseInt(oprt.getColumn());
                        right = "\""+right;
                    }catch (NumberFormatException nfe){
                        right  = "\"$"+right;
                    }
                    String temp = right;
                    right = left;
                    left = temp;
                }
                str = "$lt";
                if(((LowerThan) oprt).isEqual())str+="e";
                str += " [ " + left+", "+right +" ]";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof In){
                if(((In) oprt).getPodupit() != null){
                    podupitAdapter = (ClauseAdapter) AdapterFactoryUtils.getFactory(((In) oprt).getPodupit()).createAdapter(((In) oprt).getPodupit());
                    podupitAdapter.fillOutList();

                }
                // sastavljanje stringa za in
                str = "$avg: [ \"" + oprt.getVariable() +"\" ]";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Like){
                // TODO: 02/06/2023 provaliti kako funkcionise like u sql-u i u monguDB-u
                // sastavljanje stringa za like
                continue;
            }
            if(oprt instanceof And){
                // sastavljanje stringa za and
                str = "$and [ ";
                str += adaptedOprt.get(adaptedOprt.size()-1);
                adaptedOprt.remove(adaptedOprt.size()-1);
                i++;
                str +=", "+this.stringConverter(clause.getOperators().get(i));
                
                if(clause.getOperators().get(i+1) instanceof And){
                    i+=2;
                    str +=", "+this.stringConverter(clause.getOperators().get(i));
                }
                
                str += " ]";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Or){
                // sastavljanje stringa za or
                str = "$or [ ";
                str += adaptedOprt.get(adaptedOprt.size()-1);
                adaptedOprt.remove(adaptedOprt.size()-1);
                i++;
                str +=", "+this.stringConverter(clause.getOperators().get(i));

                if(clause.getOperators().get(i+1) instanceof Or){
                    i+=2;
                    str +=", "+this.stringConverter(clause.getOperators().get(i));
                }

                str += " ]";
                adaptedOprt.add(str);
                str = "";
                continue;

            }
            // sastavljanje stringa za za obican string
            str = "\""+((ColumnString)oprt).getColumnName()+"\"";
            adaptedOprt.add(str);
            str = "";
        }
    }
    public String stringConverter(Oprt oprt){
        //pomocna funkcija za pripremu String-ova za podupite
        String str;
        if(oprt instanceof Avg){
            str = "$avg: \"" + oprt.getVariable() +"\"";
            return str;
        }
        if(oprt instanceof Max){
            str = "$max: \"" + oprt.getVariable() +"\"";
            return str;
        }
        if(oprt instanceof Min){
            str = "$min: \"" + oprt.getVariable() +"\"";
            return str;
        }
        str = "\""+((ColumnString)oprt).getColumnName()+"\"";
        return str;
    }
}
