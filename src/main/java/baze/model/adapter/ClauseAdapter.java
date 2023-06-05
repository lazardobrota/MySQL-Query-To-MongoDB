package baze.model.adapter;

import baze.model.factory.adapter.AdapterFactoryUtils;
import baze.model.implementation.Clause;
import baze.model.implementation.operators.*;
import baze.model.implementation.operators.agregation.*;

import java.util.ArrayList;
import java.util.List;

public class ClauseAdapter implements Adapter{
    protected Clause clause;
    protected  ClauseAdapter podupitAdapter;
    protected List<String> adaptedOprt;

    public ClauseAdapter() {
        adaptedOprt = new ArrayList<>();
    }

    public ClauseAdapter(Clause clause) {
        this.clause = clause;
        this.adaptedOprt = new ArrayList<>();
        fillOutList();

    }

    @Override
    public String toString() {
        return "ClauseAdapter{" +
                 adaptedOprt +
                '}';
    }

    public void fillOutList(){

        Oprt oprt;
        String str;
        // prolazi kroz listu operatora i u zavisnosti od njegove instance odgovarajuce ga konvertuje u String
        for(int i = 0; i < clause.getOperators().size(); i++){
            oprt = clause.getOperators().get(i);

            //ovde!!!!!!!!!!!!!!!!!!!!!!!!
            if(oprt instanceof Join){
                str = "{ $lookup: { ";
                i++;
                Oprt next = clause.getOperators().get(i);
                if(next instanceof On){
                    str +="localField: "+srediAtribut(next.getRight().getLeft())
                            +", "+srediAtribut(next.getRight().getLeft())
                            +", as: "+srediAtribut(oprt.getLeft());
                }else {//Using
                    str +="localField: "+srediAtribut(next.getRight())
                            +", "+srediAtribut(next.getRight())
                            +", as: "+srediAtribut(oprt.getLeft());
                }
                str +=" }";
                adaptedOprt.add(str);
                str = "";
                continue;

            }

            if(oprt instanceof Like){//sintaksa zaa like: a like (nesto)
                str ="{ "+srediAtribut(oprt.getLeft());
                str+=": /";
                String temp = srediAtribut(oprt.getRight());
                temp = temp.substring(2,temp.length()-2);
                if(temp.contains("%")){//pita da li se % nalazi u stringu
                    if(temp.charAt(0) == '%'){//pita da li se % nalazi na pocetku
                        if(temp.charAt(temp.length()-1) == '%'){//pita da li se % nalazi na kraju
                            str+=temp.substring(1, temp.length()-1)+"/i";
                        }else{// u slucaju da pocinje sa % ali se ne zavrsava sa %
                            str+=temp.substring(1)+"$/i";
                        }
                    }else{//slucaj da ne pocinje sa %
                        if(temp.charAt(temp.length()-1) == '%'){//pita da li sadrzi % na kraju
                            str+="^"+temp.substring(0,temp.length()-1)+"/i";;
                        }
                        else str=srediAtribut(oprt.getLeft())+": /"+temp+"/i";
                    }
                }else str=srediAtribut(oprt.getLeft())+": /"+temp+"/i";
                str+=" }";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof And){
                // sastavljanje stringa za and
                str = "{ $and: [ {";
                str += adaptedOprt.get(adaptedOprt.size()-1)+" }";
                adaptedOprt.remove(adaptedOprt.size()-1);
                i++;
                str +=", {"+this.stringConverter(clause.getOperators().get(i))+" }";

                if(i<=clause.getOperators().size()-2)
                    if(clause.getOperators().get(i+1) instanceof And){
                        i+=2;
                        str +=", {"+this.stringConverter(clause.getOperators().get(i))+ " }";
                    }
                
                str += " ] }";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            if(oprt instanceof Or){
                // sastavljanje stringa za or
                str = "{ $or: [ {";
                str += adaptedOprt.get(adaptedOprt.size()-1)+" }";
                adaptedOprt.remove(adaptedOprt.size()-1);
                i++;
                str +=", {"+this.stringConverter(clause.getOperators().get(i))+" }";
                if(i<=clause.getOperators().size()-2)
                    if(clause.getOperators().get(i+1) instanceof Or){
                        i+=2;
                        str +=", {"+this.stringConverter(clause.getOperators().get(i))+" }";
                    }

                str += " ] }";
                adaptedOprt.add(str);
                str = "";
                continue;

            }
            adaptedOprt.add(stringConverter(oprt));
        }
    }
    public String stringConverter(Oprt oprt){
        //pomocna funkcija za pripremu String-ova za podupite
        String str;
        if(oprt instanceof Sum){
            String buf = srediAtribut(oprt.getRight());
            if(buf.contains("*"))buf = "1";
            str = "{ $sum: " + buf+" }";
            return str;
        }
        if(oprt instanceof Count){
            String buf = srediAtribut(oprt.getRight());
            if(buf.contains("*"))buf = "1";
            str = "{ $sum: " + buf+" }";
            return str;
        }
        if(oprt instanceof Avg){
            str = "{ $avg: " + srediAtribut(oprt.getRight())+" }";
            return str;
        }
        if(oprt instanceof Max){
            str = "{ $max: " + srediAtribut(oprt.getRight())+" }";
            return str;
        }
        if(oprt instanceof Min){
            str = "{ $min: " + srediAtribut(oprt.getRight())+" }";
            return str;
        }
        if(oprt instanceof Equals){
            // sastavljanje stringa za =
            String left, right;

            left = srediAtribut(oprt.getLeft());

            right = srediAtribut(oprt.getRight());

            str = "{ "+left + ": { $eq: " + right +" } }";
            return str;
        }
        if(oprt instanceof GreaterThan){
            // sastavljanje stringa za >/>=
            String left, right;

            left = srediAtribut(oprt.getLeft());//sredjuje levi atribut

            right = srediAtribut(oprt.getRight());//sredjuje desni atribut

            str = "$gt";//provera da li je u pitanju >= ili >
            if(((GreaterThan) oprt).isEqual())str+="e";
            str = "{ "+left+": { "+str+": "+right +" } }";
            return str;
        }
        if(oprt instanceof LowerThan){
            // sastavljanje stringa za </<=
            String left, right;

            left = srediAtribut(oprt.getLeft());

            right = srediAtribut(oprt.getRight());

            str = "$lt";
            if(((LowerThan) oprt).isEqual())str+="e";
            str = "{ "+left+": { "+str+": "+right +" } }";
            return str;
        }
        if(oprt instanceof In){
            str = srediAtribut(oprt.getLeft()) + ": { $in: [ "; // pocetni deo adaptiranog mongu koda
            if(!((In) oprt).getColumnStrings().isEmpty()){//U slucaju da ne sadrzi podupit popunjava atribute i zatvara zagrade
                for(int i = 0; i < ((In) oprt).getColumnStrings().size(); i++){
                    str += srediAtribut(((In) oprt).getColumnStrings().get(i));
                    if(i<((In) oprt).getColumnStrings().size()-1)str+=", ";
                }
                return str + " ] }";
            }
            if(((In) oprt).getPodupit() != null){ // ako sadrzi podupit onda ostavlja otvorenu zagradu bez atributa i priprema podupit
                podupitAdapter = (ClauseAdapter) AdapterFactoryUtils.getFactory(((In) oprt).getPodupit()).createAdapter(((In) oprt).getPodupit());
                podupitAdapter.fillOutList();

                return str;
            }
            return "";
        }

        if(oprt == null) return "";

        str = "\""+oprt.getValue()+"\"";

        return str;

    }
    public String srediAtribut(Oprt oprt){//Proverava prosledjen atribut i pretvata u String
        String str = "";
        str = oprt.getValue();
            if(! (oprt instanceof ColumnString)){ // u slucaju da je atribut novi Oprt onda zove stringConverter za njega
                str = stringConverter(oprt);
            }else{// u suprotnom je samo atribut i pretvara se u string
                try {
                    Integer.parseInt(oprt.getValue());
                }catch (NumberFormatException nfe){
                    str  = "\""+str+"\"";
                }
            }


        return str;
    }

}