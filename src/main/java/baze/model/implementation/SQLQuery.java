package baze.model.implementation;

import baze.model.factory.clause.FactoryUtilsClause;
import baze.model.implementation.*;
import baze.model.implementation.operators.In;
import baze.model.implementation.operators.Join;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class SQLQuery implements AClause{

    private List<AClause> claues = new ArrayList<>();

    public SQLQuery() {
    }

    @Override
    public String toString() {
        return "SQLQuery{" +
                "claues=" + claues +
                '}';
    }

    public void strToObj(String query) {
        query = query.toLowerCase(); // postavlja se sva slova na lowercase
        String[] line = query.split(" "); // po space razdvaja reci

        int i = 0;
        while (i < line.length) {
            if (FactoryUtilsClause.getFactory(line[i]) != null) {
                //Podupit
                //Ako nije pocetni select i ako je (select
                if (i - 1 > 0 && line[i].equals("(select")) {
                    Stack<Character> zagrade = new Stack<>();
                    zagrade.add('('); // prva zagrada
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(line[i]).append(" "); // pravi string za podupit
                    //Dok ne dodje do kraja stringa ili dok se isprazni stack zagrada(svaki put kad se upare zagrade sklanjaju se sa steka) sto znaci da je dosao do kraja upita
                    // i = i + 1 da bi preskocilo (select
                    for (i = i + 1; i < line.length && !zagrade.isEmpty(); i++) { // OVO SAM ISKOPIRAO IZ MOG KODA IZ ALGORITAMA
                        //Prolazi kroz rec
                        for (int j = 0; j < line[i].length(); j++) {
                            char c = line[i].charAt(j);
                            if (c != ')') {
                                zagrade.push(c);
                                continue;
                            }

                            //Dok stack nije prazan i dok se ne pronadje otvorena zagrade sve se uklanja
                            while (!zagrade.isEmpty() && zagrade.peek() != '(') {
                                zagrade.pop();
                            }

                            //If ce uvek vaziti al za svaki slucaj sam dodao
                            if (!zagrade.isEmpty())
                                zagrade.pop(); // izbacuje tu jednu zagradu

                            //Resenje
                            if (zagrade.isEmpty()) {
                                //Poslednja rec ce imati ) u sebi pa da se ukloni
                                line[i] = line[i].substring(0, line[i].length() - 1); // bez zagrade na kraju
                                break;
                            }
                        }
                        stringBuilder.append(line[i]).append(" "); // pravi string za podupit
                    }
                    SQLQuery podupit = new SQLQuery();
                    podupit.strToObj(stringBuilder.toString());
                    // TODO Treba da se stavi podupit u neki operator
                    Clause where = (Clause) claues.get(claues.size() - 1); // uzima where clause
                    for (int j = 0; j < where.getOperators().size(); j++) {
                        if (where.getOperators().get(j) instanceof In) {
                            In in = (In) where.getOperators().get(j); // sigurno je poslednji in ako postoji podupit
                            if (in.getPodupit() != null)
                                continue;
                            in.setPodupit(podupit);
                            System.out.println(((Clause) claues.get(claues.size() - 1)).getOperators());
                            break;
                        }
                    }
                    //System.out.println(((SQLQuery)((In)((Clause) claues.get(claues.size() - 1)).getOperators().get(0)).getPodupit()).getClaues().get(0));
                    //claues.add(podupit); // dodaje u listu ali kao novi SQLQuery
                    continue;
                }
                Clause clause = Objects.requireNonNull(FactoryUtilsClause.getFactory(line[i])).getClause();
                claues.add(clause); // dodaje u listu
                int j = i + 1;
                //Trazi sledecu kljucnu rec da bi znali granice od trenutne
                while (j < line.length && FactoryUtilsClause.getFactory(line[j]) == null) {
                    j++;
                }

                clause.fillOut(line, i, j); // poziva funkciju posebnu za svaku funkciju
                i = j;
                continue;
            }

            i++;
        }
    }
}
