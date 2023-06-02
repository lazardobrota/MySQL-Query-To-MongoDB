package baze.model.adapter;

import baze.model.implementation.Clause;

import java.util.ArrayList;
import java.util.List;

public class ClauseAdapter implements Adapter{
    protected Clause clause;
    protected List<String> adaptedOprt;

    public ClauseAdapter(Clause clause) {
        this.clause = clause;
        this.adaptedOprt = new ArrayList<>();
    }

    public List<String> getOperations(){
        return null;
    }
}
