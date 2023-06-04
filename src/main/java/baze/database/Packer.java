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
                String name = split[i].substring(split[i].indexOf('\"') + 1); // uklanja sa pocetka "
                name = name.substring(0, name.indexOf('\"')); //uklanja sa kraja "
                this.columnNames.add(name);// dodaje ime kolone
                continue;
            }

            //Dodaje value za tu kolonu
            StringBuilder name = new StringBuilder();
            split[i] = split[i].substring(split[i].indexOf('\"') + 1); // uklanja sa pocetka "

            //Ako je -1 znaci da ne postoji " tj da je value sastavljen od dva dela na primer "de haank"
            //onda zna da treba da spoji trenutni i sledeci index
            while(i < split.length && split[i].indexOf('\"') == -1) {
                name.append(split[i]).append(" ");
                i++;
            }

            //Ako nikada ne bi trebalo da se desi
            if (i == split.length)
                break;

            name.append(split[i].substring(0, split[i].indexOf('\"'))); //uklanja sa kraja "
            this.values.add(name.toString()); //select z, zz, zz from employees
        }

        System.out.println(columnNames);
        System.out.println(values);
    }
}
