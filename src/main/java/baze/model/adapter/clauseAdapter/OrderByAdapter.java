package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import baze.model.implementation.operators.Oprt;

public class OrderByAdapter extends ClauseAdapter {

    public  OrderByAdapter(){
        super();
    }
    public OrderByAdapter(Clause clause) {
        super(clause);
    }

    @Override
    public void fillOutList() {

        //clause.getOperators().size() - 1 jer je poslednji operand 1 ili -1
        for (int i = 0; i < clause.getOperators().size() - 1; i += 2) {
            //Uzima trenutnu kolonu i za da je sledeci broj 1 ili -1
            adaptedOprt.add(clause.getOperators().get(i).getValue() + ": " + clause.getOperators().get(i + 1).getValue());
        }
    }

    @Override
    public String stringConverter(Oprt oprt) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ "); //posle $sort:
        for (int i = 0; i < adaptedOprt.size(); i++) {
            stringBuilder.append(adaptedOprt.get(i)).append(", ");
        }
        String str = stringBuilder.substring(0, stringBuilder.length() - 2) + "}"; // "}" oznaci kra
        return str;
    }

    @Override
    public String adapterToString(ClauseAdapter clauseAdapter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ "); //posle $sort:
        for (int i = 0; i < adaptedOprt.size(); i++) {
            stringBuilder.append(adaptedOprt.get(i)).append(", ");
        }
        String str = stringBuilder.substring(0, stringBuilder.length() - 2) + "}"; // "}" oznaci kra
        return str;
    }
}
