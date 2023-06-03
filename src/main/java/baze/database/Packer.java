package baze.database;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Packer {
    String document;

    List<String> columnNames;
    List<String> values;

    public Packer(String document) {
        this.document = document;
        columnNames = new ArrayList<>();
        values = new ArrayList<>();
    }

    public void translate() {
        String[] split = document.split(" ");

        for (int i = 0; i < split.length; i++) {

            //Preskace ako postoji { jer znaci da nije dosao unutrasnjih zagrada
            if (i + 1 < split.length && (split[i + 1].contains("{")))
                continue;

            //Pronasao je ime kolone
            if (split[i].contains("\":")) {
                //1 - presko ", split[i].length()-2 - uklanja ":
                String name = split[i].substring(1, split[i].length() - 2);
                this.columnNames.add(name);// dodaje ime kolone
                continue;
            }

            //Dodaje value za tu kolonu
            // 1 - uklanja ", split[i].length() - 1 - uklanja
            String name = split[i].substring(1 , split[i].length() - 1);
            this.values.add(name);
        }

        //System.out.println(columnNames);
        //System.out.println(values);
    }
}
