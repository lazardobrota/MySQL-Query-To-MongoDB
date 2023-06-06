package baze.model.adapter.mapper;

import baze.model.adapter.ClauseAdapter;
import baze.model.adapter.clauseAdapter.*;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Mapper {
    private List<ClauseAdapter> clauseAdapters;
    private List<ClauseAdapter> checkers;
    private List<org.bson.Document> documents; // cuvace sve metode koje se prave, tehnicki kao ceo string kao kod za mongo, samo for ne cuvsa
    private String from = "employees"; // posebno za from, on se jedini koristi samo kao string, na pocetku samo stavljeno da ima barem emloyees
    private SelectAdapter selectAdapter; // na pocetku je false, ako postoji project onda se stavlja na true

    public Mapper(List<ClauseAdapter> clauseAdapters) {
        this.clauseAdapters = clauseAdapters;
        this.documents = new ArrayList<>();

        checkers = new ArrayList<>();

        //Jako je bitan redosled ovih
        checkers.add(new FromAdapter());    // nista dodatno ne treba
        checkers.add(new WhereAdapter());   // $match:
        checkers.add(new HavingAdapter());  // $match:
        checkers.add(new SelectAdapter());  // $project:
        checkers.add(new GroupByAdapter()); // $group:
        checkers.add(new OrderByAdapter()); // $sort:
    }

    //IDEJA JE DA PRODJE REDOM KAKO BI ISLO U MONGO I ONDA TRAZI U CLAUSEADAPTERS LISTI TAJ KOJI CE SE POTREFITI SA ONIM KOJI JE TRENUTNO SETOVA
    public String combineClauses() {

        //Prolazi kroz sve upite redom
        for (ClauseAdapter checker : checkers) {
            StringBuilder stringBuilder = new StringBuilder(); // spaja stvari u stringbuilder

            //Prolazi kroz citavu listu klausovih adaptera koji su zapisani na mongo foricu i trazi onaj koji odgovara
            for (ClauseAdapter adapter : clauseAdapters) {
                //Ako je from adapter
                if (checker instanceof FromAdapter && adapter instanceof FromAdapter) { //todo treba join da se uradi
                    adapter.fillOutList();
                    from = ((FromAdapter) adapter).getFrom(); // uzima za from deo
                    //stringBuilder.append("{").append(adapter.adapterToString(adapter)).append("}");
                    //documents.add(org.bson.Document.parse(stringBuilder.toString()));
                    break;
                }
                //Ako je select adapter
                if (checker instanceof SelectAdapter && adapter instanceof SelectAdapter) {
                    adapter.fillOutList();
                    if (adapter.toString().length() == 0)
                        break;
                    selectAdapter = (SelectAdapter) adapter;
                    stringBuilder.append("{ $project: ").append(adapter.adapterToString(adapter)).append("}");
                    documents.add(org.bson.Document.parse(stringBuilder.toString()));
                    break;
                }
                //Ako je having
                if (checker instanceof HavingAdapter && adapter instanceof HavingAdapter) {
                    adapter.fillOutList();
                    stringBuilder.append("{ $match: ").append(adapter.adapterToString(adapter)).append("}");
                    documents.add(org.bson.Document.parse(stringBuilder.toString()));
                    break;
                }
                //Ako je where
                if (checker instanceof WhereAdapter && adapter instanceof WhereAdapter) { //todo treba podupit da se uradi i druge verzije like da se skloni {}, 'S%', ...
                    adapter.fillOutList();
                    stringBuilder.append("{ $match: ").append(adapter.adapterToString(adapter)).append("}");
                    documents.add(org.bson.Document.parse(stringBuilder.toString()));
                    break;
                }
                //Ako je group by
                if (checker instanceof GroupByAdapter && adapter instanceof GroupByAdapter) { //todo prebaci sort u group by ako group by postoji
                    adapter.fillOutList();
                    documents.remove(documents.size() - 1); // uklanja select jer je select provera
                    stringBuilder.append("{ $group: ").append(adapter.adapterToString(selectAdapter)).append("}");
                    documents.add(org.bson.Document.parse(stringBuilder.toString()));
                    break;
                }
                //Ako je order by
                if (checker instanceof OrderByAdapter && adapter instanceof OrderByAdapter) {
                    stringBuilder.append("{ $sort: ").append(adapter.adapterToString(adapter)).append("}");
                    documents.add(org.bson.Document.parse(stringBuilder.toString()));
                    break;
                }
            }
        }

        return "";
    }
}
