package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;

public class OrderByAdapter extends ClauseAdapter {

    public  OrderByAdapter(){
        super();
    }
    public OrderByAdapter(Clause clause) {
        super(clause);
    }

    @Override
    public void fillOutList() {
        adaptedOprt.add("{ " + clause.getOperators().get(0).getValue() + ": " + clause.getOperators().get(1).getValue() + "}");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < adaptedOprt.size(); i++) {
            stringBuilder.append(adaptedOprt.get(i)).append(", ");
        }
        String str = stringBuilder.substring(0, stringBuilder.length() - 2);
        return str;
    }
}
