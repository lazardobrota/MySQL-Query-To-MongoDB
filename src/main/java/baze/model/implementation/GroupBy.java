package baze.model.implementation;

import baze.model.factory.oprt.FactoryUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupBy extends Clause{


    @Override
    public void fillOut(String[] lines, int l, int r) {
        //Dodaje sve kolone tabela u listu
        for (int i = l + 2; i < r; i++) {
            //Ako treba da ukloni zares izmedju njih
            String[] arr = lines[i].split(",");// deli po zarecu al bice samo jedna rec u teksu, samo mi je lakse ovako da radim
            this.getOperators().add(FactoryUtils.getFactory(arr[0]).getOprt(arr[0]));
        }

        System.out.println("GroupBy: " + this.getOperators());
    }
}
