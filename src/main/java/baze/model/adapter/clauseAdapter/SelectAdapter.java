package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import lombok.Setter;


@Setter
public class SelectAdapter extends ClauseAdapter {

    public SelectAdapter(Clause clause) {
        super(clause);
    }

}
