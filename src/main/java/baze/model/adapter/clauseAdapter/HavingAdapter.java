package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import baze.model.implementation.Having;
import baze.model.implementation.operators.Oprt;

public class HavingAdapter extends ClauseAdapter {

    public HavingAdapter(){
        super();
    }
    public HavingAdapter(Clause clause) {
        super(clause);
    }
    @Override
    public String toString() {
        return "{" +
                adaptedOprt +
                '}';
    }
    //TODO Isto ko where kopiraj, samo za agregacije

    @Override
    public String adapterToString(ClauseAdapter clauseAdapter) {
        return null;
    }

    @Override
    public void fillOutList() {

    }

    @Override
    public String stringConverter(Oprt oprt) {
        return null;
    }
}
