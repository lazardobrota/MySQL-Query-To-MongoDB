package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;

public class GroupByAdapter extends ClauseAdapter {
    public GroupByAdapter(){
        super();
    }
    public GroupByAdapter(Clause clause) {
        super(clause);
    }
    @Override
    public String toString() {
        return "group by{" +
                adaptedOprt +
                '}';
    }
}
