package baze.model.implementation.operators;

import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.AClause;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//In se koristi za podupit
public class In extends Oprt{
// izgled u okviru unosa: in (nesto)

    //TODO Jedini IN ima promenljive unutar seebe
    private AClause podupit;
    private List<ColumnString> columnStrings = new ArrayList<>();

    @Override
    public void doOperation(String[] line, int c) {
        left = FactoryUtils.getFactory(line[c - 1]).getOprt(line[c - 1]);

        //Ako ima (select znaci da je podupit i lista je prazna
        if (line[c + 1].equals("(select"))
            return;

        //Nema podupit
        c++; // da preskoci in
        //Do ne pronadjen ) za kraj skupa elemenata
        String[] arr = line.clone();
        arr[c] = arr[c].substring(1); // uklanja prvu zagradu
        while (!arr[c].contains(")")) {
            arr[c] = arr[c].substring(0, arr[c].length() - 1); // uklanja zarez
            columnStrings.add((ColumnString) FactoryUtils.getFactory(arr[c]).getOprt(arr[c++]));
        }
        arr[c] = arr[c].substring(0, arr[c].length() - 1);
        columnStrings.add((ColumnString) FactoryUtils.getFactory(arr[c]).getOprt(arr[c]));
    }


    @Override
    public String toString() {
        return "In{" +
                "podupit=" + podupit +
                ", columnStrings=" + columnStrings +
                ", left=" + left +
                ", right=" + right +
                ", value='" + value + '\'' +
                '}';
    }
}
