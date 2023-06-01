package baze.gui.validator;

import baze.gui.view.MainFrame;
import baze.model.implementation.AClause;
import baze.model.implementation.SQLQuery;

public class ThirdRule extends Rule{
    public ThirdRule(String name) {
        super(name);
    }

    @Override
    public boolean ruleCheck(SQLQuery sqlQuery) {


        //message();
        //return false;
        return true;
    }

    @Override
    public void message() {
        MainFrame.getInstance().errorMessage("Where ne moze da ima agregacije");
    }
}
