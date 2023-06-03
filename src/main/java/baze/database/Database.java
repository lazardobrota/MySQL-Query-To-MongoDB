package baze.database;

import baze.data.Row;

import java.util.List;

//Rukovanje (komunikacija sa bazom)
public interface Database {
    //String from govori iz koje tabele zelimo da citamo podatke
    List<Row> getDataFromTable(String from);
}
