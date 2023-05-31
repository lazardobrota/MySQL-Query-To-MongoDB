package baze.model.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupBy extends Clause{
    private List<String> columns;

    public GroupBy() {
        columns = new ArrayList<>();
    }

    @Override
    public void fillOut(String[] lines, int l, int r) {
        //Dodaje sve kolone tabela u listu
        for (int i = l + 2; i < r; i++) {
            //Ako treba da ukloni zares izmedju njih
            String[] arr = lines[i].split(",");// deli po zarecu al bice samo jedna rec u teksu, samo mi je lakse ovako da radim
            columns.addAll(Arrays.asList(arr)); // stavlja tu jednu rec u listu
        }

        System.out.println(columns);
    }
}
