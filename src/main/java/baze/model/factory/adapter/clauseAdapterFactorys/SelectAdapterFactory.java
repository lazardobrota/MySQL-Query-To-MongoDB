package baze.model.factory.adapter.clauseAdapterFactorys;

import baze.model.adapter.Adapter;
import baze.model.adapter.clauseAdapter.SelectAdapter;
import baze.model.factory.adapter.AdapterFactory;
import baze.model.implementation.Select;

public class SelectAdapterFactory extends AdapterFactory {
    @Override
    public Adapter createAdapter(Object ob) {
        return new SelectAdapter((Select) ob);
    }
}
