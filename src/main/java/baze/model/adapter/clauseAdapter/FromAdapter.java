package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import baze.model.implementation.From;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FromAdapter extends ClauseAdapter {

    private String from;
    private String join;
    public  FromAdapter(){
        super();
        join = ""; // na pocetku je prazno i dodaje se svima, ako postoji join on uzima naziv i dodaje ga svima, primer elementdepartment.first_name
    }

    public FromAdapter(Clause clause) {
        super(clause);
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
