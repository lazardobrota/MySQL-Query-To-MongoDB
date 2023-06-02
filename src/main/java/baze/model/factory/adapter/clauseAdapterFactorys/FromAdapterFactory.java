package baze.model.factory.adapter.clauseAdapterFactorys;

import baze.model.adapter.Adapter;
import baze.model.adapter.clauseAdapter.FromAdapter;
import baze.model.factory.adapter.AdapterFactory;
import baze.model.implementation.Clause;

public class FromAdapterFactory extends AdapterFactory {
    @Override
    public Adapter createAdapter(Object ob) {
        return new FromAdapter((Clause) ob);
    }
}
