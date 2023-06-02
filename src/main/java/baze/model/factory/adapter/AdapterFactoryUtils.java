package baze.model.factory.adapter;

import baze.model.factory.adapter.clauseAdapterFactorys.*;
import baze.model.implementation.*;

public class AdapterFactoryUtils {
    private static final SelectAdapterFactory selectAdapterFactory = new SelectAdapterFactory();
    private static final WhereAdapterFactory whereAdapterFactory = new WhereAdapterFactory();
    private static final FromAdapterFactory fromAdapterFactory = new FromAdapterFactory();
    private static final HavingAdapterFactory havingAdapterFactory = new HavingAdapterFactory();
    private static final OrderByAdapterFactory oBAdapterFactory = new OrderByAdapterFactory();
    private static final GroupByAdapterFactory gBAdapterFactory = new GroupByAdapterFactory();



    public static AdapterFactory getFactory(Object obj){

        if(obj instanceof GroupBy)
            return gBAdapterFactory;
        if(obj instanceof OrderBy)
            return oBAdapterFactory;
        if(obj instanceof From)
            return fromAdapterFactory;
        if(obj instanceof Having)
            return havingAdapterFactory;
        if(obj instanceof Select)
            return selectAdapterFactory;
        if(obj instanceof Where)
            return whereAdapterFactory;


        return null;

    }
}
