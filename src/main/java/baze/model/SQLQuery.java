package baze.model;

import baze.model.implementation.*;
import baze.model.implementation.agregation.Avg;
import baze.model.implementation.agregation.Max;
import baze.model.implementation.agregation.Min;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLQuery {
    //TODO Ideja: prolazi kroz listu reci i provera da li postoji u hashmapi, ako postoji upise je u hashmapu i stavi to iz hashmap u neku listu
    //TODO zatim tu listu dalje prosledjuje gde treba

    List<Clause> claues = new ArrayList<>();
    HashMap<String, Clause> hashMap = new HashMap<>(); // cuva sve sto moze da se pravi

    public SQLQuery() {
        hashMap.put("from", new From("from"));
        hashMap.put("groupby", new GroupBy("groupby"));
        hashMap.put("having", new Having("having")); //hashMap.put("avg", new Avg("avg")); //hashMap.put("max", new Max("max")); //hashMap.put("min", new Min("min"));
        hashMap.put("join", new Join("join")); //hashMap.put("on", new On("on")); // hashMap.put("using", new Using("using"));
        hashMap.put("select", new Select("select"));
        hashMap.put("where", new Where("where")); //hashMap.put("like", new Like("like"));
    }

    private void strToObj(String query) {
        query = query.toLowerCase(); // postavlja se sva slova na lowercase
        String[] line = query.split(" "); // po space razdvaja reci

        int i = 0;
        while (i < line.length) {
            if (hashMap.containsKey(line[i])) {
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
