package baze.gui.validator;

import baze.gui.view.MainFrame;
import baze.model.implementation.AClause;
import baze.model.implementation.SQLQuery;
import baze.model.implementation.Select;
import baze.model.implementation.Where;
import baze.model.implementation.operators.*;

public class ThirdRule extends Rule{
    public ThirdRule(String name) {
        super(name);
    }

    @Override
    public boolean ruleCheck(SQLQuery sqlQuery) {

        boolean agregation = false;
        for (int i = 0; i < sqlQuery.getClaues().size(); i++) {

            // Dok ne nadje where preskace
            if (!(sqlQuery.getClaues().get(i) instanceof Where))
                continue;

            Where where = (Where) sqlQuery.getClaues().get(i);

            for (int j = 0; j < where.getOperators().size(); j++) {
                Oprt oprt = where.getOperators().get(j);

                //Pronasao je agregaciju
                if (oprt.getAgregation() != null) {
                    agregation = true;
                    break;
                }
            }

            //Ako nema agregaciju
            if (!agregation)
                return true;

            // Pronaso je where ako je dovde dosao i ne moze vise where od jednog da postoji tako da nema potreba da nastavi da gleda for petlju
            // ovo takodje znaci da je agregation == true pa postoji agregacija :(
            break;
        }

        //Ako where ne postoji
        if (!agregation)
            return true;

        message();
        return false;
    }

    @Override
    public void message() {
        MainFrame.getInstance().errorMessage("Where ne moze da ima agregacije");
    }
}

/*
@Override
    public boolean ruleCheck(SQLQuery sqlQuery) {

        boolean agregation = false;
        for (int i = 0; i < sqlQuery.getClaues().size(); i++) {

            // Dok ne nadje where preskace
            if (!(sqlQuery.getClaues().get(i) instanceof Where))
                continue;

            Where where = (Where) sqlQuery.getClaues().get(i);
            //Prolazi kroz sve operatore od where
            for (int j = 0; j < where.getOperators().size(); j++) {

                Oprt oprt = where.getOperators().get(j);

                //Ako operator ima agregaciju
                if (oprt.getAgregation() != null) {
                    agregation = true;
                    break;
                }

                //Ako je And ili Or
                if (oprt instanceof CombineOprt) {
                    CombineOprt combineOprt = (CombineOprt) oprt;
                    Oprt left = combineOprt.getLeft();
                    Oprt right = combineOprt.getRight();

                    //Postoji agregacija kod levog ili desnog operatora
                    if (left.getAgregation() != null || right.getAgregation() != null) {
                        agregation = true;
                        break;
                    }

                    // Ako je In operator
                    // Ako ima in znaci da ima podupit i krece ponovo ruleCheck rekurzivo
                    if (checkInAgregation(left) || checkInAgregation(right)) {
                        agregation = true;
                        break;
                    }
                }
                else { // Ako je In operator
                    // Ako ima in znaci da ima podupit i krece ponovo ruleCheck rekurzivo
                    if (checkInAgregation(oprt)) {
                        agregation = true;
                        break;
                    }
                }
            }

            //Nema agregacije
            if (!agregation)
                return true;

            //Postoji agregacija
            break;
        }
        //Nema agregacije
        if (!agregation)
            return true;

        message();
        return false;
    }

    private boolean checkInAgregation(Oprt oprt) {
        if (oprt instanceof In) {
            In in = (In) oprt;
            if (ruleCheck((SQLQuery) in.getPodupit())) {
                return false;
            }
        }

        return true;
    }
 */
