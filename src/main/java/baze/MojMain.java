package baze;

import baze.model.adapter.ClauseAdapter;
import baze.model.factory.adapter.AdapterFactoryUtils;
import baze.model.implementation.Clause;
import baze.model.implementation.SQLQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MojMain {
    public static void main(String[] args) throws IOException {
        while(true){
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Unesi sql Query\n");
            String s = br.readLine();
            if(s.contains("///"))return;
            SQLQuery sq = new SQLQuery();
            sq.strToObj(s);

            List<ClauseAdapter> clauseAdapters = new ArrayList<>();
            for (int i = 0 ;i < sq.getClaues().size(); i++) {
                Clause clause = (Clause) sq.getClaues().get(i);
                clauseAdapters.add((ClauseAdapter) AdapterFactoryUtils.getFactory(clause).getAdapter(clause));
            }

            System.out.println(clauseAdapters);
        }
    }
}
