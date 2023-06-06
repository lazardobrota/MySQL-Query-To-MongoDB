package baze.model.implementation;

import baze.model.factory.oprt.FactoryUtils;
import baze.model.implementation.operators.ColumnString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Select extends Clause{

    private boolean isDistinct;

    public Select() {

    }

    //Select: string reci, agregacije(min, max, avg)
    @Override
    public void fillOut(String[] lines, int l, int r) {
        //Prolazi kroz sve space-ove za koje znaju da pripadaju select
        for (int i = l + 1 ;i < r; i++) {
            //Ako treba da ukloni zares izmedju njih
            if(lines[i].matches("distinct")){
                this.isDistinct = true;
                continue;
            }
            String[] arr = lines[i].split(",");// deli po zarecu

            //Dodaje ih sve listu koji nemaju min, max, avg, count
            for (int j = 0; j < arr.length; j++) {
                if (arr[j].contains("max") || arr[j].contains("min") || arr[j].contains("avg") || arr[j].contains("count") || arr[j].contains("sum")) {
                    getOperators().add(Objects.requireNonNull(FactoryUtils.getFactory(arr[j])).getOprt(arr[j])); // pravi novi max ili avg ili min operator

                    // uzima lines , i + j koji govori dokle je u lines stao, na primer lines[2] je max i u arr kada se splituje to ce biti na 0 mestu, pa 2 + 0 = 2
                    this.getOperators().get(getOperators().size() - 1).doOperation(lines, i + j);
                    continue;
                }
                //Ako ima zagrade znaci da je on nekog operatora
                if (arr[j].contains("(") && arr[j].contains(")"))
                    continue;
                getOperators().add(FactoryUtils.getFactory(arr[j]).getOprt(arr[j]));
            }
            //Collections.addAll(column, arr); // dodaje sve u listu
        }
        System.out.println("select: " + getOperators());
    }

    @Override
    public String toString() {
        return "Select{" +
                "operators=" + getOperators() +
                '}';
    }
}
