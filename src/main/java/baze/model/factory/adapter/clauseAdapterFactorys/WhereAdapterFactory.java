package baze.model.factory.adapter.clauseAdapterFactorys;

import baze.model.adapter.Adapter;
import baze.model.adapter.clauseAdapter.WhereAdapter;
import baze.model.factory.adapter.AdapterFactory;
import baze.model.implementation.Where;

public class WhereAdapterFactory extends AdapterFactory {
    @Override
    public Adapter createAdapter(Object ob) {
        return new WhereAdapter((Where) ob);
    }
}