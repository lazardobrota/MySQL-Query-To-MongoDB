package baze.model.implementation;

import baze.model.factory.FactoryUtils;
import baze.model.implementation.operators.Avg;
import baze.model.implementation.operators.Max;
import baze.model.implementation.operators.Min;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Select extends Clause{

    private List<String> column;

    public Select(String name) {
        super(name);
        column = new ArrayList<>();
    }

    //Select: string reci, agregacije(min, max, avg)
    //TODO IMA BAG AKO SU SPOJENI SVI DA CE DA KRESUJE AKO SU AVG, MAX, MIN TU, STVARNO ME MRZI VISE DA POPRAVLJAM TO
    @Override
    public void fillOut(String[] lines, int l, int r) {
        //Prolazi kroz sve space-ove za koje znaju da pripadaju select
        for (int i = l + 1 ;i < r; i++) {
            //Ako treba da ukloni zares izmedju njih
            String[] arr = lines[i].split(",");// deli po zarecu

            //Dodaje ih sve listu koji nemaju min, max, avg
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].contains("max") || arr[j].contains("min") || arr[j].contains("avg")) {
                    getOperators().add(Objects.requireNonNull(FactoryUtils.getFactory(arr[j])).getOprt(arr[j])); // pravi novi max ili avg ili min operator

                    // uzima lines , i + j koji govori dokle je u lines stao, na primer lines[2] je max i u arr kada se splituje to ce biti na 0 mestu, pa 2 + 0 = 2
                    this.getOperators().get(getOperators().size() - 1).doOperation(lines, i + j);
                    continue;
                }
                //Ako ima zagrade znaci da je on nekog operatora
                if (arr[j].contains("(") && arr[j].contains(")"))
                    continue;
                column.add(arr[j]);
            }
            //Collections.addAll(column, arr); // dodaje sve u listu
        }
        System.out.println("select: " + column);
        System.out.println("operators: " + getOperators());
    }
}
