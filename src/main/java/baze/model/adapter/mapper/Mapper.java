package baze.model.adapter.mapper;

import baze.model.adapter.ClauseAdapter;
import baze.model.adapter.clauseAdapter.*;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    List<ClauseAdapter> clauseAdapters;
    List<ClauseAdapter> checkers;
    public Mapper(List<ClauseAdapter> clauseAdapters) {
        this.clauseAdapters = clauseAdapters;

        checkers = new ArrayList<>();

        //Jako je bitan redosled ovih
        /* TODO Fali prazan konstruktor u adapterima, nisam ih menjao(tj dodao) jer cekam andreju da mi posalje prvo nesto iz njih
        checkers.add(new FromAdapter());
        checkers.add(new GroupByAdapter());
        checkers.add(new HavingAdapter());
        checkers.add(new OrderByAdapter());
        checkers.add(new SelectAdapter());
        checkers.add(new WhereAdapter());
         */
    }

    //TODO IDEJA JE DA PRODJE REDOM KAKO BI ISLO U MONGO I ONDA TRAZI U CLAUSEADAPTERS LISTI TAJ KOJI CE SE POTREFITI SA ONIM KOJI JE TRENUTNO SETOVA
    public String combineClauses() {
        StringBuilder stringBuilder = new StringBuilder(); // rezultijuci string koji pise kao mongoDB

        //Prolazi kroz sve upite redom
        for (int k = 0; k < checkers.size(); k++) {
            //Prolazi kroz citavu listu klausovih adaptera koji su zapisani na mongo foricu
            for (ClauseAdapter adapter : clauseAdapters) {
                //Ako je select adapter
                if (!(adapter instanceof SelectAdapter)) {
                }
            }
        }

        return "";
    }
}
