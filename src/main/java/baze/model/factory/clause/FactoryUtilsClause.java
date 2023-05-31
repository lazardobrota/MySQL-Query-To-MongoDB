package baze.model.factory.clause;


public class FactoryUtilsClause {
    private static final FromFactory fromFactory = new FromFactory();
    private static final GroupByFactory groupFactory = new GroupByFactory();
    private static final HavingFactory havingFactory = new HavingFactory();
    private static final SelectFactory selectFactory = new SelectFactory();
    private static final WhereFactory whereFactory = new WhereFactory();

    public static ClauseFactory getFactory(String clause) {
        if (clause.equals("from"))
            return fromFactory;
        if (clause.equals("group"))
            return groupFactory;
        if (clause.equals("having"))
            return havingFactory;
        if (clause.equals("select") || clause.equals("(select"))
            return selectFactory;
        if (clause.equals("where"))
            return whereFactory;
        return null;
    }
}
