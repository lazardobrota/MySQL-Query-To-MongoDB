package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.factory.adapter.AdapterFactoryUtils;
import baze.model.implementation.Clause;
import baze.model.implementation.From;
import baze.model.implementation.operators.*;
import baze.model.implementation.operators.agregation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FromAdapter extends ClauseAdapter {

    private String from;
    public  FromAdapter(){
        super();
    }

    public FromAdapter(Clause clause) {
        super(clause);
    }


    @Override
    public void fillOutList() {
        Oprt oprt;
        String str;
        // prolazi kroz listu operatora i u zavisnosti od njegove instance odgovarajuce ga konvertuje u String
        for(int i = 0; i < clause.getOperators().size(); i++){
            oprt = clause.getOperators().get(i);

            if(oprt instanceof Join){
                this.setFrom(oprt.getLeft().getValue());
                join = combineColumnNames(oprt.getLeft(), oprt.getRight());
                str = "{ $lookup: { from: "+srediAtribut(oprt.getRight());
                i++;
                Oprt next = clause.getOperators().get(i);
                if(next instanceof On){
                    str +=", localField: "+srediAtribut(next.getRight().getLeft())
                            +", foreignField: "+srediAtribut(next.getRight().getLeft())
                            +", as: " + combineColumnNames(oprt.getLeft(), oprt.getRight());
                }else {//Using
                    str +=", localField: "+srediAtribut(next.getRight())
                            +", foreignField: "+srediAtribut(next.getRight())
                            +", as: " + combineColumnNames(oprt.getLeft(), oprt.getRight());
                }
                str +=" } ";
                adaptedOprt.add(str);
                str = "";
                continue;
            }
            adaptedOprt.add(stringConverter(oprt));
        }
    }

    @Override
    public String stringConverter(Oprt oprt) {
        //pomocna funkcija za pripremu String-ova za podupite
        String str;
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
            str = "{ "+ srediAtribut(oprt.getLeft()) + ": { $in: [ "; // pocetni deo adaptiranog mongu koda
            if(!((In) oprt).getColumnStrings().isEmpty()){//U slucaju da ne sadrzi podupit popunjava atribute i zatvara zagrade
                for(int i = 0; i < ((In) oprt).getColumnStrings().size(); i++){
                    str += srediAtribut(((In) oprt).getColumnStrings().get(i));
                    if(i<((In) oprt).getColumnStrings().size()-1)str+=", ";
                }
                return str + " ] }}";
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
        this.setFrom(oprt.getValue());
        return str;
    }

    @Override
    public String toString() {
        String str = this.adaptedOprt.get(0).substring(1,adaptedOprt.get(0).length()-1);
        return this.adaptedOprt.get(0).substring(1,adaptedOprt.get(0).length()-1);
    }

    @Override
    public String adapterToString(ClauseAdapter clauseAdapter) {
        return this.adaptedOprt.get(0).substring(1,adaptedOprt.get(0).length()-1);
    }
}
