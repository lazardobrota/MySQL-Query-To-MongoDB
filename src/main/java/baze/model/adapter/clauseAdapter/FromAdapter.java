package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.From;

public class FromAdapter extends ClauseAdapter {
    public FromAdapter(From from) {
        clause = from;
    }
}
