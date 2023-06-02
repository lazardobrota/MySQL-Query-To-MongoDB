package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.factory.adapter.AdapterFactoryUtils;
import baze.model.implementation.Clause;
import baze.model.implementation.Select;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
public class SelectAdapter extends ClauseAdapter {

    public SelectAdapter(Clause clause) {
        super(clause);
    }

}
