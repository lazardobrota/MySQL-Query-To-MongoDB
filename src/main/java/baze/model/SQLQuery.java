package baze.model;

import baze.model.implementation.*;
import baze.model.implementation.operators.Join;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SQLQuery implements AClause{
    //TODO Ideja: prolazi kroz listu reci i provera da li postoji u hashmapi, ako postoji upise je u hashmapu i stavi to iz hashmap u neku listu
    // zatim tu listu dalje prosledjuje gde treba
    private static int i = 0; // mora da bude static da bi cuvao ako se prolazi kroz podupit gde je stao posle podupita

    private List<AClause> claues = new ArrayList<>();
    private Map<String, Clause> hashMap = new HashMap<>(); // cuva sve sto moze da se pravi

    //TODO Ide sada ( select ), znaci zagrad space dawdawdawdaw space zagrada
    public SQLQuery() {
        hashMap.put("from", new From("from"));
        hashMap.put("group", new GroupBy("groupby")); // TODO Treba da se stavi group by al nisam siguran kako dve reci da gledam
        hashMap.put("having", new Having("having")); //hashMap.put("avg", new Avg("avg")); //hashMap.put("max", new Max("max")); //hashMap.put("min", new Min("min"));
        //hashMap.put("join", new Join("join")); //hashMap.put("on", new On("on")); // hashMap.put("using", new Using("using"));
        hashMap.put("select", new Select("select"));
        hashMap.put("where", new Where("where")); //hashMap.put("like", new Like("like"));
    }

    public void strToObj(String query) {
        query = query.toLowerCase(); // postavlja se sva slova na lowercase
        String[] line = query.split(" "); // po space razdvaja reci

        while (i < line.length) {
            if (hashMap.containsKey(line[i])) {
                //Podupit
                if (i - 1 > 0 && line[i].matches("select")) {

                    //claues.add(hashMap.get(line[i])); // dodaje u listu ali kao novi SQLQuery
                    continue;
                }
                claues.add(hashMap.get(line[i])); // dodaje u listu
                int j = i + 1;
                //Trazi sledecu kljucnu rec da bi znali granice od trenutne
                while (j < line.length && !hashMap.containsKey(line[j])) {
                    j++;
                }
                hashMap.get(line[i]).fillOut(line, i, j); // poziva funkciju posebnu za svaku funkciju
                i = j;
                continue;
            }

            i++;
        }

        /*TODO ovo je za validator
        //Ako nema select ili from nema sta dalje da gleda
        if (!line[0].matches("select") || !line[2].matches("from"))
            return;
        */

    }
}
