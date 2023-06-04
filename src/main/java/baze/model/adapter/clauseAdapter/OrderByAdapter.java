package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;

public class OrderByAdapter extends ClauseAdapter {

    public  OrderByAdapter(){
        super();
    }
    public OrderByAdapter(Clause clause) {
        super(clause);
    }
    @Override
    public String toString() {
        return "{" + adaptedOprt + '}';
    }
}
