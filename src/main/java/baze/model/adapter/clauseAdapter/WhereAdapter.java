package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import baze.model.implementation.Where;

import java.util.List;

public class WhereAdapter extends ClauseAdapter {

    public WhereAdapter() {
        super();
    }

    public WhereAdapter(Clause clause) {
        super(clause);
    }
    @Override
    public String toString() {
        return "where{" +
                adaptedOprt +
                '}';
    }
}
