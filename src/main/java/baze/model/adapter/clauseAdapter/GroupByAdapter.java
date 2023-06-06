package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import baze.model.implementation.operators.Oprt;

public class GroupByAdapter extends ClauseAdapter {
    public GroupByAdapter(){
        super();
    }
    public GroupByAdapter(Clause clause) {
        super(clause);
    }

    @Override
    public void fillOutList() {

        //Pravi za _id sta sve treba u njega da stavi
        for (int i = 0; i < clause.getOperators().size(); i++) {
            adaptedOprt.add(clause.getOperators().get(i).getValue() + ": " + "\"$" + clause.getOperators().get(i).getValue() + "\"");
        }
    }

    @Override
    public String stringConverter(Oprt oprt) {
        return null;
    }

    //Ovo se poziva umesto toString
    public String groupByToString(SelectAdapter selectAdapter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{_id: {");
        for (String s : adaptedOprt) {
            stringBuilder.append(s).append(", ");
        }

        //Uklanja zarez sa kraja
        stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 2) + "}");

        for (String s : selectAdapter.getAdaptedOprt()) {
            //Ako ima agregaciju da je doda u $group
            if (s.contains("$max") || s.contains("$avg") || s.contains("$min") || s.contains("$count") || s.contains("$sum"))
                stringBuilder.append(", ").append(s);
        }
        return stringBuilder.append("}").toString();
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{_id: {");
        for (String s : adaptedOprt) {
            stringBuilder.append(s).append(", ");
        }
        //Uklanja zarez sa kraja
        return stringBuilder.substring(0, stringBuilder.length() - 2) + "}}";
    }

    @Override // ClauseAdapter je ovde SelectAdapter
    public String adapterToString(ClauseAdapter clauseAdapter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{_id: {");
        for (String s : adaptedOprt) {
            stringBuilder.append(s).append(", ");
        }

        //Uklanja zarez sa kraja
        stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 2) + "}");

        for (String s : clauseAdapter.getAdaptedOprt()) {
            //Ako ima agregaciju da je doda u $group
            if (s.contains("$max") || s.contains("$avg") || s.contains("$min") || s.contains("$count") || s.contains("$sum"))
                stringBuilder.append(", ").append(s);
        }
        return stringBuilder.append("}").toString();
    }
}
