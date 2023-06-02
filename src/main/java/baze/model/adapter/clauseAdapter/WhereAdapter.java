package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Where;

import java.util.List;

public class WhereAdapter extends ClauseAdapter {


    public WhereAdapter(Where where) {
        clause = where;

    }

    @Override
    public List<String> getOperations() {
        return null;
    }
}
