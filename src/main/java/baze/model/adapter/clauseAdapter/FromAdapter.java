package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import baze.model.implementation.From;

public class FromAdapter extends ClauseAdapter {

    public  FromAdapter(){
        super();
    }

    public FromAdapter(Clause clause) {
        super(clause);
    }
    @Override
    public String toString() {
        return "db."+this.adaptedOprt.get(0).substring(1,adaptedOprt.get(0).length()-1)+".find(";
    }
}
