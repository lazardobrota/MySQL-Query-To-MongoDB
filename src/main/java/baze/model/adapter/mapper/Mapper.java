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
    List<ClauseAdapter> clauseAdapters;
    List<ClauseAdapter> checkers;
    List<Document> documents; // cuvace sve metode koje se prave, tehnicki kao ceo string kao kod za mongo, samo for ne cuvsa
    String from = "employees"; // posebno za from, on se jedini koristi samo kao string, na pocetku samo stavljeno da ima barem emloyees
    public Mapper(List<ClauseAdapter> clauseAdapters) {
        this.clauseAdapters = clauseAdapters;
        this.documents = new ArrayList<>();

        checkers = new ArrayList<>();

        //Jako je bitan redosled ovih
        //TODO Fali prazan konstruktor u adapterima, nisam ih menjao(tj dodao) jer cekam andreju da mi posalje prvo nesto iz njih
        checkers.add(new FromAdapter());
        checkers.add(new WhereAdapter());
        checkers.add(new HavingAdapter());
        checkers.add(new SelectAdapter()); // .project
        checkers.add(new GroupByAdapter());
        checkers.add(new OrderByAdapter());
    }

    //TODO IDEJA JE DA PRODJE REDOM KAKO BI ISLO U MONGO I ONDA TRAZI U CLAUSEADAPTERS LISTI TAJ KOJI CE SE POTREFITI SA ONIM KOJI JE TRENUTNO SETOVA
    public String combineClauses() {

        //Prolazi kroz sve upite redom
        for (ClauseAdapter checker : checkers) {
            StringBuilder stringBuilder = new StringBuilder(); // spaja stvari u stringbuilder

            //Prolazi kroz citavu listu klausovih adaptera koji su zapisani na mongo foricu i trazi onaj koji odgovara
            for (ClauseAdapter adapter : clauseAdapters) {
                //Ako je from adapter
                if (checker instanceof FromAdapter && adapter instanceof FromAdapter) {
                    from = adapter.toString(); // uzima za from deo
                    break;
                }
                //Ako je select adapter
                if (checker instanceof SelectAdapter && adapter instanceof SelectAdapter) {
                    stringBuilder.append("{ $project: ").append(adapter.toString()).append("}");
                    documents.add(Document.parse(stringBuilder.toString()));
                    break;
                }
                //Ako je having
                if (checker instanceof HavingAdapter && adapter instanceof HavingAdapter) {
                    break;
                }
                //Ako je where
                if (checker instanceof WhereAdapter && adapter instanceof WhereAdapter) {
                    break;
                }
                //Ako je group by
                if (checker instanceof GroupByAdapter && adapter instanceof GroupByAdapter) {
                    break;
                }
                //Ako je order by
                if (checker instanceof OrderByAdapter && adapter instanceof OrderByAdapter) {
                    break;
                }
            }
        }

        return "";
    }
}
