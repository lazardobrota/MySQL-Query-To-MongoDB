package baze.model.factory.adapter;

import baze.model.adapter.Adapter;
import baze.model.implementation.Clause;

public abstract class AdapterFactory {
    public Adapter getAdapter(Object ob) {
        return createAdapter(ob);
    }

    public abstract Adapter createAdapter(Object ob);
}
