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

    @Override
    public String toString() {
        List<String> oprtAdapters = getOperations();


        return "SelectAdapter{" +
                "select=" + clause +
                '}';
    }

    @Override
    public List<String> getOperations() {
        List<String> oprtAdapters = new ArrayList<>();

        for(int i = 0; i<clause.getOperators().size(); i++){
            Object o = clause.getOperators().get(i);
        }
        return oprtAdapters;
    }
}
