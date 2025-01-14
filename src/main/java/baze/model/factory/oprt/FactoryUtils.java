package baze.model.factory.oprt;

public class FactoryUtils {
    private static final AndFactory andFactory = new AndFactory();
    private static final OrFactory orFactory = new OrFactory();
    private static final GreaterThanFactory greaterThanFactory = new GreaterThanFactory();
    private static final LikeFactory likeFactory = new LikeFactory();
    private static final LowerThanFactory lowerThanFactory = new LowerThanFactory();
    private static final OnFactory onFactory = new OnFactory();

    private static final UsingFactory usingFactory = new UsingFactory();
    private static final EqualsFactory equalsFactory = new EqualsFactory();
    private static final AvgFactory avgFactory = new AvgFactory();
    private static final MaxFactory maxFactory = new MaxFactory();
    private static final MinFactory minFactory = new MinFactory();
    private static final JoinFactory joinFactory = new JoinFactory();
    private static final InFactory inFactory = new InFactory();
    private static final CountFactory countFactory = new CountFactory();
    private static final RownumFactory rownumFactory = new RownumFactory();
    private static final ColumnStringFactory columnStringFactory = new ColumnStringFactory();
    private static final SumFactory sumFactory = new SumFactory();


    public static OprtFactory getFactory(String operation) {
        if(operation.contains("sum()") || operation.matches("sum"))
            return sumFactory;
        if(operation.matches("rownum"))
            return rownumFactory;
        if(operation.matches("count") || operation.contains("count("))
            return countFactory;
        if (operation.matches("and"))
            return andFactory;
        if (operation.matches("or"))
            return orFactory;
        if (operation.matches(">") || operation.matches(">="))
            return greaterThanFactory;
        if (operation.matches("like") || operation.contains("like("))
            return likeFactory;
        if (operation.matches("<") || operation.matches("<="))
            return lowerThanFactory;
        if (operation.matches("on") || operation.contains("on("))
            return onFactory;
        if (operation.matches("using") || operation.contains("using("))
            return usingFactory;
        if (operation.matches("="))
            return equalsFactory;
        if (operation.matches("avg") || operation.contains("avg("))
            return avgFactory;
        if (operation.matches("max") || operation.contains("max("))
            return maxFactory;
        if (operation.matches("min") || operation.contains("min("))
            return minFactory;
        if (operation.matches("sum") || operation.contains("sum("))
            return sumFactory;
        if (operation.equals("join"))
            return joinFactory;
        if (operation.equals("in"))
            return inFactory;

        return columnStringFactory; // Kolumn String da se gleda kao null
    }
}
