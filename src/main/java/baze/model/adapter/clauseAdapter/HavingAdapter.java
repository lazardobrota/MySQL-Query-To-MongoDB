package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import baze.model.implementation.Having;

public class HavingAdapter extends ClauseAdapter {

    public HavingAdapter(){
        super();
    }
    public HavingAdapter(Clause clause) {
        super(clause);
    }
    @Override
    public String toString() {
        return "having{" +
                adaptedOprt +
                '}';
    }
}
