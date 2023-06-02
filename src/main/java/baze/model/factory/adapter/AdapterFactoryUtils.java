package baze.model.factory.adapter;

import baze.model.factory.adapter.clauseAdapterFactorys.SelectAdapterFactory;
import baze.model.factory.adapter.clauseAdapterFactorys.WhereAdapterFactory;
import baze.model.implementation.Select;
import baze.model.implementation.Where;

public class AdapterFactoryUtils {
    private static final SelectAdapterFactory selectAdapterFactory = new SelectAdapterFactory();
    private static final WhereAdapterFactory whereAdapterFactory = new WhereAdapterFactory();



    public static AdapterFactory getFactory(Object obj){

        if(obj instanceof Select)
            return selectAdapterFactory;
        if(obj instanceof Where)
            return whereAdapterFactory;


        return null;

    }
}
