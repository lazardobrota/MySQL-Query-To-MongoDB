package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Having;

public class HavingAdapter extends ClauseAdapter {

    public HavingAdapter(Having having) {
        clause = having;
    }
}
