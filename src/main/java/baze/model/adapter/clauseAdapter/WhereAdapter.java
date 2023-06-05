package baze.model.adapter.clauseAdapter;

import baze.model.adapter.ClauseAdapter;
import baze.model.implementation.Clause;
import baze.model.implementation.Where;

import java.util.List;

public class WhereAdapter extends ClauseAdapter {

    public WhereAdapter() {
        super();
    }

    public WhereAdapter(Clause clause) {
        super(clause);
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

    @Override
    public String adapterToString(ClauseAdapter clauseAdapter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < adaptedOprt.size(); i++) {
            stringBuilder.append(adaptedOprt.get(i)).append(", ");
        }
        String str = stringBuilder.substring(0, stringBuilder.length() - 2);
        return str;
    }
}
